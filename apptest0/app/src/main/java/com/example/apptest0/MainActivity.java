package com.example.apptest0;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int size=10;
    public void bigger(View v){
        TextView txv;
        txv= (TextView) findViewById(R.id.txv);
        txv.setTextSize(++size);       // 修改字符大小
        Log.i("bigger", "按下按钮");
    }

}
