package xyz.android.amrro.recipes.ui.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Defining the Adapter of the {@link android.widget.ListView}
 */

public final class IngredientsListService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new IngredientsDataProvider(this.getApplicationContext()));
    }
}
