package xyz.android.amrro.recipes.di;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.api.RecipesService;
import xyz.android.amrro.recipes.utils.retrofit.LiveDataCallAdapterFactory;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 * <p>
 * Main Dependencies for API
 */
@SuppressWarnings("WeakerAccess")
@Module
public class NetModule {


    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();
    }

    @Singleton
    @Provides
    public RecipesService provideMoviesService(Application application, OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .baseUrl(application.getString(R.string.api_url))
                .client(client)
                .build()
                .create(RecipesService.class);
    }
}
