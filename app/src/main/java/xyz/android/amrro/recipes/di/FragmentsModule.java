package xyz.android.amrro.recipes.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import xyz.android.amrro.recipes.ui.steps.StepsSliderFragment;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 *
 * injects {@link android.support.v4.app.Fragment}s
 */
@Module
public abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract StepsSliderFragment contributesStepsFragment();
}
