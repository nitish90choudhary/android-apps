package com.example.miwok.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.miwok.R;
import com.example.miwok.adapters.WordAdapter;
import com.example.miwok.model.Word;

import java.util.ArrayList;
import java.util.List;


public class PhrasesActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;

    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null)
            mMediaPlayer.release();

        mMediaPlayer = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        ListView rootListView = findViewById(R.id.rootListView);

        final List<Word> wordList = new ArrayList<>();
        wordList.add(new Word("Wat is jouw naam?", "What is your name?"));
        wordList.add(new Word("Mijn naam is ...", "My name is .."));
        wordList.add(new Word("Hoe gaat het met je?", "How are you?"));
        wordList.add(new Word("Ik maak het prima dankjewel! En jij?", "I am fine, thank you! And you?"));
        wordList.add(new Word("Waar werk je?", "Where do you work?"));
        wordList.add(new Word("I work at..", "Ik werk bij ..."));
        wordList.add(new Word("Laten we gaan!", "Let's go!"));
        wordList.add(new Word("Tot straks", "See you later (in the same day)"));
        wordList.add(new Word("Tot zo", "See you soon"));
        wordList.add(new Word("Alstublieft / Alsjeblieft", "Please"));
        wordList.add(new Word("Dank u wel / Dank je wel", "Thank you"));


        WordAdapter wordAdapter = new WordAdapter(this, wordList, R.color.category_phrases);

        rootListView.setAdapter(wordAdapter);
        rootListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, wordList.get(position).getMediaRes());
                mMediaPlayer.start();
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
