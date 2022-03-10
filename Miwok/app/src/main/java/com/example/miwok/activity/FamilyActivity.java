package com.example.miwok.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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


public class FamilyActivity extends AppCompatActivity {

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
                //focus is loosing either for short period or can duck
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
        setContentView(R.layout.activity_family);
        ListView rootListView = findViewById(R.id.rootListView);

        final List<Word> wordList = new ArrayList<>();
        wordList.add(new Word("vader", "father", R.drawable.family_father, R.raw.vader));
        wordList.add(new Word("moeder", "mother", R.drawable.family_mother, R.raw.moeder));
        wordList.add(new Word("zoon", "son", R.drawable.family_son, R.raw.zoon));
        wordList.add(new Word("dochter", "daughter", R.drawable.family_daughter, R.raw.dochter));
        wordList.add(new Word(" broer", "brother", R.drawable.family_older_brother, R.raw.broer));
        wordList.add(new Word("zus", "sister", R.mipmap.ic_launcher, R.raw.zus));
        wordList.add(new Word("oma", "grand-mother", R.drawable.family_grandmother, R.raw.oma));
        wordList.add(new Word("opa", "grand-father", R.drawable.family_grandfather, R.raw.opa));
        wordList.add(new Word("oom", "uncle", R.mipmap.ic_launcher, R.raw.oom));
        wordList.add(new Word("tante", "aunt", R.mipmap.ic_launcher, R.raw.tante));


        WordAdapter wordAdapter = new WordAdapter(this, wordList, R.color.category_family);

        rootListView.setAdapter(wordAdapter);
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        rootListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, STREAM_MUSIC, AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer();
                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this, wordList.get(position).getMediaRes());
                    mMediaPlayer.start();
                } else
                    Toast.makeText(FamilyActivity.this, "Audio focus not granteds", Toast.LENGTH_SHORT);

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
