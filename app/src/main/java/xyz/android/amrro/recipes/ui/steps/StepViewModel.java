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
import xyz.android.amrro.recipes.data.model.Step;


public final class StepViewModel extends ViewModel {

    private final LiveData<List<Step>> steps;
    private final MutableLiveData<Integer> recipeId = new MutableLiveData<>();

    @Inject
    StepViewModel(@NonNull final RecipeRepo repo) {
        steps = Transformations.switchMap(recipeId, repo::steps);
    }


    @NonNull
    StepViewModel setRecipeId(@NonNull final Integer recipeId) {
        Objects.requireNonNull(recipeId, "stepId cannot be null ");

        if (Objects.equals(this.recipeId.getValue(), recipeId)) {
            return this;
        }
        this.recipeId.setValue(recipeId);
        return this;
    }


    // STOPSHIP: 8/14/17 no need to load all steps here.
    @NonNull
    public LiveData<List<Step>> steps() {
        return steps;
    }

    public LiveData<Step> step(@NonNull final Integer stepId) {
        return Transformations.map(this.steps, steps -> {
            if (steps != null && stepId >= 0) {
                /*for (Step step : steps) {
                    if (step.getId().equals(stepId)) return step;
                }*/
                try {
                    return steps.get(stepId);
                } catch (IndexOutOfBoundsException out) {
                    return null;
                }
            }
            return null;
        });
    }

    @SuppressWarnings("Convert2MethodRef")
    @NonNull
    LiveData<Boolean> hasNext(@NonNull final Integer stepId) {
        return Transformations.map(this.step(stepId + 1), step -> step != null);
    }

    @SuppressWarnings("Convert2MethodRef")
    @NonNull
    LiveData<Boolean> hasPrevious(@NonNull final Integer stepId) {
        return Transformations.map(this.step(stepId - 1), step -> step != null);
    }
}
