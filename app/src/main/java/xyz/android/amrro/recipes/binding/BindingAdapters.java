
package xyz.android.amrro.recipes.binding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.TextView;

import xyz.android.amrro.recipes.data.model.Ingredient;

/**
 * Data Binding adapters specific to the app.
 */
public class BindingAdapters {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("quantity")
    public static void quantity(TextView tv, Ingredient ingredient) {
        tv.setText(String.format("%s %s", ingredient.getQuantity(), ingredient.getMeasure()));
    }
}
