package xyz.android.amrro.recipes.ui.recipe;

import xyz.android.amrro.recipes.common.SimpleRecyclerActivity;
import xyz.android.amrro.recipes.data.model.Step;

public class RecipeDetailActivity extends SimpleRecyclerActivity<Step, StepsAdapter> {

    @Override
    protected StepsAdapter createAdapter() {
        return new StepsAdapter(step -> navigator.toSteps(itemId(), step));
    }

    @Override
    protected void createList() {
        setHomeEnabled(true);
        final SingleRecipeViewModel model = getViewModel(SingleRecipeViewModel.class).setId(itemId());
        model.recipe().observe(this, recipe -> {
            if (recipe != null) updateAdapter(recipe.steps);
        });
    }
}
