package xyz.android.amrro.recipes.ui.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import xyz.android.amrro.recipes.R;


public final class WidgetUpdateService extends IntentService {
    public static final String ACTION_UPDATE_WIDGETS = "xyz.android.amrro.recipes.action.update_widgets";


    public WidgetUpdateService() {
        super("update-ingredient-widgets");
    }

    /**
     * Starts this service to perform UpdateWidgets action with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionUpdateWidgets(Context context) {
        Intent intent = new Intent(context, WidgetUpdateService.class);
        intent.setAction(ACTION_UPDATE_WIDGETS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_WIDGETS.equals(action)) {
                handleActionUpdateWidgets();
            }
        }
    }

    private void handleActionUpdateWidgets() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, IngredientsWidget.class));
        //Trigger data update to handle the GridView widgets and force a data refresh
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_ingredients_list);
        //Now update all widgets
        IngredientsWidget.updateWidgets(this, appWidgetManager, appWidgetIds);
    }
}
