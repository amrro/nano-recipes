package xyz.android.amrro.recipes.ui.recipe;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.databinding.BakingBinding;

public class BakingActivity extends AppCompatActivity {
    private BakingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_baking);
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
