package com.example.android.mywedding.connection;


import android.util.Log;
import com.example.android.mywedding.interfaces.IBackendClient;
import com.example.android.mywedding.interfaces.IBackendClientAux;
import com.example.android.mywedding.util.Helper;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class BackendService {

    private static final String TAG = "STEP";
    private static String NULL_RESPONSE = "Response is null";

    private IBackendClientAux mIBackendClient;

    public BackendService(IBackendClientAux iBackendClientAux){
        mIBackendClient = iBackendClientAux;
    }

    public Call<ResponseBody> getUsersNumber(){
        Log.d(TAG, this.getClass().toString() + "::getUsersNumber");

        IBackendClient client = new ServiceGenerator().createService(IBackendClient.class);
        Call<ResponseBody> call = client.getUsersNumber();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                mIBackendClient.hideDialog();
                if (response != null) {
                    //mIBackendClient.hideDialog();
                    ResponseBody errorBody = response.errorBody();
                    if (!response.isSuccessful() && response.errorBody() != null){
                        try {
                            String errorMsg = errorBody.string();
                            Log.d(TAG, this.getClass().toString() + "::getUsersNumber::Response on error: " + errorMsg);
                            mIBackendClient.onError(errorMsg);
                        } catch (IOException e) {
                            Log.e("Error", e.toString());
                            e.printStackTrace();
                        }
                    } else {
                        //mIBackendClient.hideDialog();
                        try {
                            String result = response.body().string();
                            Log.d(TAG, this.getClass().toString() + "::getUsersNumber::Response ok: " + result);
                            mIBackendClient.nextAction(Helper.stringToJson(result));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Log.d(TAG, this.getClass().toString() + "::getUsersNumber::Response error: " + NULL_RESPONSE);
                    //mIBackendClient.hideDialog();
                    mIBackendClient.onError(NULL_RESPONSE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, this.getClass().toString() + "::getUsersNumber::onFailure: " + t.toString());
                if (call.isCanceled()) {
                    Log.d(TAG, this.getClass().toString() + "::getUsersNumber::onFailure: request was cancelled");
                }
                mIBackendClient.hideDialog();
                mIBackendClient.onFailure(t.toString());
            }
        });
        return call;
    }
}
