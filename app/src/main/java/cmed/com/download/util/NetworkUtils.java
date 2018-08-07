package cmed.com.download.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import cmed.com.download.DownloadDataApplication;

public class NetworkUtils {

    private NetworkUtils() {}

    public static boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) DownloadDataApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}
