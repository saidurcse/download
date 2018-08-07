package cmed.com.download;


import android.app.Application;

public class DownloadDataApplication extends Application{

    private static DownloadDataApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

    }

    public static DownloadDataApplication getInstance() {
        return instance;
    }
}
