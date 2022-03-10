package com.example.courtscore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtViewTeamAScore;
    TextView txtViewTeamBScore;
    int scoreTeamA;
    int scoreTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtViewTeamAScore = findViewById(R.id.txtViewTeamAScore);
        txtViewTeamBScore = findViewById(R.id.txtViewTeamBScore);

        scoreTeamA = Integer.parseInt(txtViewTeamAScore.getText().toString());
        scoreTeamB = Integer.parseInt(txtViewTeamBScore.getText().toString());
    }

    public void threePointsForA(View view) {
        scoreTeamA += 3;
        displayScoreForA();
    }

    public void twoPointsForA(View view) {
        scoreTeamA += 2;
        displayScoreForA();
    }

    public void freeThrowForA(View view) {
        scoreTeamA += 1;
        displayScoreForA();
    }

    private void displayScoreForA() {
        txtViewTeamAScore.setText("" + scoreTeamA);
    }

    private void displayScoreForB() {
        txtViewTeamBScore.setText("" + scoreTeamB);
    }

    public void resetScore(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayScoreForA();
        displayScoreForB();
    }

    public void threePointsForB(View view) {
        scoreTeamB += 3;
        displayScoreForB();
    }

    public void twoPointsForB(View view) {
        scoreTeamB += 2;
        displayScoreForB();
    }

    public void freeThrowForB(View view) {
        scoreTeamB += 1;
        displayScoreForB();
    }
}
