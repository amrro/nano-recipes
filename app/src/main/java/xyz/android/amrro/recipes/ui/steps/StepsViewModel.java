package xyz.android.amrro.recipes.ui.steps;

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
import xyz.android.amrro.recipes.data.model.Step;


public final class StepsViewModel extends ViewModel {

    private final LiveData<List<Step>> steps;
    private final LiveData<List<Ingredient>> ingredient;
    private final MutableLiveData<Integer> id = new MutableLiveData<>();

    @Inject
    StepsViewModel(@NonNull final RecipeRepo repo) {
        steps = Transformations.switchMap(id, repo::steps);
        ingredient = Transformations.switchMap(id, repo::ingredient);
    }


    public StepsViewModel setId(@NonNull final Integer id) {
        Objects.requireNonNull(id, "id cannot be null ");
        if (Objects.equals(this.id.getValue(), id)) {
            return this;
        }
        this.id.setValue(id);
        return this;
    }

    @NonNull
    public LiveData<List<Step>> steps() {
        return steps;
    }

    @NonNull
    public LiveData<List<Ingredient>> ingredient() {
        return ingredient;
    }

    public LiveData<Step> step(@NonNull final Integer stepId) {
        return Transformations.map(this.steps, steps -> {
            if (steps != null) {
                for (Step step : steps) {
                    if (step.getId().equals(stepId)) return step;
                }
            }
            return null;
        });
    }
}
