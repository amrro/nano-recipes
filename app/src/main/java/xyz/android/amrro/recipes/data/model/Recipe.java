package xyz.android.amrro.recipes.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class Recipe {
    public static Recipe create(Integer id, String name, List<Ingredient> ingredients, List<Step> steps, Integer servings, String image) {
        return new AutoValue_Recipe(id, name, ingredients, steps, servings, image);
    }

    public static TypeAdapter<Recipe> typeAdapter(Gson gson) {
        return new AutoValue_Recipe.GsonTypeAdapter(gson);
    }

    public abstract Integer id();

    public abstract String name();

    public abstract List<Ingredient> ingredients();

    public abstract List<Step> steps();

    public abstract Integer servings();

    public abstract String image();

    /*@NonNull
    final public Integer id;
    @NonNull
    final public String name;
    @NonNull
    final public List<Ingredient> ingredients;
    @NonNull
    final public List<Step> steps;
    @NonNull
    final public Integer servings;
    @NonNull
    final public String image;


    public Recipe(@NonNull Integer id,
                  @NonNull String name,
                  @NonNull List<Ingredient> ingredients,
                  @NonNull List<Step> steps,
                  @NonNull Integer servings,
                  @NonNull String image) {

        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(servings);

        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) &&
                Objects.equals(name, recipe.name) &&
                Objects.equals(ingredients, recipe.ingredients) &&
                Objects.equals(steps, recipe.steps) &&
                Objects.equals(servings, recipe.servings) &&
                Objects.equals(image, recipe.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ingredients, steps, servings, image);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                ", servings=" + servings +
                ", image='" + image + '\'' +
                '}';
    }*/
}
