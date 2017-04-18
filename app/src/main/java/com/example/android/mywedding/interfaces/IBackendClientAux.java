package com.example.android.mywedding.interfaces;


import com.google.gson.JsonObject;

public interface IBackendClientAux {

    void displayDialog(String msg);
    void hideDialog();
    void nextAction(JsonObject json);
    void onError(String msg);
    void onFailure(String msg);
}
