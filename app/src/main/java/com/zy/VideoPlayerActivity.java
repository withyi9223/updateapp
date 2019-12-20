package com.zy;

import android.net.Uri;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.IOException;

public class VideoPlayerActivity extends AppCompatActivity {

    private VrVideoView vr_video;
    private SeekBar seekBar;
    private String url = "http://120.77.37.252:8086/static/imgWork/vedio/case-2048.mp4";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        vr_video = findViewById(R.id.vr_video);
        seekBar = findViewById(R.id.seek_bar);
        
        
        vr_video.setEventListener(new VrVideoEventListener() {
            @Override
            public void onLoadSuccess() {
                super.onLoadSuccess();
                seekBar.setMax((int) vr_video.getDuration());
            }

            @Override
            public void onNewFrame() {
                super.onNewFrame();
                seekBar.setProgress((int) vr_video.getCurrentPosition());
            }
        });

        VrVideoView.Options options = new VrVideoView.Options();
        options.inputType = VrVideoView.Options.TYPE_MONO;
        options.inputFormat = VrVideoView.Options.FORMAT_DEFAULT;
        try {
            vr_video.loadVideo(Uri.parse(url), options);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        vr_video.resumeRendering();
    }

    @Override
    protected void onPause() {
        super.onPause();
        vr_video.pauseRendering();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vr_video.shutdown();
    }
}
