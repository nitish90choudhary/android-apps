package com.example.miwok.activity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miwok.R;
import com.example.miwok.adapters.WordAdapter;
import com.example.miwok.model.Word;

import java.util.ArrayList;
import java.util.List;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class ColorsActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;
    AudioManager audioManager;
    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                //pause playback
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
                //resume playback
            } else if (focusChange == AUDIOFOCUS_LOSS)
                releaseMediaPlayer();
        }
    };

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null)
            mMediaPlayer.release();

        mMediaPlayer = null;
        audioManager.abandonAudioFocus(afChangeListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        setContentView(R.layout.activity_colors);
        ListView rootListView = findViewById(R.id.rootListView);

        final List<Word> wordList = new ArrayList<>();
        wordList.add(new Word("rood", "red", R.drawable.color_red));
        wordList.add(new Word("groen", "green", R.drawable.color_green));
        wordList.add(new Word("bruin", "brown", R.drawable.color_brown, R.raw.color_bruin));
        wordList.add(new Word("grijs", "gray", R.drawable.color_gray));
        wordList.add(new Word("zwart", "black", R.drawable.color_black, R.raw.color_zwart));
        wordList.add(new Word("geel", "yellow", R.drawable.color_dusty_yellow, R.raw.color_geel));
        wordList.add(new Word("blauw", "blue", R.mipmap.ic_launcher, R.raw.color_blauw));
        wordList.add(new Word("roze", "pink", R.mipmap.ic_launcher));
        wordList.add(new Word("hemelsblauw", "sky blue", R.mipmap.ic_launcher));
        wordList.add(new Word("gouden", "golden", R.mipmap.ic_launcher));
        wordList.add(new Word("wit", "white", R.mipmap.ic_launcher, R.raw.color_wit));


        final WordAdapter wordAdapter = new WordAdapter(this, wordList, R.color.category_colors);

        rootListView.setAdapter(wordAdapter);

        rootListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                int result = audioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, wordList.get(position).getMediaRes());
                    mMediaPlayer.start();
                }

                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
