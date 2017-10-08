package xyz.android.amrro.recipes.ui.recipe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.Objects;

import xyz.android.amrro.recipes.ui.recipe.ingredient.IngredientsFragment;
import xyz.android.amrro.recipes.ui.recipe.step.StepsFragment;


public final class BakingPagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_ITEMS = 2;

    private final Integer recipeId;

    BakingPagerAdapter(FragmentManager fm, final Integer recipeId) {
        super(fm);
        this.recipeId = Objects.requireNonNull(recipeId);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return StepsFragment.newInstance(recipeId);
            case 1:
                return IngredientsFragment.newInstance(recipeId);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
