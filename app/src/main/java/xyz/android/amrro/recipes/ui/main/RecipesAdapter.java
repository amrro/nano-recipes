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
}

