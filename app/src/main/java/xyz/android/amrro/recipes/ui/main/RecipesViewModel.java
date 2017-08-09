package xyz.android.amrro.recipes.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import xyz.android.amrro.recipes.data.RecipeRepo;
import xyz.android.amrro.recipes.data.api.ApiResponse;
import xyz.android.amrro.recipes.data.model.Recipe;


public final class RecipesViewModel extends ViewModel {

    private LiveData<ApiResponse<List<Recipe>>> recipes;

    @Inject
    RecipesViewModel(@NonNull final RecipeRepo repo) {
        recipes = repo.recipes();
    }

    public LiveData<ApiResponse<List<Recipe>>> getRecipes() {
        return recipes;
    }
}
