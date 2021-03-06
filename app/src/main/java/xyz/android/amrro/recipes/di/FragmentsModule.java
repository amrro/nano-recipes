package xyz.android.amrro.recipes.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import xyz.android.amrro.recipes.ui.recipe.ingredient.IngredientsFragment;
import xyz.android.amrro.recipes.ui.recipe.step.StepsFragment;
import xyz.android.amrro.recipes.ui.steps.StepDetailFragment;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 * injects {@link android.support.v4.app.Fragment}s
 */
@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract StepDetailFragment contributesStepDetailFragment();

    @ContributesAndroidInjector
    abstract StepsFragment contributesStepsFragment();

    @ContributesAndroidInjector
    abstract IngredientsFragment contributesIngredientsFragment();
}
