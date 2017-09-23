package xyz.android.amrro.recipes.common;

import android.support.annotation.NonNull;

/**
 * used with {@link android.support.v7.widget.RecyclerView.Adapter}s to return the data of clicked item.
 *
 * @param <T>
 */
public interface OnItemClickedListener<T> {
    void onClicked(@NonNull final T item);
}
