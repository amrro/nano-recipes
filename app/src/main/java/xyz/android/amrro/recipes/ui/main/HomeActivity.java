package xyz.android.amrro.recipes.ui.main;

import xyz.android.amrro.recipes.common.SimpleRecyclerActivity;
import xyz.android.amrro.recipes.data.model.Recipe;

public class HomeActivity extends SimpleRecyclerActivity<Recipe, RecipesAdapter> {

    @Override
    protected RecipesAdapter createAdapter() {
        return new RecipesAdapter(recipe -> navigator.toRecipeDetails(recipe));
    }

    @Override
    protected void createList() {
        RecipesViewModel recipes = getViewModel(RecipesViewModel.class);
        recipes.getRecipes().observe(this, response -> {
            if (response != null && response.isSuccessful())
                updateAdapter(response.getData());
        });
    }
}
