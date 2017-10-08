package xyz.android.amrro.recipes.ui.recipe;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import xyz.android.amrro.recipes.data.RecipeRepo;
import xyz.android.amrro.recipes.data.model.Ingredient;
import xyz.android.amrro.recipes.data.model.Recipe;


public final class SingleRecipeViewModel extends ViewModel {

    private final MutableLiveData<Integer> id = new MutableLiveData<>();
    private LiveData<Recipe> recipe;
    private LiveData<List<Ingredient>> ingredient;

    @Inject
    SingleRecipeViewModel(@NonNull final RecipeRepo repo) {
        recipe = Transformations.switchMap(id, repo::recipe);
        ingredient = Transformations.switchMap(id, repo::ingredient);
    }

    public LiveData<Recipe> recipe() {
        return recipe;
    }

    public LiveData<List<Ingredient>> ingredients() {
        return ingredient;
    }

    public SingleRecipeViewModel setId(@NonNull final Integer id) {
        Objects.requireNonNull(id, "id cannot be null ");
        if (Objects.equals(this.id.getValue(), id)) {
            return this;
        }
        this.id.setValue(id);
        return this;
    }

    void retry() {
        if (this.id.getValue() != null) {
            this.id.setValue(this.id.getValue());
        }
    }
}
