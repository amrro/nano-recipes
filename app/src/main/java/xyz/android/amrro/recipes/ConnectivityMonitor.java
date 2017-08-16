package xyz.android.amrro.recipes;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.support.annotation.NonNull;

import java.util.Objects;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 8/15/17.
 * <p>
 * This class handle connectivity and detect if available.
 */

public final class ConnectivityMonitor implements LifecycleObserver {

    private ConnectivityManager manager;
    private MutableLiveData<Boolean> isConnected = new MutableLiveData<>();
    private boolean monitoring = false;
    private ConnectivityManager.NetworkCallback connectivityCallback
            = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(Network network) {
            isConnected.postValue(true);
        }

        @Override
        public void onLost(Network network) {
            isConnected.postValue(false);
        }
    };

    public ConnectivityMonitor(@NonNull final Context context) {
        Objects.requireNonNull(context, "_> context cannot be null.");
        manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private void checkConnectivity() {
        final NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        final boolean connection = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if (connection) {
            isConnected.setValue(connection);
        } else {
            isConnected.setValue(false);
            manager.registerNetworkCallback(
                    new NetworkRequest.Builder()
                            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build(),
                    connectivityCallback
            );
            monitoring = true;
        }
    }

    @NonNull
    public MutableLiveData<Boolean> isConnected() {
        return isConnected;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        checkConnectivity();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        if (monitoring) {
            manager.unregisterNetworkCallback(connectivityCallback);
            monitoring = false;
        }
    }
}
