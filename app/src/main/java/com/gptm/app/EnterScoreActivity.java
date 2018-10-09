package com.gptm.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gptm.app.fragments.EnterHoleScoreFragment;
import com.gptm.app.utility.Functions;

import org.json.JSONArray;
import org.json.JSONObject;

public class EnterScoreActivity extends AppCompatActivity   {

    private int holeCount;

    private String[] playerNames;
    private EnterHoleScoreFragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_score);

        try {

            holeCount = 2;

            Log.i("Player ", getIntent().getExtras().getString("Players"));

            JSONObject playersJsonObj = new JSONObject(getIntent().getExtras().getString("Players"));
            JSONArray jsonArray = playersJsonObj.getJSONArray("Players");

            playerNames = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++)
                playerNames[i] = jsonArray.get(i).toString();

            fragments = new EnterHoleScoreFragment[holeCount];

            for (int i = 0; i < holeCount; i++)
                fragments[i] = EnterHoleScoreFragment.newInstance(i, holeCount);

            addEnterHoleScoreFragment(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEnterHoleScoreFragment(int count) {

        if (count == holeCount)   {

            Toast.makeText(this, "Last Fragment", Toast.LENGTH_SHORT).show();

        } else
            Functions.fragment_replacement(
                getSupportFragmentManager(),
                R.id.relative,
                fragments[count],
                false
        );
    }


    public String[] getPlayerNames() {
        return playerNames;
    }
}
