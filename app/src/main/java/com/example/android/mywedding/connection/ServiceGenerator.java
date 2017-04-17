package com.example.android.mywedding.connection;


import android.util.Log;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String TAG = "STEP";
    private static final int TIMEOUT_VALUE = 30;
    private static final TimeUnit TIMEOUT_UNIT = TimeUnit.SECONDS;
    private OkHttpClient httpClientBuilder;

    public ServiceGenerator() {
        httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_VALUE, TIMEOUT_UNIT)
                .readTimeout(TIMEOUT_VALUE, TIMEOUT_UNIT)
                .writeTimeout(TIMEOUT_VALUE, TIMEOUT_UNIT)
                .build();
    }

    public ServiceGenerator(Integer connectTimeout) {
        connectTimeout = (connectTimeout == null) ? TIMEOUT_VALUE : connectTimeout;
        httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TIMEOUT_UNIT)
                .readTimeout(connectTimeout,  TIMEOUT_UNIT)
                .writeTimeout(connectTimeout,  TIMEOUT_UNIT)
                .build();
    }

    //TODO finalize this function
    public <S> S createService(Class<S> serviceClass, String url, String service) {
        Log.d(TAG, this.getClass().toString() + "::createService::url: " + url + " | service: " + service);
        String baseUrl;
        switch (service) {
            case "Algo":
                baseUrl = "path";
                break;
            default:
                throw new IllegalArgumentException("Please specify a service");
        }
        Log.d(TAG, "url: " + url + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url + baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder)
                .build();
        return retrofit.create(serviceClass);
    }
}
