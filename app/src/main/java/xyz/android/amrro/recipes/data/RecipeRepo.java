package xyz.android.amrro.recipes.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import xyz.android.amrro.recipes.data.api.ApiResponse;
import xyz.android.amrro.recipes.data.api.RecipesService;
import xyz.android.amrro.recipes.data.model.Ingredient;
import xyz.android.amrro.recipes.data.model.Recipe;
import xyz.android.amrro.recipes.data.model.Step;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 8/9/17.
 * <p>
 * Main Repository for Recipes.
 */

public final class RecipeRepo {

    @NonNull
    private RecipesService service;


    @Inject
    public RecipeRepo(@NonNull RecipesService service) {
        Objects.requireNonNull(service);
        this.service = service;
    }

    public LiveData<ApiResponse<List<Recipe>>> recipes() {
        return service.recipes();
    }

    @NonNull
    public LiveData<Recipe> recipe(@NonNull final Integer id) {
        return Transformations.map(this.recipes(), response -> {
            if (response != null && response.isSuccessful()) {
                for (Recipe recipe : response.getData()) {
                    if (recipe.getId().equals(id)) return recipe;
                }
            }
            return null;
        });
    }


    // STOPSHIP: 8/9/17 if useless, delete
    @NonNull
    public LiveData<List<Ingredient>> ingredient(@NonNull final Integer id) {
        return Transformations.map(this.recipes(), response -> {
            if (response != null && response.isSuccessful()) {
                for (Recipe recipe : response.getData()) {
                    if (recipe.getId().equals(id))
                        return recipe.getIngredients();
                }
            }
            return new ArrayList<>();
        });
    }

    // STOPSHIP: 8/9/17 if useless, delete
    @NonNull
    public LiveData<List<Step>> steps(@NonNull final Integer id) {
        return Transformations.map(this.recipes(), response -> {
            if (response != null && response.isSuccessful()) {
                for (Recipe recipe : response.getData()) {
                    if (recipe.getId().equals(id)) return recipe.getSteps();
                }
            }
            return new ArrayList<>();
        });
    }


}
