package xyz.android.amrro.recipes.ui.recipe.step;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.android.amrro.recipes.R;
import xyz.android.amrro.recipes.common.Navigator;

public class StepsFragment extends Fragment {

    private int id;

    public StepsFragment() {
        // Required empty public constructor
    }

    /**
     * @param id recipe id.
     * @return A new instance of fragment StepsFragment.
     */
    public static StepsFragment newInstance(final Integer id) {
        StepsFragment fragment = new StepsFragment();
        Bundle args = new Bundle();
        args.putInt(Navigator.KEY_ITEM_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(Navigator.KEY_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_steps, container, false);
    }

}
