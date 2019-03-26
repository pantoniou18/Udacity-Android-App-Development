package com.example.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int scoreTeamA, scoreTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayScoreTeamA(){
        TextView scoreAText = (TextView)findViewById(R.id.score_team_a);
        scoreAText.setText(String.valueOf(scoreTeamA));
    }

    public void displayScoreTeamB(){
        TextView scoreBText = (TextView)findViewById(R.id.score_team_b);
        scoreBText.setText(String.valueOf(scoreTeamB));
    }


    public void addOneToScoreTeamA(View v){
        scoreTeamA = scoreTeamA + 1;
        displayScoreTeamA();
    }

    public void addOneToScoreTeamB(View v){
        scoreTeamB = scoreTeamB + 1;
        displayScoreTeamB();
    }


    public void addTwoToScoreTeamA(View v){
        scoreTeamA = scoreTeamA + 2;
        displayScoreTeamA();
    }

    public void addTwoToScoreTeamB(View v){
        scoreTeamB = scoreTeamB + 2;
        displayScoreTeamB();
    }

    public void addThreeToScoreTeamA(View v){
        scoreTeamA = scoreTeamA + 3;
        displayScoreTeamA();
    }

    public void addThreeToScoreTeamB(View v){
        scoreTeamB = scoreTeamB + 3;
        displayScoreTeamB();
    }

    public void resetScore(View v){
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayScoreTeamA();
        displayScoreTeamB();
    }
}
