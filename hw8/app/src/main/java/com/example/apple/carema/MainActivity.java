package com.example.apple.carema;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

///
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;




public class MainActivity extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private MediaRecorder mMediaRecorder;
    private boolean isRecording = false;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private int mId;
    private String mPath;
    private Button mbutton;

    private static String[] permissions = new String[]{Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO};
    private static int PERMISSION_CODE = 1;

    private void requestForPermission(){
        if(PackageManager.PERMISSION_GRANTED != getPackageManager().checkPermission(permissions[0], getPackageName())
                || PackageManager.PERMISSION_GRANTED != getPackageManager().checkPermission(permissions[1], getPackageName()))
            ActivityCompat.requestPermissions(this,permissions,PERMISSION_CODE);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button captureButton = findViewById(R.id.button_capture);
        captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("点击了button","isRecording是"+isRecording);
                        record(v);;
                    }

                }
        );

        requestForPermission();
    }
    public void record(View v){

        if (isRecording) {
            ((Button)v).setText("Capture");

            mMediaRecorder.stop();
            releaseMediaRecorder();
        } else {

            if (prepareVideoRecorder(mCamera)) {


                ((Button)v).setText("暂停");
                mMediaRecorder.start();

                Toast.makeText(MainActivity.this, "文件保存在:"+mPath, Toast.LENGTH_LONG).show();
            }
            else {
                releaseMediaRecorder();
            }
        }
        isRecording = !isRecording;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        mId = FindCamera();
        System.out.println("mId = " + mId);
        try {
            mCamera = Camera.open(mId);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "摄像头打开失败", Toast.LENGTH_LONG).show();
            return;
        }
        mCamera.setFaceDetectionListener(new MyFaceDetectionListener());
        mPreview = new CameraPreview(MainActivity.this, mCamera, mId);
        FrameLayout preview = findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }



    @TargetApi(9)
    private int FindCamera(){
        int cameraCount = 0;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        System.out.println("cameraCount = " + cameraCount);
        for ( int camIdx = 0; camIdx < cameraCount;camIdx++ ) {
            Camera.getCameraInfo( camIdx, cameraInfo );
            if ( cameraInfo.facing ==Camera.CameraInfo.CAMERA_FACING_BACK) {
                return camIdx;
            }
        }
        return -1;
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaRecorder();
        releaseCamera();
    }

    private void releaseMediaRecorder(){
        if (mMediaRecorder != null) {
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
            mCamera.lock();
        }
    }

    private void releaseCamera(){
        if (mCamera != null){
            mCamera.release();
            mCamera = null;
        }
    }

    private boolean prepareVideoRecorder(Camera mCamera){

        mMediaRecorder = new MediaRecorder();
        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mMediaRecorder.setProfile(CamcorderProfile.get(mId, CamcorderProfile.QUALITY_1080P));
       mPath = getOutputMediaFile(MEDIA_TYPE_VIDEO).toString();

        mMediaRecorder.setOutputFile(mPath);
        Surface surface = mPreview.getHolder().getSurface();
        mMediaRecorder.setPreviewDisplay(surface);

        try {

            mMediaRecorder.prepare();

        } catch (IllegalStateException e) {

            releaseMediaRecorder();
            return false;
        } catch (IOException e) {

            releaseMediaRecorder();
            return false;
        }

        return true;
    }


    private static File getOutputMediaFile(int type){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    class MyFaceDetectionListener implements Camera.FaceDetectionListener {

        @Override
        public void onFaceDetection(Camera.Face[] faces, Camera camera) {
            if (faces.length > 0){
                Log.d("FaceDetection", "face detected: "+ faces.length +
                        " Face 1 Location X: " + faces[0].rect.centerX() +
                        "Y: " + faces[0].rect.centerY() );
            }
        }
    }

    public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder mHolder;
        private Camera mCamera;
        private int mId;
        private SurfaceView msurfaceView;

        public CameraPreview(Context context, Camera camera, int id) {
            super(context);
            mCamera = camera;
            mId = id;
            msurfaceView=findViewById(R.id.surfaceview);

            mHolder = getHolder();
            mHolder.addCallback(this);
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        public void surfaceCreated(SurfaceHolder holder) {
            try {
                mCamera.setPreviewDisplay(holder);
                mCamera.startPreview();
                startFaceDetection();
            } catch (IOException e) {

            }
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            if (mCamera != null) {
                mCamera.stopPreview();
            }
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
            if (mHolder.getSurface() == null){
                return;
            }
            try {
                mCamera.stopPreview();
            } catch (Exception e){

            }

            setCameraDisplayOrientation(MainActivity.this, mId, mCamera);
            List<Camera.Size> supportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
            for (Camera.Size size : supportedPreviewSizes) {
                System.out.println("Width = " + size.width + "  Height = " + size.height);
            }
            Camera.Parameters parameters = mCamera.getParameters();

            parameters.setPreviewSize(1920, 1080);
            mCamera.setParameters(parameters);

            try {
                mCamera.setPreviewDisplay(mHolder);
                mCamera.startPreview();
            } catch (Exception e){
            }
        }
        public void setCameraDisplayOrientation(AppCompatActivity activity, int cameraId, android.hardware.Camera camera) {
            android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
            android.hardware.Camera.getCameraInfo(cameraId, info);
            int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
            int degrees = 0;
            switch (rotation) {
                case Surface.ROTATION_0: degrees = 0; break;
                case Surface.ROTATION_90: degrees = 90; break;
                case Surface.ROTATION_180: degrees = 180; break;
                case Surface.ROTATION_270: degrees = 270; break;
            }

            int result;
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                result = (info.orientation + degrees) % 360;
                result = (360 - result) % 360;
            } else {
                result = (info.orientation - degrees + 360) % 360;
            }
            camera.setDisplayOrientation(result);
        }
    }

    private void startFaceDetection() {
        Camera.Parameters params = mCamera.getParameters();
        if (params.getMaxNumDetectedFaces() > 0){
            mCamera.startFaceDetection();
        }
    }

}
