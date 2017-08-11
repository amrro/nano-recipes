package xyz.android.amrro.recipes.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import xyz.android.amrro.recipes.ui.main.RecipesListActivity;
import xyz.android.amrro.recipes.ui.recipe.RecipeDetailActivity;
import xyz.android.amrro.recipes.ui.steps.StepDetailActivity;

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
    abstract StepDetailActivity contributesStepsActivity();

    @ContributesAndroidInjector
    abstract RecipeDetailActivity contributesRecipeDetailActivity();
}
