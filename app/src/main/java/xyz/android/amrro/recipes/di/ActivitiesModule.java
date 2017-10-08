package xyz.android.amrro.recipes.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import xyz.android.amrro.recipes.ui.main.HomeActivity;
import xyz.android.amrro.recipes.ui.recipe.BakingActivity;
import xyz.android.amrro.recipes.ui.steps.StepsSliderActivity;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 * <p>
 * Injects {@link android.app.Activity}s
 */

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract HomeActivity contributesRecipesListActivity();

    @ContributesAndroidInjector
    abstract StepsSliderActivity contributesStepsActivity();

    @ContributesAndroidInjector
    abstract BakingActivity contributesBakingActivity();
}
