package com.example.hw2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw2.recycler.LinearItemDecoration;
import com.example.hw2.recycler.MyAdapter;
import com.example.hw2.recycler.TestData;
import com.example.hw2.recycler.TestDataSet;

import androidx.appcompat.app.AppCompatActivity;

public class DYActivity extends AppCompatActivity implements MyAdapter.IOnItemClickListener {

    private static final String TAG = "TAG";
    /*
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dy);
            Log.i(TAG, "LinearLayoutActivity onCreate");
            initView();
        }

        private void initView() {
        }

        @Override
        protected void onStart() {
            super.onStart();
            Log.i(TAG, "LinearLayoutActivity onStart");
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

       */
    //recycler
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dy);
        Log.i(TAG, "RecyclerViewActivity onCreate");
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(TestDataSet.getData());
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        LinearItemDecoration itemDecoration = new LinearItemDecoration(Color.BLUE);
//        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        DefaultItemAnimator animator = new DefaultItemAnimator();
//        animator.setAddDuration(3000);
//        recyclerView.setItemAnimator(animator);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "RecyclerViewActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "RecyclerViewActivity onResume");
    }


    @Override
    public void onItemCLick(int position, TestData data) {
        //Toast.makeText(DYActivity.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
        // mAdapter.addData(position + 1, new TestData("新增头条", "0w"));
        switch (position) {
            case 0:
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("position","点击了第" + position + "条");
                startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent(this, DYassActivity.class);
                intent1.putExtra("position","点击了第" + position + "条");
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(this, SyetemActivity.class);
                intent2.putExtra("position","点击了第" + position + "条");
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(this,f1Activity.class);
                intent3.putExtra("position","点击了第" + position + "条");
                startActivity(intent3);
                break;
            case 4:
                Intent intent4 = new Intent(this,f2Activity.class);
                intent4.putExtra("position","点击了第" + position + "条");
                startActivity(intent4);
                break;
            case 5:
                Intent intent5 = new Intent(this,f3Activity.class);
                intent5.putExtra("position","点击了第" + position + "条");
                startActivity(intent5);
                break;
            case 6:
                Intent intent6 = new Intent(this,f4Activity.class);
                intent6.putExtra("position","点击了第" + position + "条");
                startActivity(intent6);
                break;
            case 7:
                Intent intent7 = new Intent(this,f5Activity.class);

                intent7.putExtra("position","点击了第" + position + "条");
                startActivity(intent7);
                break;
        }
    }
    @Override
    public void onItemLongCLick(int position, TestData data) {
        Toast.makeText(DYActivity.this, "长按了第" + position + "条", Toast.LENGTH_SHORT).show();
        mAdapter.removeData(position);
    }

}


