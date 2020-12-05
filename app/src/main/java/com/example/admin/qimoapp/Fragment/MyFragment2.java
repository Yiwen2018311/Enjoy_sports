package com.example.admin.qimoapp.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.admin.qimoapp.R;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by Jay on 2015/8/28 0028.
 */
@SuppressLint("ValidFragment")
public class MyFragment2 extends Fragment {
    private VideoView videoView;
    private static final String TAG = "MainActivity";
    private ListView mListView;
    private VideoListAdapter mAdapter;
    private SensorManager mSensorManager;
    private SensorEventListener mSensorEventListener;
    private View view;
    public MyFragment2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_fragment,container,false);
        mListView=(ListView)view.findViewById(R.id.list_view);
        mAdapter=new VideoListAdapter(getActivity());
        mListView.setAdapter(mAdapter);
        mSensorManager=(SensorManager)getActivity().getSystemService(SENSOR_SERVICE);
        mSensorEventListener=new JCVideoPlayer.JCAutoFullscreenListener();
         //播放本地视频
        videoView = (VideoView)view.findViewById(R.id.vdd2);
        videoView.setVideoURI(Uri.parse("android.resource://com.example.admin.qimoapp/"+R.raw.yujia));
        MediaController mediaController=new MediaController(getActivity());
        videoView.setMediaController(mediaController);
        videoView.stopPlayback();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        Sensor mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mSensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        Log.e(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorEventListener);
        JCVideoPlayer.releaseAllVideos();
        Log.e(TAG, "onPause");
    }
}