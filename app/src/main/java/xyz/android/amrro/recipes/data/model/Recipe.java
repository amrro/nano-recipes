package xyz.android.amrro.recipes.data.model;

import java.util.List;
import java.util.Objects;


@SuppressWarnings("WeakerAccess")
public class Recipe {

    public final Integer id;
    public final String name;
    public final List<Ingredient> ingredients;
    public final List<Step> steps;
    public final Integer servings;
    public final String image;

    public Recipe(Integer id, String name, List<Ingredient> ingredients, List<Step> steps, Integer servings, String image) {
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
    }
}
