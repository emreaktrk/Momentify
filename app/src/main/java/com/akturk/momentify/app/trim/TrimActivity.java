package com.akturk.momentify.app.trim;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.VideoView;

import com.akturk.momentify.R;
import com.akturk.trimbar.TrimBar;

public class TrimActivity extends AppCompatActivity implements
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnErrorListener,
        TrimBar.OnSeekListener {

    private VideoView mVideoView;
    private Uri mUri;

    private TrimBar mTrimBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trim);

        mTrimBar.setOnSeekListener(this);

        mUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample);

        mVideoView.setOnErrorListener(this);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setVideoURI(mUri);

        mVideoView.start();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        mVideoView = (VideoView) findViewById(R.id.layout_trim_video_view);
        mTrimBar = (TrimBar) findViewById(R.id.layout_trim_trim_bar);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onSeekStart() {
        Toast.makeText(TrimActivity.this, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSeekMove(int time) {
        mVideoView.seekTo(time * 100000);
    }

    @Override
    public void onSeekEnd(int time, int start, int end) {
        Toast.makeText(TrimActivity.this, "End : " + " start : " + start + " end : " + end, Toast.LENGTH_SHORT).show();
    }
}
