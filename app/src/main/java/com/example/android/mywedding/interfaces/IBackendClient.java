package com.example.android.mywedding.interfaces;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IBackendClient {

    @GET("beta/users")
    Call<ResponseBody> getUsersNumber();
}
