package xyz.android.amrro.recipes.ui.main;

import xyz.android.amrro.recipes.common.RecyclerActivity;
import xyz.android.amrro.recipes.data.model.Recipe;

public class HomeActivity extends RecyclerActivity<Recipe, RecipesAdapter> {

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
