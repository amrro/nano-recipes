package xyz.android.amrro.recipes.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import xyz.android.amrro.recipes.data.RecipeRepo;
import xyz.android.amrro.recipes.data.api.ApiResponse;
import xyz.android.amrro.recipes.data.model.Recipe;


public final class RecipesViewModel extends ViewModel {

    private LiveData<ApiResponse<List<Recipe>>> recipes;
    private MutableLiveData<String> retry = new MutableLiveData<>();

    @Inject
    RecipesViewModel(@NonNull final RecipeRepo repo) {
        recipes = Transformations.switchMap(retry, input -> repo.recipes());
        retry.setValue("Retry");
    }

    public LiveData<ApiResponse<List<Recipe>>> getRecipes() {
        return recipes;
    }

    void retry() {
        if (this.retry.getValue() != null) {
            this.retry.setValue(this.retry.getValue());
        }
    }
}
