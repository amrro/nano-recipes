package xyz.android.amrro.recipes.ui.main;

import android.support.annotation.VisibleForTesting;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import xyz.android.amrro.recipes.common.RecyclerActivity;
import xyz.android.amrro.recipes.data.model.Recipe;

public class HomeActivity extends RecyclerActivity<Recipe, RecipesAdapter> {
    RecipesViewModel recipes;


    @VisibleForTesting
    @Inject OkHttpClient client;

    @Override
    protected RecipesAdapter createAdapter() {
        return new RecipesAdapter(recipe -> navigator.toRecipeDetails(recipe));
    }

    @Override
    protected void createList() {
        recipes = getViewModel(RecipesViewModel.class);
        recipes.getRecipes().observe(this, response -> {
            if (response != null && response.isSuccessful())
                updateAdapter(response.getData());
        });
    }

    @VisibleForTesting
    public OkHttpClient getClient() {
        return client;
    }
}
