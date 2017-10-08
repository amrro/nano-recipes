package xyz.android.amrro.recipes.ui.recipe.ingredient;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.common.DataListAdapter;
import xyz.android.amrro.recipes.common.OnItemClickedListener;
import xyz.android.amrro.recipes.data.model.Ingredient;
import xyz.android.amrro.recipes.databinding.CardIngredientBinding;

public final class IngredientsAdapter extends DataListAdapter<Ingredient, CardIngredientBinding> {

    IngredientsAdapter(OnItemClickedListener<Ingredient> listener) {
        super(listener);
    }

    @Override
    protected CardIngredientBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        return DataBindingUtil.inflate(inflater, R.layout.card_ingredient, parent, false);
    }

    @Override
    protected void bind(CardIngredientBinding binding, Ingredient item) {
        binding.setIngredient(item);
    }
}
