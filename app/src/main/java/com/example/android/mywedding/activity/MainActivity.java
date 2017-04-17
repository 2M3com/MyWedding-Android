package com.example.android.mywedding.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.mywedding.Enviroment;
import com.example.android.mywedding.R;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "STEP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, this.getClass().toString() + "::onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView hello = (TextView)findViewById(R.id.hello);
        if(Enviroment.ENVIROMENT.equals("DEV") ){
            hello.setText("Hello DEV");
        } else {
            hello.setText("Hello PRO");
        }
    }
}
