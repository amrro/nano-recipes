package xyz.android.amrro.recipes.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.api.WidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWidget extends AppWidgetProvider {

    @Inject
    public WidgetService api;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget);

        setRemoteAdapter(context, views, appWidgetId);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    /**
     * Sets the remote adapter used to fill in the list items
     *
     * @param views    RemoteViews to set the RemoteAdapter
     * @param widgetId
     */
    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views, int widgetId) {
        final Intent intent = new Intent(context, IngredientsListService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        views.setRemoteAdapter(R.id.widget_ingredients_list, intent);
    }

    public static void updateWidgets(Context context, AppWidgetManager manager, int[] widgetIds) {
        for (int widgetId : widgetIds) {
            updateAppWidget(context, manager, widgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AndroidInjection.inject(this, context);
        final String action = intent.getAction();
        if (action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            refreshAllWidget(context);
        }
        super.onReceive(context, intent);
    }

    private void refreshAllWidget(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, IngredientsWidget.class));
        //Trigger data update to handle the GridView widgets and force a data refresh
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_ingredients_list);
        //Now update all widgets
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        WidgetUpdateService.startActionUpdateWidgets(context);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

