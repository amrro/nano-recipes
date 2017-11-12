package xyz.android.amrro.recipes.ui.widget;

import android.content.Context;
import android.database.Cursor;
import android.os.Binder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import timber.log.Timber;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.db.IngredientsContentProvider;
import xyz.android.amrro.recipes.data.model.Ingredient;


public final class IngredientsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    Context context;
    Cursor cursor;

    IngredientsRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
    }


    @Override
    public void onDataSetChanged() {
        Timber.i(">>>>onDataSetChanged<<<<");
//        Permission Denial
        final long token = Binder.clearCallingIdentity();
        try {
            if (cursor != null) cursor.close();
            cursor = context.getContentResolver().query(
                    IngredientsContentProvider.URI_INGREDIENT, null,
                    null, null, null
            );
        } finally {
            Binder.restoreCallingIdentity(token);
        }

    }

    @Override
    public void onDestroy() {
        if (cursor != null) cursor.close();
    }

    @Override
    public int getCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (cursor == null || cursor.getCount() == 0) return null;
        final Ingredient ingredient = Ingredient.fromCursor(cursor, position);
        final RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.card_ingredient);
        remoteView.setTextViewText(R.id.ingredient_name, ingredient.ingredient);
        remoteView.setTextViewText(R.id.ingredient_quantity, quantity(ingredient));
        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private String quantity(Ingredient ingredient) {
        return String.format("%s %s", ingredient.quantity, ingredient.measure);
    }

}
