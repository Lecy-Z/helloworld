package com.bytedance.todolist.activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import com.bytedance.todolist.database.TodoListDao;
import com.bytedance.todolist.database.TodoListDatabase;
import com.bytedance.todolist.database.TodoListEntity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.todolist.R;
import com.bytedance.todolist.database.TodoListEntity;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;


public class TakeNoteActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private EditText editText;
    private Button mbtn_cnf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_a_note);
        Log.i(TAG, "LinearLayoutActivity onCreate");
        initView();
    }

    private void initView() {
        editText = findViewById(R.id.edittext);
        mbtn_cnf=findViewById(R.id.btn_cnf);
        mbtn_cnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String input = editText.getText().toString();
                //写入数据库
                new Thread() {
                    @Override
                    public void run() {
                        TodoListDao dao = TodoListDatabase.inst(TakeNoteActivity.this).todoListDao();
                        //dao.deleteAll();
                        TodoListEntity entity=new TodoListEntity(input, new Date(System.currentTimeMillis()));
                            dao.addTodo(entity);
                        //TodoListDao dao = TodoListDatabase.inst(TodoListActivity.this).todoListDao();
                        dao.updTodo(entity);



                        Snackbar.make(mbtn_cnf, "数据新建完成", Snackbar.LENGTH_SHORT).show();
                    }
                }.start();



                Intent intent = new Intent();
                // b intent.putExtra(KEY, input);
                if (input.isEmpty()) {
                    setResult(RESULT_CANCELED, intent);
                } else {
                    setResult(RESULT_OK, intent);
                }

                finish();
            }
        });
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

    }

