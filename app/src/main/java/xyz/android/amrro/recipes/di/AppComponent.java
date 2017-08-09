package xyz.android.amrro.recipes.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import xyz.android.amrro.recipes.RecipesApp;


@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        NetModule.class,
        ActivitiesModule.class,
        FragmentsModule.class
})
public interface AppComponent {
    void inject(RecipesApp recipesApp);
}
