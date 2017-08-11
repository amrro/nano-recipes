package xyz.android.amrro.recipes.ui.recipe;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.data.model.Step;
import xyz.android.amrro.recipes.databinding.StepListContentBinding;
import xyz.android.amrro.recipes.ui.steps.StepDetailActivity;
import xyz.android.amrro.recipes.ui.steps.StepDetailFragment;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 8/9/17.
 * <p>
 * to list steps of some recipe.
 */
public class RecipeStepsAdapter
        extends RecyclerView.Adapter<RecipeStepsAdapter.ViewHolder> {

    private final List<Step> values;
    private RecipeDetailActivity recipeDetailActivity;

    RecipeStepsAdapter(RecipeDetailActivity recipeDetailActivity, List<Step> steps) {
        this.recipeDetailActivity = recipeDetailActivity;
        values = steps;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final StepListContentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.step_list_content, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Step step = values.get(position);
        holder.binding.setStep(step);

        holder.binding.getRoot().setOnClickListener(view -> {
            if (recipeDetailActivity.mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putInt(StepDetailFragment.ARG_ITEM_ID, holder.binding.getStep().getId());
                StepDetailFragment fragment = new StepDetailFragment();
                fragment.setArguments(arguments);
                recipeDetailActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.step_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, StepDetailActivity.class);
                intent.putExtra(StepDetailFragment.ARG_ITEM_ID, holder.binding.getStep().getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values == null ? 0 : values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final StepListContentBinding binding;

        ViewHolder(StepListContentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + binding.stepName.getText().toString() + "'";
        }
    }
}
