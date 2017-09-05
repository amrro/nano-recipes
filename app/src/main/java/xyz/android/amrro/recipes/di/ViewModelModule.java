package xyz.android.amrro.recipes.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import xyz.android.amrro.recipes.ui.main.RecipesViewModel;
import xyz.android.amrro.recipes.ui.recipe.SingleRecipeViewModel;
import xyz.android.amrro.recipes.ui.steps.StepViewModel;
import xyz.android.amrro.recipes.utils.ViewModelFactory;

@SuppressWarnings("WeakerAccess")
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RecipesViewModel.class)
    abstract ViewModel bindRecipesViewModel(RecipesViewModel recipesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SingleRecipeViewModel.class)
    abstract ViewModel bindSingleRecipeViewModel(SingleRecipeViewModel singleRecipeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(StepViewModel.class)
    abstract ViewModel bindStepsViewModel(StepViewModel stepViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
