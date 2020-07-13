package com.bytedance.todolist.activity;

import android.os.Bundle;

import com.bytedance.todolist.database.TodoListDao;
import com.bytedance.todolist.database.TodoListDatabase;
import com.bytedance.todolist.database.TodoListEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.bytedance.todolist.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.List;

import android.util.Log;
import android.content.Intent;

public class TodoListActivity extends AppCompatActivity {

    private TodoListAdapter mAdapter;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list_activity_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TodoListAdapter();
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(MyItemClickListener);


        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                //有部分功能未能完全实现，希望能在以后的学习中加深了解，完善项目
                Intent intent = new Intent(TodoListActivity.this, TakeNoteActivity.class);
                startActivity(intent);


            }
        });
        //loadFromDatabase();
        mFab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        TodoListDao dao = TodoListDatabase.inst(TodoListActivity.this).todoListDao();
                        dao.deleteAll();
                        for (int i = 0; i < 20; ++i) {
                            dao.addTodo(new TodoListEntity("This is " + i + " item", new Date(System.currentTimeMillis())));
                        }
                        Snackbar.make(mFab, R.string.hint_insert_complete, Snackbar.LENGTH_SHORT).show();
                    }
                }.start();
                return true;
            }
        });

        loadFromDatabase();


    }

    private TodoListAdapter.OnItemClickListener MyItemClickListener=new TodoListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, TodoListAdapter.ViewName viewName, int position) {
            switch ((v.getId())){
                case R.id.btn_del:
                    //数据删除，此处已经可以反馈点击信息
                    Log.i("点了","cha");

                    break;
                case R.id.checkBox:
                    //选择checkBox
                    //Log.i("点了","check");
                    break;
                default:
                    break;
            }
        }
    };




    private void loadFromDatabase() {
        new Thread() {
            @Override
            public void run() {
                TodoListDao dao = TodoListDatabase.inst(TodoListActivity.this).todoListDao();
                final List<TodoListEntity> entityList = dao.loadAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("setData:","运行");
                        mAdapter.setData(entityList);
                    }
                });
            }
        }.start();
    }
}
