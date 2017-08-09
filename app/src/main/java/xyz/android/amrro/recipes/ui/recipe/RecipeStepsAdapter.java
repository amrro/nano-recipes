package xyz.android.amrro.recipes.ui.recipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.ui.recipe.dummy.DummyContent;
import xyz.android.amrro.recipes.ui.steps.StepsActivity;
import xyz.android.amrro.recipes.ui.steps.StepsFragment;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 8/9/17.
 */
public class RecipeStepsAdapter
        extends RecyclerView.Adapter<RecipeStepsAdapter.ViewHolder> {

    private final List<DummyContent.DummyItem> mValues;
    private RecipeActivity recipeActivity;

    public RecipeStepsAdapter(RecipeActivity recipeActivity, List<DummyContent.DummyItem> items) {
        this.recipeActivity = recipeActivity;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recipeActivity.mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(StepsFragment.ARG_ITEM_ID, holder.mItem.id);
                    StepsFragment fragment = new StepsFragment();
                    fragment.setArguments(arguments);
                    recipeActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.step_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, StepsActivity.class);
                    intent.putExtra(StepsFragment.ARG_ITEM_ID, holder.mItem.id);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
