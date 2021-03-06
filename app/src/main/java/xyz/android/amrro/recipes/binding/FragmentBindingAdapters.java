package xyz.android.amrro.recipes.binding;

import android.databinding.BindingAdapter;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

/**
 * Binding adapters that work with a fragment instance.
 */
public class FragmentBindingAdapters {
    private final Fragment fragment;

    @Inject
    public FragmentBindingAdapters(Fragment fragment) {
        this.fragment = fragment;
    }

    @BindingAdapter("imageUrl")
    public void bindImage(ImageView imageView, String url) {
        if (url != null && ! TextUtils.isEmpty(url.trim())) {
            Glide.with(fragment)
                    .load(url)
                    .into(imageView);
        }
    }
}
