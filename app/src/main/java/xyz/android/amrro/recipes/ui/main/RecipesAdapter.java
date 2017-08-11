package xyz.android.amrro.recipes.ui.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.model.Recipe;
import xyz.android.amrro.recipes.databinding.CartRecipeBinding;


public final class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    private List<Recipe> data;
    private RecipesListActivity.OnRecipeClickedListener listener;


    RecipesAdapter(List<Recipe> data,
                   RecipesListActivity.OnRecipeClickedListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final CartRecipeBinding binding = DataBindingUtil.inflate(inflater, R.layout.cart_recipe, parent, false);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        final Recipe recipe = data.get(position);
        holder.binding.getRoot().setOnClickListener(view -> listener.onRecipeClicked(recipe));
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    static final class RecipeViewHolder extends RecyclerView.ViewHolder {
        private CartRecipeBinding binding;

        RecipeViewHolder(final CartRecipeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(@NonNull final Recipe recipe) {
            Objects.requireNonNull(recipe, "recipe cannot be null.");
            binding.name.setText(recipe.getName());
            if (!recipe.getImage().isEmpty()) {
                Glide.with(binding.getRoot())
                        .load(recipe.getImage())
                        .into(binding.image);
            }
        }
    }
}

