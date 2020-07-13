package com.bytedance.todolist.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.todolist.R;
import com.bytedance.todolist.database.TodoListEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangrui.sh
 * @since Jul 11, 2020
 */
public class TodoListAdapter extends RecyclerView.Adapter <TodoListAdapter.TodoListItemHolder> implements View.OnClickListener {

    private List<TodoListEntity> mDatas;

    /////
    //public CheckBox mcheckbox;
    private  boolean isshow=false;
    private  Map<Integer,Boolean> map =new HashMap<>();


    public TodoListAdapter() {
        mDatas = new ArrayList<>();
        initMap();
    }
    private void initMap(){
        for(int i=0;i<mDatas.size();i++)
        {
            map.put(i,false);
        }
    }

    @NonNull
    @Override
    public TodoListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListItemHolder holder, final int position) {
        holder.bind(mDatas.get(position));

        holder.itemView.setTag(position);
        holder.mbut_img.setTag(position);


        //
        holder.mcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                map.put(position,isChecked);
            }
        });
        if(map.get(position)==null){
            map.put(position,false);
        }
        holder.mcheckbox.setChecked(map.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private String formatDate(Date date) {
        DateFormat format = SimpleDateFormat.getDateInstance();
        return format.format(date);
    }

    /*
    private TextView mContent;
    private TextView mTimestamp;
    public ImageButton mbut_img;
    */
    public class TodoListItemHolder extends RecyclerView.ViewHolder {
        private TextView mContent;
        private TextView mTimestamp;
        public ImageButton mbut_img;

        ///////
        public CheckBox mcheckbox;




        public TodoListItemHolder(@NonNull View itemView) {
            super(itemView);
            mContent = itemView.findViewById(R.id.tv_content);
            mTimestamp = itemView.findViewById(R.id.tv_timestamp);
            mbut_img = itemView.findViewById(R.id.btn_del);
            //////
            mcheckbox=itemView.findViewById(R.id.checkBox);

            itemView.setOnClickListener(TodoListAdapter.this);
            mbut_img.setOnClickListener((TodoListAdapter.this));
        }

        public void bind(TodoListEntity entity) {
            mContent.setText(entity.getContent());
            mTimestamp.setText(formatDate(entity.getTime()));
        }
    }



   public enum ViewName {
       ITEM,
       PRA
   }


    public interface OnItemClickListener {
            void onItemClick(View v, ViewName viewName, int position);
        }

        public OnItemClickListener mOnItemClickListener;

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }


            @Override
            public void onClick (View v){
            ///
            int position = 1;
            if (mOnItemClickListener != null) {
                switch (v.getId()) {
                    case R.id.rv_list:
                        mOnItemClickListener.onItemClick(v,ViewName.PRA, position);
                        break;
                    default:
                        mOnItemClickListener.onItemClick(v, ViewName.ITEM, position);
                        break;
                }
            }
            ///

        }


        ///////
    public  void  setSelectItem(int position){
            if(map.get(position)){
                map.put(position,false);
            }else {
                map.put(position,true);
            }
            notifyItemChanged(position);
    }
    public  Map<Integer,Boolean>getMap(){
            return  map;
    }

    @MainThread
    public void setData(List<TodoListEntity> list) {
        mDatas = list;
        notifyDataSetChanged();
    }
    ////
}
