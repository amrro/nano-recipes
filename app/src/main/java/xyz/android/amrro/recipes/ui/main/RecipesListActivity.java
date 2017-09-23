package xyz.android.amrro.recipes.ui.main;

import xyz.android.amrro.recipes.common.SimpleRecyclerActivity;
import xyz.android.amrro.recipes.data.model.Recipe;

public class RecipesListActivity extends SimpleRecyclerActivity<Recipe, RecipesAdapter> {

    private RecipesViewModel recipes;

    @Override
    protected RecipesAdapter createAdapter() {
        return new RecipesAdapter(recipe -> navigator.toRecipeDetails(recipe));
    }

    @Override
    protected void createList() {
        recipes = getViewModel(RecipesViewModel.class);
        recipes.getRecipes().observe(this, response -> {
            if (response != null && response.isSuccessful())
                updateAdapter(response.getData());
        });
    }


   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        navigator = new Navigator(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipes_list);
        binding.setNoConnection(true);
        binding.grid.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.)));
        recipesViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(RecipesViewModel.class);
        recipesViewModel.getRecipes()
                .observe(this, (ApiResponse<List<Recipe>> response) -> {
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
    }*/
}
