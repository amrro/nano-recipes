package xyz.android.amrro.recipes.ui.steps;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.RecipeVideoPlayer;
import xyz.android.amrro.recipes.binding.FragmentDataBindingComponent;
import xyz.android.amrro.recipes.common.BaseFragment;
import xyz.android.amrro.recipes.data.model.Step;
import xyz.android.amrro.recipes.databinding.FragmentStepDetailBinding;

/**
 * A fragment representing a single Step detail screen.
 */
public class StepDetailFragment extends BaseFragment {

    public static final String ARG_STEP_ID = "item_id";
    public static final String ARG_RECIPE_ID = "recipe_id";
    public static final String KEY_LAST_POSITION = "last_position";

    DataBindingComponent component = new FragmentDataBindingComponent(this);

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private FragmentStepDetailBinding binding;

    private RecipeVideoPlayer player;
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
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_step_detail,
                container,
                false,
                component
        );
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
    public void onActivityCreated(@Nullable Bundle savedState) {
        super.onActivityCreated(savedState);
        if (getArguments().containsKey(ARG_STEP_ID) && getArguments().containsKey(ARG_RECIPE_ID)) {
            recipeId = getArguments().getInt(ARG_RECIPE_ID);
            stepId = getArguments().getInt(ARG_STEP_ID);
            final StepViewModel stepViewModel = ViewModelProviders.of(this, viewModelFactory).get(StepViewModel.class)
                    .setRecipeId(recipeId);
            stepViewModel.step(stepId).observe(this, step -> {
                final long position = savedState != null ? savedState.getLong(KEY_LAST_POSITION) : 0;
                updateUI(step, position);
            });
            stepViewModel.hasNext(stepId).observe(this, hasNext -> {
                binding.setHasNext(hasNext);
                if (hasNext) {
                    binding.next.setOnClickListener(view -> replaceFragment(stepId + 1));
                }
            });
            stepViewModel.hasPrevious(stepId).observe(this, hasPrevious -> {
                binding.setHasPrev(hasPrevious);
                if (hasPrevious) {
                    binding.previous.setOnClickListener(view -> replaceFragment(stepId - 1));
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (player != null) {
            outState.putLong(KEY_LAST_POSITION, player.getLastPosition());
        }
        super.onSaveInstanceState(outState);
    }

    private void replaceFragment(@NonNull final Integer newId) {
        final StepDetailFragment nextStepFragment = newInstance(recipeId, newId);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.step_detail_container, nextStepFragment)
                .commit();
    }

    private void updateUI(@NonNull final Step step, final long lastPosition) {
        binding.setStep(step);
        if (! TextUtils.isEmpty(step.videoURL)) {
            binding.setNoVideo(false);
            player = new RecipeVideoPlayer(
                    getContext(),
                    step.videoURL,
                    binding.playerView, lastPosition
            );
            getLifecycle().addObserver(player);
        }
        binding.setNoVideo(TextUtils.isEmpty(step.videoURL));
    }
}
