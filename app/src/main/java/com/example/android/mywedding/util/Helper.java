package com.example.android.mywedding.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.android.mywedding.R;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class Helper {


    /**
     * Display information to user.
     * @param title Alert title.
     * @param msg   Alert message information.
     */
    public static void showAlert(String title, String msg, Activity act){
        new AlertDialog.Builder(act)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(true)
                .setNeutralButton(R.string.close, null)
                .show();
    }

    /**
     * To display a progress dialog
     *
     * @param title          string: Title of progress dialog.
     * @param msg            string: Message to show in the progress dialog.
     * @param style          int: Type of progress dialog style.
     * @param act            activity: Activity that it invokes progress dialog.
     * @param progressDialog ProgressDialog: ProgressDialog object.
     */
    public static void showProgressDialog(final String title, final String msg, final int style,
                                          final Activity act, final ProgressDialog progressDialog,
                                          final Call<ResponseBody> call) {
        try {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.setTitle(title);
                    progressDialog.setMessage(msg);
                    progressDialog.setProgressStyle(style);
                    progressDialog.setCancelable(true);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            if (call != null) {
                                call.cancel();
                            }
                        }
                    });
                }
            });
        } catch (Exception ex) {
            Log.e("ProgressDialogError", ex.toString());
        }
    }

    /**
     * To hide and close current progress dialog.
     *
     * @param act            Activity that it invokes the progress dialog.
     * @param progressDialog Progress dialog object.
     */
    public static void hideProgressDialog(Activity act, final ProgressDialog progressDialog) {
        try {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.hide();
                    progressDialog.dismiss();
                }
            });
        } catch (NullPointerException e) {
            Log.e("Error", "Hide ProgressDialog error");
        }
    }

    /**
     * Check if input string if it is not null and return , empty string in other case.
     * @param input String to check.
     * @return Return a string: original string if it is not null, empty string in other case.
     */
    public static String assureNotNull (String input){
        input = (input == null) ? "" : input;
        return input;
    }

    /**
     * Make a JsonObject object from a string.
     * @param input string.
     * @return New json from input string.
     */
    public static JsonObject stringToJson(String input) {
        if (input == null || input.isEmpty()){
            return new JsonObject();
        }
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(input);
        if (jsonElement.isJsonObject()) {
            return jsonElement.getAsJsonObject();
        } else {
            JsonObject json = new JsonObject();
            json.add("result", jsonElement);
            return json;
        }
    }

    /**
     * When failure connection process, it can be used to get json response.
     *
     * @param status Response status.
     * @param msg Error string message.
     * @return Output json information.
     */
    public static JsonObject errorToJson(String status, String msg) {
        status = assureNotNull(status);
        msg = assureNotNull(msg);
        JsonObject json = new JsonObject();
        json.addProperty("status", status);
        json.addProperty("message", msg);
        JsonObject result = new JsonObject();
        result.add("error", json);
        return result;
    }

}
