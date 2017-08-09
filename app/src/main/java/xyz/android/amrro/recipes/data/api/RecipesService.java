package xyz.android.amrro.recipes.data.api;


import android.arch.lifecycle.LiveData;

import java.util.List;

import retrofit2.http.GET;
import xyz.android.amrro.recipes.data.model.Recipe;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 *
 * Service to make the API requests.
 */

public interface RecipesService {

    @GET("baking.json")
    public LiveData<ApiResponse<List<Recipe>>> recipes();

}
