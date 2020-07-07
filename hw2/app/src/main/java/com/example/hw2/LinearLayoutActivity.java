package com.example.hw2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

public class LinearLayoutActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout);
        Log.i(TAG, "LinearLayoutActivity onCreate");
        initView();
    }

    private void initView() {
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Log.i(TAG, "LinearLayoutActivity onStart");
        ViewGroup root=(ViewGroup)findViewById(R.id.llo);
        int cnt=0;
        cnt++;
        if(root instanceof ViewGroup){
            LinkedList<ViewGroup> queue=new LinkedList<>();
            queue.add((ViewGroup)root);
            while(!queue.isEmpty()){
                ViewGroup p=queue.removeFirst();
                for(int i=0;i<p.getChildCount();i++)
                {
                    cnt++;
                    View c=p.getChildAt(i);
                    if(c instanceof  ViewGroup){
                        queue.add((ViewGroup)c);
                    }
                }
            }
        }

        Log.i(TAG, "view:"+cnt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "LinearLayoutActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "LinearLayoutActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "LinearLayoutActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "LinearLayoutActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "LinearLayoutActivity onDestroy");
    }

}