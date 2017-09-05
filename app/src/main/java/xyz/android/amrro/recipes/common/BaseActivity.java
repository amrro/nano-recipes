package xyz.android.amrro.recipes.common;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import java.util.Objects;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 8/29/17.
 * <p>
 * Holds the common properties of all {@link android.app.Activity}s.
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private final LifecycleRegistry registry = new LifecycleRegistry(this);
    @Inject
    protected ViewModelProvider.Factory viewModelFactory;
    protected Navigator navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        navigator = new Navigator(this);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return registry;
    }

    protected void notify(@NonNull final String message) {
        Objects.requireNonNull(message, "message cannot be null");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void notifyNoData() {
        notify("No Data Avaliable. Check later.");
    }

    protected <T extends ViewModel> T getViewModel(final Class<T> cls) {
        return ViewModelProviders.of(this, viewModelFactory).get(cls);
    }

    protected void setActionBarTitle(@NonNull final String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Objects.requireNonNull(title));
        }
    }

    protected void setHomeEnabled(@NonNull final Boolean showUp) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void dismissKeyboard(IBinder windowToken) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(windowToken, 0);
    }

    public Integer itemId() {
        return getIntent().getIntExtra(Navigator.KEY_ITEM_ID, - 1);
    }

    /**
     * used with {@link android.support.v7.widget.RecyclerView.Adapter}s to return the data of clicked item.
     *
     * @param <T>
     */
    public interface OnItemClickedListener<T> {
        void onClicked(@NonNull final T item);
    }
}
