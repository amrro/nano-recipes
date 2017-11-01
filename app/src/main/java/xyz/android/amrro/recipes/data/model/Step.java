package xyz.android.amrro.recipes.data.model;


import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Step {
    public static Step create(Integer id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        return new AutoValue_Step(id, shortDescription, description, videoURL, thumbnailURL);
    }

    public static TypeAdapter<Step> typeAdapter(Gson gson) {
        return new AutoValue_Step.GsonTypeAdapter(gson);
    }

    public abstract Integer id();

    public abstract String shortDescription();

    public abstract String description();

    public abstract String videoURL();

    public abstract String thumbnailURL();
}
