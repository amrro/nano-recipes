package xyz.android.amrro.recipes.ui.steps;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import javax.inject.Inject;

import xyz.android.amrro.recipes.RecipeVideoPlayer;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.model.Step;
import xyz.android.amrro.recipes.databinding.FragmentStepDetailsBinding;

public class StepDetailsFragment extends LifecycleFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private Step step;
    private FragmentStepDetailsBinding binding;
    private RecipeVideoPlayer player;

    public StepDetailsFragment() {
        // Required empty public constructor
    }

    public static StepDetailsFragment newInstance(@NonNull final Step step) {
        Objects.requireNonNull(step, "step cannot be null.");
        StepDetailsFragment fragment = new StepDetailsFragment();
        fragment.step = step;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_details, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setStep(step);
        // TODO: 8/13/17 save to instant state for rotation
        player = new RecipeVideoPlayer(getContext(), step.getVideoURL(), binding.playerView);
        getLifecycle().addObserver(player);

    }
}
