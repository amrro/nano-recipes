package xyz.android.amrro.recipes.ui.steps;

import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.RecipeVideoPlayer;
import xyz.android.amrro.recipes.data.model.Step;
import xyz.android.amrro.recipes.databinding.FragmentStepDetailBinding;
import xyz.android.amrro.recipes.ui.recipe.RecipeDetailActivity;

/**
 * A fragment representing a single Step detail screen.
 * This fragment is either contained in a {@link RecipeDetailActivity}
 * in two-pane mode (on tablets) or a {@link StepsSliderActivity}
 * on handsets.
 */
public class StepDetailFragment extends LifecycleFragment {

    public static final String ARG_STEP_ID = "item_id";
    public static final String ARG_RECIPE_ID = "recipe_id";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private FragmentStepDetailBinding binding;
    private PagerAdapter adapter;
    private StepViewModel stepViewModel;

    private Integer recipeId;
    private Integer stepId;

    public StepDetailFragment() {
    }

    public static StepDetailFragment newInstance(@NonNull final Integer recipeId,
                                                 @NonNull final Integer stepId) {
        Bundle args = new Bundle();
        args.putInt(ARG_RECIPE_ID, recipeId);
        args.putInt(ARG_STEP_ID, stepId);
        StepDetailFragment fragment = new StepDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_detail, container, false);
        return binding.getRoot();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments().containsKey(ARG_STEP_ID) && getArguments().containsKey(ARG_RECIPE_ID)) {
            recipeId = getArguments().getInt(ARG_RECIPE_ID);
            stepId = getArguments().getInt(ARG_STEP_ID);
            stepViewModel = ViewModelProviders.of(this, viewModelFactory).get(StepViewModel.class)
                    .setRecipeId(recipeId);
            stepViewModel.step(stepId).observe(this, this::updateUI);
            stepViewModel.hasNext(stepId).observe(this, hasNext -> binding.setHasNext(hasNext));

            stepViewModel.hasPrevious(stepId).observe(this, hasPrevious -> binding.setHasPrev(hasPrevious));
        }
    }

    private void updateUI(@NonNull final Step step) {
        // TODO: 8/14/17 handle nullity
        binding.setStep(step);
        if (!TextUtils.isEmpty(step.getVideoURL())) {
            binding.setNoVideo(false);
            final RecipeVideoPlayer player = new RecipeVideoPlayer(
                    getContext(),
                    step.getVideoURL(),
                    binding.playerView
            );
            getLifecycle().addObserver(player);
        }
        binding.setNoVideo(TextUtils.isEmpty(step.getVideoURL()));
    }
}
