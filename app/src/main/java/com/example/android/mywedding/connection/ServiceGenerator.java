package com.example.android.mywedding.connection;


import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceGenerator {

    private static final String TAG = "STEP";
    private static final int TIMEOUT_VALUE = 10;
    private static final TimeUnit TIMEOUT_UNIT = TimeUnit.SECONDS;
    private OkHttpClient httpClientBuilder;


    public ServiceGenerator() {
        httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_VALUE, TIMEOUT_UNIT)
                .readTimeout(TIMEOUT_VALUE, TIMEOUT_UNIT)
                .writeTimeout(TIMEOUT_VALUE, TIMEOUT_UNIT)
                /*.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request.Builder requestBuilder = original.newBuilder()
                                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                .addHeader("Authorization", "AKIAJU2IJ3WCJTG4HDYA")
                                .addHeader("Credential", "Credential=AKIAJU2IJ3WCJTG4HDYA/20170418/eu-west-1/execute-api/aws4_request")
                                .addHeader("SignedHeaders", "content-type;host;x-amz-date")
                                .addHeader("Signature", "11828357069165ae5cd76c39e960f2279937f88282769c12c87450bea6016dbe");
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })*/
                .build();
    }


/*
    public ServiceGenerator(Integer connectTimeout) {
        connectTimeout = (connectTimeout == null) ? TIMEOUT_VALUE : connectTimeout;
        httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TIMEOUT_UNIT)
                .readTimeout(connectTimeout,  TIMEOUT_UNIT)
                .writeTimeout(connectTimeout,  TIMEOUT_UNIT)
                .build();
    }*/


    public <S> S createService(Class<S> serviceClass) {
        Log.d(TAG, this.getClass().toString() + "::createService");
        String baseUrl = "https://hp6z6g7my6.execute-api.eu-west-1.amazonaws.com/";

        Log.d(TAG, "url: " + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder)
                .build();
        return retrofit.create(serviceClass);
    }
}
