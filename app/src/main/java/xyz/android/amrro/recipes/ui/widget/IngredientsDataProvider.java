package xyz.android.amrro.recipes.ui.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.model.Ingredient;


public final class IngredientsDataProvider implements RemoteViewsService.RemoteViewsFactory {
    final private List<Ingredient> ingredients = new ArrayList<>();
    final private Context context;
    private int appWidgetId;


    IngredientsDataProvider(Context context, Intent intent) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        initData();
    }

    private void initData() {
        ingredients.clear();
        for (int index = 0; index < 10; index++) {
            ingredients.add(
                    Ingredient.create(
                            Double.valueOf(String.valueOf(index)),
                            "CUPS",
                            "ingredient " + index
                    )
            );
        }
    }

    @Override
    public void onDataSetChanged() {
        initData();
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
        return String.format("%s %s", ingredient.quantity(), ingredient.measure());
    }
}
