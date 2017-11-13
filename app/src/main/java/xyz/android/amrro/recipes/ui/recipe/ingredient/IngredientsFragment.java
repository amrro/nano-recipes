package xyz.android.amrro.recipes.ui.recipe.ingredient;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import xyz.android.amrro.recipes.common.Navigator;
import xyz.android.amrro.recipes.common.RecyclerFragment;
import xyz.android.amrro.recipes.data.db.IngredientsContentProvider;
import xyz.android.amrro.recipes.data.model.Ingredient;
import xyz.android.amrro.recipes.ui.recipe.SingleRecipeViewModel;
import xyz.android.amrro.recipes.ui.widget.WidgetUpdateService;

public class IngredientsFragment extends RecyclerFragment<Ingredient, IngredientsAdapter> {
    @Inject
    SharedPreferences prefs;
    @Inject
    Gson gson;
    private int recipeId;
    private List<Ingredient> ingredientsList;



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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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
                ingredientsList = ingredients;
                updateAdapter(ingredients);


                updateWidget(recipeId, ingredientsList);


            }
        });
    }

    @Override
    protected void setUpRecyclerView() {
        super.setUpRecyclerView();
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        getRecyclerView().addItemDecoration(decoration);
    }

    private void updateWidget(final int recipeId, final List<Ingredient> ingredients) {
        // delete cached data first.
        getContext().getContentResolver()
                .delete(IngredientsContentProvider.URI_INGREDIENT, null, null);
        // cache data.
        cacheIngredients(ingredients);
    }

    private void cacheIngredients(List<Ingredient> ingredients) {
        AsyncTask.execute(() -> {
            final int count = getContext()
                    .getContentResolver()
                    .bulkInsert(IngredientsContentProvider.URI_INGREDIENT, Ingredient.toContentValues(ingredients));

            if (count != ingredients.size()) {
                throw new IllegalStateException("Inserted values doesn't match");
            }

           /* Intent intent = new Intent();
            intent.setAction(IngredientsRemoteViewsFactory.APP_UPDATE_WIDGET);
            intent.putExtra(IngredientsRemoteViewsFactory.KEY_RECIPE_ID, recipeId);
            getActivity().sendBroadcast(intent);*/
            WidgetUpdateService.startActionUpdateWidgets(getContext());
        });
    }
}
