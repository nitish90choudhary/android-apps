package com.example.miwok.activity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miwok.R;
import com.example.miwok.adapters.WordAdapter;
import com.example.miwok.model.Word;

import java.util.ArrayList;
import java.util.List;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;
import static android.media.AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
import static android.media.AudioManager.STREAM_MUSIC;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AUDIOFOCUS_GAIN)
                mMediaPlayer.start();
            else if (focusChange == AUDIOFOCUS_LOSS)
                releaseMediaPlayer();
        }
    };

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null)
            mMediaPlayer.release();

        mMediaPlayer = null;
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ListView rootListView = findViewById(R.id.rootListView);
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        final List<Word> wordList = new ArrayList<>();
        wordList.add(new Word("een", "one", R.drawable.number_one, R.raw.number_1));
        wordList.add(new Word("twee", "two", R.drawable.number_two, R.raw.number_2));
        wordList.add(new Word("drie", "three", R.drawable.number_three, R.raw.number_3));
        wordList.add(new Word("vier", "four", R.drawable.number_four, R.raw.number_4));
        wordList.add(new Word("vijf", "five", R.drawable.number_five, R.raw.number_5));
        wordList.add(new Word("zes", "six", R.drawable.number_six, R.raw.number_6));
        wordList.add(new Word("zeven", "seven", R.drawable.number_seven, R.raw.number_7));
        wordList.add(new Word("acht", "eight", R.drawable.number_eight, R.raw.number_8));
        wordList.add(new Word("negen", "nine", R.drawable.number_nine, R.raw.number_9));
        wordList.add(new Word("tien", "ten", R.drawable.number_ten, R.raw.number_10));
        wordList.add(new Word("elf", "eleven", R.mipmap.ic_launcher, R.raw.number_11));
        wordList.add(new Word("twelf", "twelve", R.mipmap.ic_launcher, R.raw.number_12));
        wordList.add(new Word("dertien", "thirteen", R.mipmap.ic_launcher, R.raw.number_13));
        wordList.add(new Word("tien", "fourteen", R.mipmap.ic_launcher, R.raw.number_14));
        wordList.add(new Word("vijftien", "fifteen", R.mipmap.ic_launcher, R.raw.number_15));
        wordList.add(new Word("tien", "sixteen", R.mipmap.ic_launcher, R.raw.number_16));
        wordList.add(new Word("zeventeen", "seventeen", R.mipmap.ic_launcher, R.raw.number_17));


        WordAdapter wordAdapter = new WordAdapter(this, wordList, R.color.category_numbers);

        rootListView.setAdapter(wordAdapter);
        rootListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, STREAM_MUSIC, AUDIOFOCUS_GAIN_TRANSIENT);
                releaseMediaPlayer();
                if (result == AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, wordList.get(position).getMediaRes());
                    mMediaPlayer.start();
                } else
                    Toast.makeText(NumbersActivity.this, "Audio focus request rejected", Toast.LENGTH_SHORT);
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
