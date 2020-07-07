package com.example.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DYassActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ass);
        Log.i(TAG, "DYassActivity onCreate");
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
        Log.i(TAG, "DYassActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "DYassActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "DYassActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "DYassActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "DYassActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "DYassActivity onDestroy");
    }

}