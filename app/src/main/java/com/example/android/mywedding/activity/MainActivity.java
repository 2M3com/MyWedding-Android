package com.example.android.mywedding.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.mywedding.Enviroment;
import com.example.android.mywedding.R;
import com.example.android.mywedding.connection.BackendService;
import com.example.android.mywedding.interfaces.IBackendClientAux;
import com.example.android.mywedding.util.Helper;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;


public class MainActivity extends AppCompatActivity implements IBackendClientAux{

    private static final String TAG = "STEP";
    private ProgressDialog progressDialog;
    private BackendService client;
    private Call<ResponseBody> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, this.getClass().toString() + "::onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        Helper.showProgressDialog(getString(R.string.app_name), "getting data", ProgressDialog.STYLE_SPINNER, this, progressDialog, call);
        client = new BackendService(this);
        call = client.getUsersNumber();

        /*TextView hello = (TextView)findViewById(R.id.hello);
        if (Enviroment.ENVIROMENT.equals("DEV") ){
            hello.setText("Hello world (DEV ENV)");
        } else {
            hello.setText("Hello world (PRO ENV)");
        }*/
    }


    public void showDialog(final Activity activity, String title, String msg){
        Log.d(TAG, this.getClass().toString() + "::showDialog");

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null) builder.setTitle(title);
        builder.setMessage(msg);

        builder.setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog = new ProgressDialog(activity);
                Helper.showProgressDialog(getString(R.string.app_name), "getting data", ProgressDialog.STYLE_SPINNER, activity, progressDialog, call);
                call = client.getUsersNumber();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        });
        builder.show();
    }

    @Override
    public void displayDialog(String msg) {

    }

    @Override
    public void hideDialog() {
        Helper.hideProgressDialog(this, progressDialog);
    }

    @Override
    public void nextAction(JsonObject json) {
        Log.d(TAG, this.getClass().toString() + "::nextAction");
        TextView hello = (TextView)findViewById(R.id.hello);
        String cad = "Users number: " + json.get("result").getAsInt();
        hello.setText(cad);
    }

    @Override
    public void onError(String msg) {
        Log.d(TAG, this.getClass().toString() + "::onError");
        showDialog(this, getString(R.string.app_name), msg);
    }

    @Override
    public void onFailure(String msg) {
        Log.d(TAG, this.getClass().toString() + "::onFailure");
        showDialog(this, getString(R.string.app_name), msg);
    }
}
