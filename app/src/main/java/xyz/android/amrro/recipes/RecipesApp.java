package xyz.android.amrro.recipes;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;
import xyz.android.amrro.recipes.di.AppModule;
import xyz.android.amrro.recipes.di.DaggerAppComponent;
import xyz.android.amrro.recipes.di.NetModule;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 */

public final class RecipesApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/"))
                .build()
                .inject(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
