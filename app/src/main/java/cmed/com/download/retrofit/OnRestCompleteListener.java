package cmed.com.download.retrofit;


import cmed.com.download.retrofit.model.ResponseType;

public interface OnRestCompleteListener<T> {

    void onComplete(ResponseType responseType, T result, String message);
}
