package com.gptm.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gptm.app.fragments.EnterHoleScoreFragment;
import com.gptm.app.utility.Functions;

public class EnterScoreActivity extends AppCompatActivity   {

    private int holeCount;

    private EnterHoleScoreFragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_score);

        holeCount = 2;

        fragments = new EnterHoleScoreFragment[holeCount];

        for (int i = 0; i < holeCount; i++)
            fragments[i] = EnterHoleScoreFragment.newInstance(i, holeCount);

        addEnterHoleScoreFragment(0);
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
}
