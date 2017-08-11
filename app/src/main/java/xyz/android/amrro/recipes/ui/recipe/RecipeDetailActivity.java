package xyz.android.amrro.recipes.ui.recipe;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.model.Step;
import xyz.android.amrro.recipes.databinding.ActivityRecipeDetailBinding;
import xyz.android.amrro.recipes.ui.steps.StepsSliderActivity;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

/**
 * An activity representing a list of Steps. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link StepsSliderActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RecipeDetailActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    public static final String KEY_RECIPE_ID = "KEY_RECIPE_ID";
    public static String KEY_RECIPE_NAME = "KEY_RECIPE_NAME";
    public boolean mTwoPane;
    public Integer recipeId;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ActivityRecipeDetailBinding binding;
    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private SingleRecipeViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail);

        setSupportActionBar(binding.toolbar);
        if (getIntent() != null) {
            recipeId = getIntent().getIntExtra(KEY_RECIPE_ID, -1);
            //noinspection ConstantConditions
            getSupportActionBar().setTitle(getIntent().getStringExtra(KEY_RECIPE_NAME));
        }


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mTwoPane = binding.include.stepDetailContainer != null;

        //noinspection ConstantConditions
        model = ViewModelProviders.of(this, viewModelFactory)
                .get(SingleRecipeViewModel.class)
                .setId(recipeId);
        model.recipe().observe(this, recipe -> {
            if (recipe == null) {
                model.retry();
            } else {
                setupRecyclerView(recipe.getSteps());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(final List<Step> steps) {
        binding.include.stepList.setAdapter(new RecipeStepsAdapter(this, steps));
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
