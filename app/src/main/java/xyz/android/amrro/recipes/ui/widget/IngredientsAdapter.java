package xyz.android.amrro.recipes.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.model.Ingredient;
import xyz.android.amrro.recipes.databinding.IngredientsItemBinding;


public final class IngredientsAdapter extends ArrayAdapter {

    @NonNull
    private Context context;

    @NonNull
    private List<Ingredient> ingredients;

    public IngredientsAdapter(@NonNull Context context, @NonNull List<Ingredient> ingredients) {
        super(context, - 1, ingredients);
        this.context = context;
        this.ingredients = ingredients;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final IngredientsItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.ingredients_item, parent, false);
        binding.setIngredient(ingredients.get(position));
        return binding.getRoot();
    }
}
