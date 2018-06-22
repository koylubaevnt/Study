package com.koylubaevnt.udacity.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addThreeForTeamA(View view) {
        scoreTeamA += 3;
        displayForTeamA();
    }

    public void addTwoForTeamA(View view) {
        scoreTeamA += 2;
        displayForTeamA();
    }

    public void addFreeThrowForTeamA(View view) {
        scoreTeamA += 1;
        displayForTeamA();
    }

    public void addThreeForTeamB(View view) {
        scoreTeamB += 3;
        displayForTeamB();
    }

    public void addTwoForTeamB(View view) {
        scoreTeamB += 2;
        displayForTeamB();
    }

    public void addFreeThrowForTeamB(View view) {
        scoreTeamB += 1;
        displayForTeamB();
    }

    public void resetScores(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA();
        displayForTeamB();
    }
    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA() {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(scoreTeamA));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB() {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(scoreTeamB));
    }
}
