package xyz.android.amrro.recipes.ui.steps;

import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.model.Step;
import xyz.android.amrro.recipes.ui.recipe.RecipeDetailActivity;

/**
 * A fragment representing a single Step detail screen.
 * This fragment is either contained in a {@link RecipeDetailActivity}
 * in two-pane mode (on tablets) or a {@link StepDetailActivity}
 * on handsets.
 */
public class StepDetailFragment extends LifecycleFragment {

    public static final String ARG_STEP_ID = "item_id";
    public static final String ARG_RECIPE_ID = "recipe_id";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public StepDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.step_detail, container, false);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments().containsKey(ARG_STEP_ID) && getArguments().containsKey(ARG_RECIPE_ID)) {
            ViewModelProviders.of(this, viewModelFactory).get(StepsViewModel.class)
                    .setId(getArguments().getInt(ARG_RECIPE_ID))
                    .step(getArguments().getInt(ARG_STEP_ID)).observe(this, this::updateUI);
        }
    }

    private void updateUI(final Step step) {
        if (step != null) {
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(step.getShortDescription());
            }
        }
    }
}
