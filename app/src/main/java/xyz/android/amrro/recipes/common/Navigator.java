package xyz.android.amrro.recipes.common;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.Objects;

import xyz.android.amrro.recipes.data.model.Recipe;
import xyz.android.amrro.recipes.data.model.Step;
import xyz.android.amrro.recipes.ui.recipe.BakingActivity;
import xyz.android.amrro.recipes.ui.steps.StepDetailFragment;
import xyz.android.amrro.recipes.ui.steps.StepsSliderActivity;


public class Navigator {
    public static final String KEY_ITEM_ID = "KEY_RECIPE_ID";
    static final String KEY_ITEM_TITLE = "KEY_ITEM_TITLE";
    private final Context context;

    Navigator(final Context context) {
        this.context = Objects.requireNonNull(context, "context cannot be null.");
    }

    public void toRecipeDetails(@NonNull final Recipe recipe) {
        final Intent intent = new Intent(context, BakingActivity.class);
        intent.putExtra(KEY_ITEM_ID, recipe.id())
                .putExtra(KEY_ITEM_TITLE, recipe.name());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    public void toSteps(Integer recipeId, Step step) {
        final Intent intent = new Intent(context, StepsSliderActivity.class);
        intent.putExtra(StepDetailFragment.ARG_RECIPE_ID, recipeId)
                .putExtra(StepDetailFragment.ARG_STEP_ID, step.id());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
}
