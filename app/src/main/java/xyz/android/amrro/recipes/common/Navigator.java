package xyz.android.amrro.recipes.common;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

import xyz.android.amrro.recipes.data.model.Recipe;
import xyz.android.amrro.recipes.ui.recipe.RecipeDetailActivity;


final public class Navigator {
    static final String KEY_ITEM_ID = "KEY_RECIPE_ID";
    static final String KEY_ITEM_TITLE = "KEY_ITEM_TITLE";
    private final Context context;

    public Navigator(final Context context) {
        this.context = Objects.requireNonNull(context, "context cannot be null.");
    }

    public void toRecipeDetails(@NonNull final Recipe recipe) {
        final Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(KEY_ITEM_ID, recipe.id)
                .putExtra(KEY_ITEM_TITLE, recipe.name);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    private void navigateTo(final Class<?> cls) {
        navigateTo(cls, null);
    }

    private void navigateTo(final Class<?> cls, @Nullable final String id) {
        final Intent intent = new Intent(context, cls);
        if (id != null) {
            intent.putExtra(KEY_ITEM_ID, id);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    private void navigateWithClear(final Class<?> cls) {
        final Intent intent = new Intent(context, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
