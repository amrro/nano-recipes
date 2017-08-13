package xyz.android.amrro.recipes.ui.steps;

import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.model.Step;
import xyz.android.amrro.recipes.databinding.FragmentStepsSliderBinding;
import xyz.android.amrro.recipes.ui.recipe.RecipeDetailActivity;

/**
 * A fragment representing a single Step detail screen.
 * This fragment is either contained in a {@link RecipeDetailActivity}
 * in two-pane mode (on tablets) or a {@link StepsSliderActivity}
 * on handsets.
 */
public class StepsSliderFragment extends LifecycleFragment {

    public static final String ARG_STEP_ID = "item_id";
    public static final String ARG_RECIPE_ID = "recipe_id";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private FragmentStepsSliderBinding binding;
    private PagerAdapter adapter;

    public StepsSliderFragment() {
    }

    public static StepsSliderFragment newInstance(@NonNull final Integer recipeId,
                                                  @NonNull final Integer stepId) {
        Bundle args = new Bundle();
        args.putInt(ARG_RECIPE_ID, recipeId);
        args.putInt(ARG_STEP_ID, stepId);
        StepsSliderFragment fragment = new StepsSliderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_steps_slider, container, false);
        return binding.getRoot();
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
                    .steps().observe(this, this::updateUI);
        }
    }

    private void updateUI(final List<Step> steps) {
        adapter = new StepsPagerAdapter(getFragmentManager(), steps);
        binding.pager.setAdapter(adapter);
        binding.pager.setCurrentItem(getArguments().getInt(ARG_STEP_ID));
    }

    public class StepsPagerAdapter extends FragmentPagerAdapter {
        private List<Step> steps;

        StepsPagerAdapter(FragmentManager fm, @NonNull final List<Step> steps) {
            super(fm);
            Objects.requireNonNull(steps);
            this.steps = steps;
        }

        @Override
        public Fragment getItem(int position) {
            try {
                return StepDetailsFragment.newInstance(steps.get(position));
            } catch (IndexOutOfBoundsException out) {
                return null;
            }
        }

        @Override
        public int getCount() {
            return steps == null ? 0 : steps.size();
        }
    }

}
