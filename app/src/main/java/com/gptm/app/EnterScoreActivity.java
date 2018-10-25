package com.gptm.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.gptm.app.controller.HoleCount;
import com.gptm.app.fragments.EnterHoleScoreFragment;
import com.gptm.app.model.Player;
import com.gptm.app.utility.Functions;

public class EnterScoreActivity extends AppCompatActivity   {

    private int holeCount;

    private EnterHoleScoreFragment[] fragments;

    private Player[] players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_score);

        try {

            holeCount = HoleCount.getInstance().getHoleCount();

            players = Functions.getPlayers(this);

            fragments = new EnterHoleScoreFragment[holeCount];

            for (int i = 0; i < holeCount; i++)
                fragments[i] = EnterHoleScoreFragment.newInstance(i, holeCount);

            addEnterHoleScoreFragment(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEnterHoleScoreFragment(int count) {

        if (count == holeCount)
            Toast.makeText(this, "Last Fragment", Toast.LENGTH_SHORT).show();
        else
            Functions.fragment_replacement(
                getSupportFragmentManager(),
                R.id.relative,
                fragments[count],
                false
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.score_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())   {
            case R.id.item1:        //Score Card

                Intent mIntent = new Intent(this, ScoreActivity.class);
                startActivity(mIntent);

                break;
            case R.id.item2:        //View Golf Course

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public Player[] getPlayers() {
        return players;
    }
}