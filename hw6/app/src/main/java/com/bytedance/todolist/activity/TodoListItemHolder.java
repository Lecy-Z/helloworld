package com.bytedance.todolist.activity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.todolist.R;
import com.bytedance.todolist.database.TodoListEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangrui.sh
 * @since Jul 11, 2020
 */
public class TodoListItemHolder extends RecyclerView.ViewHolder {
    private TextView mContent;
    private TextView mTimestamp;
    public ImageButton mbut_img;

    public TodoListItemHolder(@NonNull View itemView) {
        super(itemView);
        mContent = itemView.findViewById(R.id.tv_content);
        mTimestamp = itemView.findViewById(R.id.tv_timestamp);
        mbut_img=itemView.findViewById(R.id.btn_del);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mbut_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///
                int position=1;
                if(mOnItemClickListener!=null){
                    switch (v.getId()){
                        case R.id.rv_list:
                            mOnItemClickListener.onItemClick(v,ViewName.PRA,position);
                            break;
                        default:
                            mOnItemClickListener.onItemClick(v,ViewName.ITEM,position);
                            break;
                    }
                }
                ///

            }
        });
    }

    public void bind(TodoListEntity entity) {
        mContent.setText(entity.getContent());
        mTimestamp.setText(formatDate(entity.getTime()));
    }

    private String formatDate(Date date) {
        DateFormat format = SimpleDateFormat.getDateInstance();
        return format.format(date);
    }

    public enum ViewName{
        ITEM,
        PRA
    }

    public interface  OnItemClickListener{
        void onItemClick(View v,ViewName viewName,int position);

    }
    public  OnItemClickListener mOnItemClickListener;

    public  void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener=listener;
    }

}
