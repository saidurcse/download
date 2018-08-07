package cmed.com.download.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cmed.com.download.DownloadDataApplication;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String API_BASE_URL = "http://dropbox.sandbox2000.com/";
    private static int cacheSize = 10 * 1024 * 1024; // 10 MB

    private static Cache cache = new Cache(DownloadDataApplication.getInstance().getCacheDir(), cacheSize);

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cache(cache)
            .build();

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson));

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
