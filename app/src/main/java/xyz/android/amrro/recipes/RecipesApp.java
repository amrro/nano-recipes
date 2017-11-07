package xyz.android.amrro.recipes;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasBroadcastReceiverInjector;
import dagger.android.HasContentProviderInjector;
import timber.log.Timber;
import xyz.android.amrro.recipes.di.AppComponent;
import xyz.android.amrro.recipes.di.AppModule;
import xyz.android.amrro.recipes.di.DaggerAppComponent;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 * <p>
 * Initialize main objects for whole application.
 */

public class RecipesApp extends Application
        implements HasActivityInjector, HasBroadcastReceiverInjector, HasContentProviderInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Inject
    DispatchingAndroidInjector<BroadcastReceiver> receiverInjector;

    @Inject
    DispatchingAndroidInjector<ContentProvider> providerInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        component().inject(this);
    }

    protected AppComponent component() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    @Override
    public AndroidInjector<BroadcastReceiver> broadcastReceiverInjector() {
        return receiverInjector;
    }

    @Override
    public AndroidInjector<ContentProvider> contentProviderInjector() {
        return providerInjector;
    }
}
