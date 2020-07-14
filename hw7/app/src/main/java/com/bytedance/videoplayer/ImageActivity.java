package com.bytedance.videoplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import android.widget.ImageView;
import android.view.View;

public class ImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_image);
        ImageView imageView = findViewById(R.id.imageView);
        RequestOptions options=new RequestOptions().placeholder(R.drawable.loading).
                        error(R.drawable.fail).diskCacheStrategy(DiskCacheStrategy.NONE);

        String url = "https://s3.pstatp.com/toutiao/static/img/logo.271e845.png";
        Glide.with(this).load(url).apply(options).into(imageView);
        //initButton();
    }
}
