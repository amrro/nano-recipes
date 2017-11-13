package xyz.android.amrro.recipes.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;
import java.util.Objects;


@SuppressWarnings("WeakerAccess")
@Entity(tableName = Ingredient.TABLE_NAME)
public class Ingredient {
    public static final String TABLE_NAME = "ingredients";

    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_MEASURE = "measure";
    public static final String COLUMN_INGREDIENT_NAME = "ingredient";
    public final Double quantity;
    public final String measure;
    public final String ingredient;
    @PrimaryKey(autoGenerate = true)
    public long id;

    public Ingredient(Double quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public static Ingredient fromContentValues(final ContentValues values) {
        IngredientBuilder builder = new IngredientBuilder();
        if (values.containsKey(COLUMN_QUANTITY)) {
            builder.quantity(values.getAsDouble(COLUMN_QUANTITY));
        }

        if (values.containsKey(COLUMN_MEASURE)) {
            builder.measure(values.getAsString(COLUMN_MEASURE));
        }

        if (values.containsKey(COLUMN_INGREDIENT_NAME)) {
            builder.ingredient(values.getAsString(COLUMN_INGREDIENT_NAME));
        }

        return builder.build();
    }

    public static ContentValues[] toContentValues(List<Ingredient> ingredients) {
        ContentValues[] values = new ContentValues[ingredients.size()];
        for (int index = 0; index < ingredients.size(); index++) {
            final ContentValues current = new ContentValues();
            current.put(COLUMN_MEASURE, ingredients.get(index).measure);
            current.put(COLUMN_QUANTITY, ingredients.get(index).quantity);
            current.put(COLUMN_INGREDIENT_NAME, ingredients.get(index).ingredient);

            values[index] = current;
        }
        return values;
    }

    public static Ingredient fromCursor(final Cursor cursor, final int position) {
        cursor.moveToPosition(position);
        final int measureIndex = cursor.getColumnIndex(Ingredient.COLUMN_MEASURE);
        final int ingredientIndex = cursor.getColumnIndex(Ingredient.COLUMN_INGREDIENT_NAME);
        final int quantityIndex = cursor.getColumnIndex(Ingredient.COLUMN_QUANTITY);

        final double quantity = quantityIndex != - 1 ? cursor.getDouble(quantityIndex) : 0.0;
        final String measure = cursor.getString(measureIndex);
        final String ingredient = cursor.getString(ingredientIndex);

        return new IngredientBuilder()
                .quantity(quantity)
                .measure(measure)
                .ingredient(ingredient)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(quantity, that.quantity) &&
                Objects.equals(measure, that.measure) &&
                Objects.equals(ingredient, that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, measure, ingredient);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "quantity=" + quantity +
                ", measure='" + measure + '\'' +
                ", ingredient='" + ingredient + '\'' +
                '}';
    }

    public static class IngredientBuilder {
        private Double quantity;
        private String measure;
        private String ingredient;

        public IngredientBuilder quantity(Double quantity) {
            this.quantity = quantity;
            return this;
        }

        public IngredientBuilder measure(String measure) {
            this.measure = measure;
            return this;
        }

        public IngredientBuilder ingredient(String ingredient) {
            this.ingredient = ingredient;
            return this;
        }

        public Ingredient build() {
            return new Ingredient(quantity, measure, ingredient);
        }
    }
}
