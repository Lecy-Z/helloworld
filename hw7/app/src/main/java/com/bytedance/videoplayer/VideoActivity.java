package com.bytedance.videoplayer;


import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.media.MediaPlayer;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;

import com.bytedance.videoplayer.play.VideoPlayerIJK;
import com.bytedance.videoplayer.play.VideoPlayerListener;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 使用开源IjkPlayer播放视频
 */
public class VideoActivity extends AppCompatActivity{
   private VideoPlayerIJK ijkPlayer;
   private MediaPlayer player;
   private SurfaceHolder holder;
   private VideoView vv;
   private Button btn_play;
   private Button btn_pause;
   MediaController mediaController;


   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      setContentView(R.layout.layout_vedio);
      setTitle("VideoView");

      btn_pause = findViewById(R.id.buttonPause);
      vv=(VideoView)findViewById(R.id.vv);
      mediaController=new MediaController(this);
      btn_pause.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            vv.pause();
         }
      });

      btn_play = findViewById(R.id.buttonPlay);
      btn_play.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            vv.start();
         }
      });

      vv = findViewById(R.id.vv);
      String url1="android.resource://" + this.getPackageName() + "/" +R.raw.bytedance;
      //String url2= "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8";
      //String url2= "https://flv2.bn.netease.com/videolib1/1811/26/OqJAZ893T/HD/OqJAZ893T-mobile.mp4";
      String url2="https://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
       //vv.setVideoURI(Uri.parse(url1));
      vv.setVideoURI(Uri.parse(url2));
      //vv.setVideoPath(getVideoPath(R.raw.bytedance));

      mediaController.setMediaPlayer(vv);
      vv.setMediaController(mediaController);

   }






}



