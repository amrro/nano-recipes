package xyz.android.amrro.recipes.ui.recipe.step;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.common.DataListAdapter;
import xyz.android.amrro.recipes.common.OnItemClickedListener;
import xyz.android.amrro.recipes.data.model.Step;
import xyz.android.amrro.recipes.databinding.CardStepBinding;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 8/9/17.
 * to list steps of some recipe.
 */
public class StepsAdapter extends DataListAdapter<Step, CardStepBinding> {

    StepsAdapter(OnItemClickedListener<Step> listener) {
        super(listener);
    }

    @Override
    protected CardStepBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        final CardStepBinding binding = DataBindingUtil.inflate(inflater, R.layout.card_step, parent, false);
        binding.getRoot().setOnClickListener(view -> {
            final Step step = binding.getStep();
            if (step != null) listener.onClicked(step);
        });
        return binding;
    }

    @Override
    protected void bind(CardStepBinding binding, Step item) {
        binding.setStep(item);
    }

}
