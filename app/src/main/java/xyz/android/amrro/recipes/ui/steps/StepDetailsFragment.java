package xyz.android.amrro.recipes.ui.steps;


import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import javax.inject.Inject;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.model.Step;
import xyz.android.amrro.recipes.databinding.FragmentStepDetailsBinding;

public class StepDetailsFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private Step step;
    private FragmentStepDetailsBinding binding;

    public StepDetailsFragment() {
        // Required empty public constructor
    }

    public static StepDetailsFragment newInstance(@NonNull final Step step/*@NonNull final String recipeId, @NonNull final String stepId*/) {
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
    }


    /*@SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewModelProviders.of(this, viewModelFactory).get(StepsViewModel.class)
                .step(getArguments().getInt(ASI))
    }*/
}
