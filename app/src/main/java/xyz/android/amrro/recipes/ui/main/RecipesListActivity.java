package xyz.android.amrro.recipes.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.api.RecipesService;

public class RecipesListActivity extends AppCompatActivity {

    @Inject
    RecipesService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);

        service.recipes();
    }
}
