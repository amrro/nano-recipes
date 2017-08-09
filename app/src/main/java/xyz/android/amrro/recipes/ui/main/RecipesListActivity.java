package xyz.android.amrro.recipes.ui.main;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;

import dagger.android.AndroidInjection;
import xyz.android.amrro.recipes.R;

public class RecipesListActivity extends LifecycleActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);
    }
}
