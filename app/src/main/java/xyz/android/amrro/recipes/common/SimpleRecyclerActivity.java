package xyz.android.amrro.recipes.common;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import java.util.List;
import java.util.Objects;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.databinding.ActivityListBinding;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

/**
 * Base class for simple activities that have only Linear {@link android.support.v7.widget.RecyclerView}
 *
 * @param <M> the model class
 * @param <T> adapter.
 */
@SuppressWarnings("unchecked")
public abstract class SimpleRecyclerActivity<M, T extends DataListAdapter> extends BaseActivity {

    public boolean twoPane;
    private ActivityListBinding binding;
    private T adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        setSupportActionBar(binding.toolbar);

        final String title = getIntent().getStringExtra(Navigator.KEY_ITEM_TITLE);
        if (title != null) setActionBarTitle(title);
        twoPane = binding.container != null;
        setUpRecycler();
        createList();
        setLoading(true);
    }

    private void setUpRecycler() {
        adapter = Objects.requireNonNull(createAdapter(), "adapter cannot be null.");
        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
    }

    protected void updateAdapter(@NonNull final List<M> newData) {
        Objects.requireNonNull(newData, "replacing Adapter items with null cannot be don.");
        if (adapter != null) {
            if (newData.size() != 0) {
                this.adapter.replace(newData);
                setLoading(false);
            } else {
                toast("No Data Available. Check later.");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract T createAdapter();

    protected abstract void createList();

    protected void setLoading(final Boolean isLoading) {
        binding.setLoading(isLoading);
    }

    protected void setNoData(final Boolean noData) {

    }
}
