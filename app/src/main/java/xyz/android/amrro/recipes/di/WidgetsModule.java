package xyz.android.amrro.recipes.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import xyz.android.amrro.recipes.ui.widget.IngredientsWidget;

/**
 * To inject the app widgets.
 */
@Module
abstract class WidgetsModule {

    @ContributesAndroidInjector
    abstract IngredientsWidget contributesIngredientsWidget();

}
