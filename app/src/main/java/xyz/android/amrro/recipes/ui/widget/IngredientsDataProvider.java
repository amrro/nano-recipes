package xyz.android.amrro.recipes.ui.widget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import timber.log.Timber;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.db.IngredientsContentProvider;
import xyz.android.amrro.recipes.data.model.Ingredient;


public final class IngredientsDataProvider extends BroadcastReceiver
        implements RemoteViewsService.RemoteViewsFactory {

    public static final String APP_UPDATE_WIDGET = "xyz.android.amrro.recipes.ui.widget.APP_UPDATE_WIDGET";
    public static final String KEY_INGREDIENTS = "key-ingredients";
    public static final String KEY_RECIPE_ID = "key-recipe-id";

    @Inject
    Context app;
    private Cursor cursor;

    public IngredientsDataProvider() {
    }

    IngredientsDataProvider(Context context) {
    }

    private static void notifyWidgetsDataChanged(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context.getApplicationContext(), IngredientsWidget.class));
        //Trigger data update to handle the GridView widgets and force a data refresh
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_ingredients_list);
        //Now update all widgets
        for (int appWidgetId : appWidgetIds) {
            IngredientsWidget.updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AndroidInjection.inject(this, context);

        final String action = intent.getAction();
        if (action != null && action.equals(APP_UPDATE_WIDGET)) {
            this.onDataSetChanged();
            notifyWidgetsDataChanged(context);
        }
    }

    @Override
    public void onCreate() {
    }


    @Override
    public void onDataSetChanged() {
        Timber.i(">>>>onDataSetChanged<<<<");
        if (cursor != null) cursor.close();
        if (app != null) {
            cursor = app.getContentResolver().query(
                    IngredientsContentProvider.URI_INGREDIENT, null,
                    null, null, null
            );
        }
    }

    @Override
    public void onDestroy() {
        cursor.close();
    }

    @Override
    public int getCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (cursor == null || cursor.getCount() == 0) return null;
        final Ingredient ingredient = Ingredient.fromCursor(cursor, position);

        Timber.i(">>>> ingredients: %s", ingredient.toString());

        final RemoteViews remoteView = new RemoteViews(app.getPackageName(), R.layout.card_ingredient);
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
