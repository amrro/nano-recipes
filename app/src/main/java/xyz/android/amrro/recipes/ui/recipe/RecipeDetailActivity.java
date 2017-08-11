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
import xyz.android.amrro.recipes.ui.steps.StepDetailActivity;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

/**
 * An activity representing a list of Steps. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link StepDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RecipeDetailActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    public static final String KEY_RECIPE_ID = "KEY_RECIPE_ID";
    public boolean mTwoPane;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ActivityRecipeDetailBinding binding;
    private Integer recipeId;
    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail);

        if (getIntent() != null) {
            recipeId = getIntent().getIntExtra(KEY_RECIPE_ID, -1);
        }

        if (recipeId.equals(-1)) {
            throw new IllegalArgumentException("Recipe id cannot be -1");
        }

        // TODO: 8/11/17 user later to update title
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle(getTitle());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mTwoPane = binding.include.stepDetailContainer != null;

        ViewModelProviders.of(this, viewModelFactory)
                .get(SingleRecipeViewModel.class)
                .setId(recipeId)
                .recipe().observe(this, recipe -> setupRecyclerView(recipe.getSteps()));
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
