package xyz.android.amrro.recipes.ui.recipe.ingredient;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;

import xyz.android.amrro.recipes.common.Navigator;
import xyz.android.amrro.recipes.common.RecyclerFragment;
import xyz.android.amrro.recipes.data.model.Ingredient;
import xyz.android.amrro.recipes.ui.recipe.SingleRecipeViewModel;

public class IngredientsFragment extends RecyclerFragment<Ingredient, IngredientsAdapter> {
    private int recipeId;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    /**
     * @param id recipeId.
     * @return A new instance of fragment IngredientsFragment.
     */
    public static IngredientsFragment newInstance(final Integer id) {
        IngredientsFragment fragment = new IngredientsFragment();
        Bundle args = new Bundle();
        args.putInt(Navigator.KEY_ITEM_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected IngredientsAdapter createAdapter() {
        return new IngredientsAdapter(null);
    }

    @Override
    protected void updateList() {
        if (getArguments() != null) {
            recipeId = getArguments().getInt(Navigator.KEY_ITEM_ID);
        }

        final SingleRecipeViewModel model = getViewModel(SingleRecipeViewModel.class);
        model.setId(recipeId).ingredients().observe(this, ingredients -> {
            if (ingredients != null) {
                updateAdapter(ingredients);
            }
        });
    }

    @Override
    protected void setUpRecyclerView() {
        super.setUpRecyclerView();

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        getRecyclerView().addItemDecoration(decoration);
    }
}
