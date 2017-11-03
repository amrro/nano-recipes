package xyz.android.amrro.recipes.ui.widget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import timber.log.Timber;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.api.WidgetService;
import xyz.android.amrro.recipes.data.model.Ingredient;


public final class IngredientsDataProvider extends BroadcastReceiver
        implements RemoteViewsService.RemoteViewsFactory {

    public static final String APP_UPDATE_WIDGET = "xyz.android.amrro.recipes.ui.widget.APP_UPDATE_WIDGET";
    public static final String KEY_INGREDIENTS = "key-ingredients";
    public static final String KEY_RECIPE_ID = "key-recipe-id";

    final private List<Ingredient> ingredients = new ArrayList<>();
    @Inject
    WidgetService api;
    @Inject
    SharedPreferences prefs;
    @Inject
    Gson gson;
    //    private int appWidgetId;
    private Context context;
    private int index = 0;

    public IngredientsDataProvider() {
    }

    IngredientsDataProvider(Context context, Intent intent) {
        this.context = context;
        /*appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);*/
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
        ingredients.clear();
        ingredients.addAll(getIngredients());
    }

    @Override
    public void onDestroy() {
//        ingredients.clear();
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.card_ingredient);
        Ingredient listItem = ingredients.get(position);
        remoteView.setTextViewText(R.id.ingredient_name, listItem.ingredient());
        remoteView.setTextViewText(R.id.ingredient_quantity, quantity(listItem));
        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return new RemoteViews(context.getPackageName(), R.layout.widget_loading);
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
        return String.format("%s %s", ingredient.quantity(), ingredient.measure());
    }

    private List<Ingredient> getIngredients() {
        Type type = new TypeToken<List<Ingredient>>() {
        }.getType();
        final String ingredientsJson = prefs.getString(KEY_INGREDIENTS, null);
        return gson.fromJson(ingredientsJson, type);

    }
}
