package xyz.android.amrro.recipes.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import xyz.android.amrro.recipes.data.model.Ingredient;

@Database(entities = {Ingredient.class}, version = 1)
public abstract class IngredientsDatabase extends RoomDatabase {
    public abstract IngredientsDao ingredients();
}
