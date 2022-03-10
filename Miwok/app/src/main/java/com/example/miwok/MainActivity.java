package com.example.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miwok.activity.ColorsActivity;
import com.example.miwok.activity.FamilyActivity;
import com.example.miwok.activity.NumbersActivity;
import com.example.miwok.activity.PhrasesActivity;

public class MainActivity extends AppCompatActivity {
    TextView txtViewFamily;
    TextView txtViewNumber;
    TextView txtViewColors;
    TextView txtViewPhrases;
    Intent fIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtViewFamily = findViewById(R.id.family);
        txtViewNumber = findViewById(R.id.numbers);
        txtViewColors = findViewById(R.id.colors);
        txtViewPhrases = findViewById(R.id.phrases);

        txtViewNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(fIntent);
            }
        });

        txtViewColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(fIntent);
            }
        });

        txtViewFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fIntent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(fIntent);
            }
        });

        txtViewPhrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(fIntent);
            }
        });
    }


}
