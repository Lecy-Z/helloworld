package com.example.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class f3Activity extends AppCompatActivity {

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f3);
        Log.i(TAG, "f3Activity onCreate");
        initView();
    }

    private void initView() {
        Intent intent=getIntent();
        TextView pos=findViewById(R.id.pos);
        pos.setText(intent.getStringExtra("position"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "f3Activity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "f3Activity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "f3Activity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "f3Activity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "f3Activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "f3Activity onDestroy");
    }

}