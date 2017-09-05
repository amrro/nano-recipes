package xyz.android.amrro.recipes.common;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.databinding.ActivityListBinding;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 9/1/17.
 * The mother of all {@link RecyclerView}s activities.
 */
public abstract class SimpleRecyclerActivity<T extends DataListAdapter, M> extends BaseActivity {

    private ActivityListBinding binding;
    private T adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        setUpRecycler();
        setLoading(true);
    }

    @SuppressWarnings("unchecked")
    private void setUpRecycler() {
        adapter = Objects.requireNonNull(createAdapter(), "adapter cannot be null.");
        adapter.replace(createList());
        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
    }

    protected abstract T createAdapter();

    protected abstract List<M> createList();

    protected void setLoading(final Boolean isLoading) {
        binding.setLoading(isLoading);
    }

    protected void setNoData(final Boolean noData) {

    }
}
