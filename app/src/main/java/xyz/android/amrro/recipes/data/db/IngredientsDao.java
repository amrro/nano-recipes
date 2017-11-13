package xyz.android.amrro.recipes.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import xyz.android.amrro.recipes.data.model.Ingredient;


@Dao
public interface IngredientsDao {

    @Query("SELECT * FROM " + Ingredient.TABLE_NAME)
    Cursor selectAll();

    @Insert
    long[] insertAll(Ingredient... ingredients);

    @Insert
    long insert(Ingredient ingredient);

    @Query("DELETE FROM " + Ingredient.TABLE_NAME)
    int deleteAll();
}
