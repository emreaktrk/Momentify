package com.akturk.momentify.app.trim;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.akturk.momentify.R;
import com.coremedia.iso.IsoFile;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;

public class TrimActivity extends AppCompatActivity implements
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener,
        ControllerOverlay.Listener {

    private Uri mUri;

    private VideoView mVideoView;
    private TrimControllerOverlay mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trim);

        mUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample);

        mVideoView.setOnErrorListener(this);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setVideoURI(mUri);

        mVideoView.start();
        mController.showPlaying();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        mController = new TrimControllerOverlay(getApplicationContext());
        mController.setListener(this);

        ViewGroup rootView = (ViewGroup) findViewById(R.id.layout_trim_root_view);
        rootView.addView(mController.getView());

        mVideoView = (VideoView) findViewById(R.id.layout_trim_video_view);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPlayPause() {

    }

    @Override
    public void onSeekStart() {

    }

    @Override
    public void onSeekMove(int time) {

    }

    @Override
    public void onSeekEnd(int time, int trimStartTime, int trimEndTime) {

    }

    @Override
    public void onShown() {

    }

    @Override
    public void onHidden() {

    }

    @Override
    public void onReplay() {

    }
}
