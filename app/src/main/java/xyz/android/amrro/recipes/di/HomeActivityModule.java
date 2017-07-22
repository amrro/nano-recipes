package xyz.android.amrro.recipes.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import xyz.android.amrro.recipes.ui.main.MainActivity;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 */
@Module
public abstract class HomeActivityModule {
    @ContributesAndroidInjector
    abstract MainActivity contributeHomeActivity();
}
