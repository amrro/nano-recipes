package xyz.android.amrro.recipes.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import xyz.android.amrro.recipes.ui.main.RecipesListActivity;
import xyz.android.amrro.recipes.ui.steps.StepsActivity;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 * <p>
 * Injects {@link android.app.Activity}s
 */

@Module
public abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract RecipesListActivity contributesRecipesListActivity();

    @ContributesAndroidInjector
    abstract StepsActivity contributesStepsActivity();

}
