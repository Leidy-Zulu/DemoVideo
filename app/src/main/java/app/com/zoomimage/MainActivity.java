package app.com.zoomimage;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {


    static final int REQUEST_VIDEO_CAPTURE = 1;
    private VideoView mVideoView;
    private ImageView imageViewPlay;
    private Button onVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoView =  findViewById(R.id.videoView);
        onVideo =  findViewById(R.id.onVideo);
        loadListener();


    }

    private void loadListener() {

        onVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakeVideoIntent();
            }
        });

    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
           // mVideoView.stopPlayback();
            MediaController mc = new MediaController(this);
            mVideoView.setMediaController(mc);
            mVideoView.setVideoURI(videoUri);
            mVideoView.setVisibility(View.VISIBLE);

            mVideoView.seekTo(1);
            //imageViewPlay.setVisibility(View.VISIBLE);


        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
