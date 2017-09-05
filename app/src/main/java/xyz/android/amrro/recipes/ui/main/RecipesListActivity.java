package xyz.android.amrro.recipes.ui.main;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import xyz.android.amrro.recipes.ConnectivityMonitor;
import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.common.Navigator;
import xyz.android.amrro.recipes.data.model.Recipe;
import xyz.android.amrro.recipes.databinding.ActivityRecipesListBinding;

public class RecipesListActivity extends LifecycleActivity {
    public static final int SPAN_COUNT = 1;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private Navigator navigator;
    private ActivityRecipesListBinding binding;
    private RecipesViewModel recipesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        navigator = new Navigator(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipes_list);
        binding.setNoConnection(true);
        binding.grid.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        recipesViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(RecipesViewModel.class);
        recipesViewModel.getRecipes()
                .observe(this, response -> {
                    if (response != null && response.isSuccessful()) {
                        binding.grid.setAdapter(
                                new RecipesAdapter(response.getData(), recipe -> navigator.toRecipeDetails(recipe)));
                    }
                });

        ConnectivityMonitor monitor = new ConnectivityMonitor(this);
        getLifecycle().addObserver(monitor);
        monitor.isConnected().observe(this, isConnected -> {
            //noinspection ConstantConditions
            if (isConnected) {
                binding.setNoConnection(false);
                recipesViewModel.retry();
            }
        });
    }

    interface OnRecipeClickedListener {
        void onRecipeClicked(@NonNull final Recipe recipe);
    }
}
