package xyz.android.amrro.recipes.ui.recipe.step;

import android.os.Bundle;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.common.Navigator;
import xyz.android.amrro.recipes.common.RecyclerFragment;
import xyz.android.amrro.recipes.data.model.Step;
import xyz.android.amrro.recipes.ui.recipe.SingleRecipeViewModel;
import xyz.android.amrro.recipes.ui.steps.StepDetailFragment;

public class StepsFragment extends RecyclerFragment<Step, StepsAdapter> {
    private int recipeId;

    public StepsFragment() {
        // Required empty public constructor
    }

    /**
     * @param id recipe id.
     * @return A new instance of fragment StepsFragment.
     */
    public static StepsFragment newInstance(final Integer id) {
        StepsFragment fragment = new StepsFragment();
        Bundle args = new Bundle();
        args.putInt(Navigator.KEY_ITEM_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected StepsAdapter createAdapter() {
        return new StepsAdapter(step -> {
            boolean isTablet = getResources().getBoolean(R.bool.is_tablet);

            if (isTablet) {
                StepDetailFragment fragment = StepDetailFragment.newInstance(recipeId, step.id);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.step_detail_container, fragment)
                        .commit();
            } else {
                navigator.toSteps(recipeId, step);
            }
        });
    }

    @Override
    protected void updateList() {
        if (getArguments() != null) {
            recipeId = getArguments().getInt(Navigator.KEY_ITEM_ID);
        }

        SingleRecipeViewModel model = getViewModel(SingleRecipeViewModel.class);
        model.setId(recipeId).recipe().observe(this, recipe1 -> {
            if (recipe1 != null) {
                updateAdapter(recipe1.steps);
            }
        });
    }

}
