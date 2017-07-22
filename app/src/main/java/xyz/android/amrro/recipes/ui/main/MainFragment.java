package xyz.android.amrro.recipes.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.api.RecipesService;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    @Inject
    RecipesService api;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (api != null) {
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        AndroidSupportInjection.inject(this);
    }
}
