package com.example.android.mywedding.interfaces;


import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IBackendClient {

    @POST("alive")
    Call<ResponseBody> alive(@Body JsonObject body);
}
