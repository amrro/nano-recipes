package xyz.android.amrro.recipes.data.model;

import android.support.annotation.NonNull;

import java.util.Objects;

public class Ingredient {

    @NonNull
    private final Double quantity;
    @NonNull
    private final String measure;
    @NonNull
    private final String ingredient;

    public Ingredient(@NonNull Double quantity,
                      @NonNull String measure,
                      @NonNull String ingredient) {
        Objects.requireNonNull(quantity, "quantity cannot be null!");
        Objects.requireNonNull(measure, " measure cannot be null");
        Objects.requireNonNull(ingredient, "ingredient cannot be null");

        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    @NonNull
    public Double getQuantity() {
        return quantity;
    }

    @NonNull
    public String getMeasure() {
        return measure;
    }

    @NonNull
    public String getIngredient() {
        return ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(getQuantity(), that.getQuantity()) &&
                Objects.equals(getMeasure(), that.getMeasure()) &&
                Objects.equals(getIngredient(), that.getIngredient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuantity(), getMeasure(), getIngredient());
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "quantity=" + quantity +
                ", measure='" + measure + '\'' +
                ", ingredient='" + ingredient + '\'' +
                '}';
    }
}
