package xyz.android.amrro.recipes.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Ingredient {

    public static Ingredient create(Double quantity, String measure, String ingredient) {
        return new AutoValue_Ingredient(quantity, measure, ingredient);
    }

    public static TypeAdapter<Ingredient> typeAdapter(Gson gson) {
        return new AutoValue_Ingredient.GsonTypeAdapter(gson);
    }

    public abstract Double quantity();

    public abstract String measure();

    public abstract String ingredient();
}
