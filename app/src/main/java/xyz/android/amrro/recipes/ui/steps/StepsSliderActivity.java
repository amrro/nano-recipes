package xyz.android.amrro.recipes.ui.steps;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.common.BaseActivity;
import xyz.android.amrro.recipes.ui.recipe.BakingActivity;

/**
 * An activity representing a single Step detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link BakingActivity}.
 */
public class StepsSliderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_slider);

        if (savedInstanceState == null) {
            StepDetailFragment fragment = StepDetailFragment.newInstance(
                    getIntent().getIntExtra(StepDetailFragment.ARG_RECIPE_ID, - 1),
                    getIntent().getIntExtra(StepDetailFragment.ARG_STEP_ID, - 1)
            );
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            navigateUpTo(new Intent(this, BakingActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
