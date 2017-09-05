package xyz.android.amrro.recipes.common;

import android.content.Context;

import java.util.Objects;


final class Navigator {
    private final Context context;

    Navigator(final Context context) {
        this.context = Objects.requireNonNull(context, "context cannot be null.");
    }
}
