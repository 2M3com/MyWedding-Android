package com.example.android.mywedding.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.mywedding.Enviroment;
import com.example.android.mywedding.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
