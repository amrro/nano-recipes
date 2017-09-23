package xyz.android.amrro.recipes.ui.main;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.common.DataListAdapter;
import xyz.android.amrro.recipes.common.OnItemClickedListener;
import xyz.android.amrro.recipes.data.model.Recipe;
import xyz.android.amrro.recipes.databinding.CartRecipeBinding;


public final class RecipesAdapter extends DataListAdapter<Recipe, CartRecipeBinding> {
    RecipesAdapter(OnItemClickedListener<Recipe> listener) {
        super(listener);
    }

    @Override
    protected CartRecipeBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        final CartRecipeBinding binding = DataBindingUtil.inflate(inflater, R.layout.cart_recipe, parent, false);
        binding.getRoot().setOnClickListener(view -> {
            final Recipe recipe = binding.getRecipe();
            if (recipe != null) listener.onClicked(recipe);
        });
        return binding;
    }

    @Override
    protected void bind(CartRecipeBinding binding, Recipe item) {
        binding.setRecipe(item);
        binding.name.setText(item.name);
        if (! item.image.isEmpty()) {
            Glide.with(binding.getRoot())
                    .load(item.image)
                    .into(binding.image);
        }
    }

    /*private List<Recipe> data;
    private HomeActivity.OnRecipeClickedListener listener;


    RecipesAdapter(List<Recipe> data,
                   HomeActivity.OnRecipeClickedListener listener) {
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
            binding.name.setText(recipe.name);
            if (! recipe.image.isEmpty()) {
                Glide.with(binding.getRoot())
                        .load(recipe.image)
                        .into(binding.image);
            }
        }
    }*/
}

