package xyz.android.amrro.recipes.data.model;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.Objects;


public final class Recipe {

    @NonNull
    final private Integer id;
    @NonNull
    final private String name;
    @NonNull
    final private List<Ingredient> ingredients;
    @NonNull
    final private List<Step> steps;
    @NonNull
    final private Integer servings;
    @NonNull
    final private String image;


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

    @NonNull
    public Integer getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @NonNull
    public List<Step> getSteps() {
        return steps;
    }

    @NonNull
    public Integer getServings() {
        return servings;
    }

    @NonNull
    public String getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(getId(), recipe.getId()) &&
                Objects.equals(getName(), recipe.getName()) &&
                Objects.equals(getIngredients(), recipe.getIngredients()) &&
                Objects.equals(getSteps(), recipe.getSteps()) &&
                Objects.equals(getServings(), recipe.getServings()) &&
                Objects.equals(getImage(), recipe.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getIngredients(), getSteps(), getServings(), getImage());
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
