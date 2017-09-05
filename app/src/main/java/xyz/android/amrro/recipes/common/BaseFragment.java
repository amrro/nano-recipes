package xyz.android.amrro.recipes.common;

import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 8/29/17.
 * Holds the common behaviour and functions of fragments.
 */

public class BaseFragment extends LifecycleFragment {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;
    protected Navigator navigator;

    public BaseFragment() {
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
        navigator = new Navigator(activity);
    }

    protected void dismissKeyboard(IBinder windowToken) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(windowToken, 0);
        }
    }

    protected void notify(@NonNull final String message) {
        Objects.requireNonNull(message, "message cannot be null");
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
