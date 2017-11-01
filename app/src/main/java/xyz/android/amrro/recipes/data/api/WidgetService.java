package xyz.android.amrro.recipes.data.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import xyz.android.amrro.recipes.data.model.Recipe;

/**
 * This service is created especially for the widget, since widgets cannot used
 * Android Architecture Components cuz Lifecycle (till further research).
 */

public interface WidgetService {
    @GET("baking.json")
    Call<List<Recipe>> recipes();
}
