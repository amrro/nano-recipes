package xyz.android.amrro.recipes.ui.recipe;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.common.BaseActivity;
import xyz.android.amrro.recipes.databinding.BakingBinding;

public class BakingActivity extends BaseActivity {
    private BakingBinding binding;
    private int currentItem;
    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_baking);
        setSupportActionBar(binding.toolbar);
        setHomeEnabled(true);
        // is on tablet?
        isTwoPane = binding.stepDetailContainer != null;

        BakingPagerAdapter adapter = new BakingPagerAdapter(getSupportFragmentManager(), itemId(), isTwoPane);
        binding.content.pager.setAdapter(adapter);
        setUpTabs();

    }

    private void setUpTabs() {
        binding.content.bottomBar.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_ingredient:
                    currentItem = 0;
                    break;

                case R.id.nav_steps:
                    currentItem = 1;
                    break;

            }
            binding.content.pager.setCurrentItem(currentItem);
            return true;
        });

        binding.content.pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                binding.content.bottomBar.getMenu().getItem(currentItem).setChecked(true);
            }
        });
    }


}
