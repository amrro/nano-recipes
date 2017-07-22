package xyz.android.amrro.recipes.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import xyz.android.amrro.recipes.RecipesApp;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        NetModule.class,
        HomeActivityModule.class,
        FragmentsModule.class
})
public interface AppComponent {
    void inject(RecipesApp recipesApp);
}
