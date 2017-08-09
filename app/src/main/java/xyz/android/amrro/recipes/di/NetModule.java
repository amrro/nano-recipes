package xyz.android.amrro.recipes.di;

import android.support.annotation.NonNull;

import com.github.simonpercic.oklog3.OkLogInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.android.amrro.recipes.data.api.RecipesService;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 *
 * Main Dependencies for API
 */
@Module
public class NetModule {

    @NonNull
    private String url;

    public NetModule(@NonNull String url) {
        this.url = url;
    }


    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Singleton
    @Provides
    public OkLogInterceptor provideOkLogInterceptor() {
        return new OkLogInterceptor.Builder()
                .shortenInfoUrl(true)
                .withAllLogData()
                .useAndroidLog(true)
                .build();
    }


    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(OkLogInterceptor logInterceptor) {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(logInterceptor)
                .build();
    }

    @Singleton
    @Provides
    public RecipesService provideMoviesService(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(url)
                .client(client)
                .build()
                .create(RecipesService.class);
    }
}