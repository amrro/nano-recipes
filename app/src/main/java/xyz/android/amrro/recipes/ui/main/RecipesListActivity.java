package xyz.android.amrro.recipes.ui.main;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.model.Recipe;
import xyz.android.amrro.recipes.databinding.ActivityRecipesListBinding;
import xyz.android.amrro.recipes.ui.recipe.RecipeDetailActivity;

public class RecipesListActivity extends LifecycleActivity {
    public static final int SPAN_COUNT = 1;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ActivityRecipesListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipes_list);

        binding.grid.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        ViewModelProviders.of(this, viewModelFactory)
                .get(RecipesViewModel.class)
                .getRecipes()
                .observe(this, response -> {
                    if (response != null && response.isSuccessful()) {
                        binding.grid.setAdapter(
                                new RecipesAdapter(response.getData(), recipe -> {
                                    Intent intent = new Intent(this, RecipeDetailActivity.class);
                                    intent.putExtra(RecipeDetailActivity.KEY_RECIPE_ID, recipe.getId());
                                    intent.putExtra(RecipeDetailActivity.KEY_RECIPE_NAME, recipe.getName());
                                    startActivity(intent);
                                }));
                    }
                });
    }

    interface OnRecipeClickedListener {
        void onRecipeClicked(@NonNull final Recipe recipe);
    }
}
