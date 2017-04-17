package com.example.android.mywedding.interfaces;


public interface IBackendClientAux {

    void displayDialog(String msg);
    void hideDialog();
    void nextAction();
    void onErrorNfp(String msg);
    void onFailure(String msg);
}
