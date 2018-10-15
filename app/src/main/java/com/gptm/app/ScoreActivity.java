package com.gptm.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.gptm.app.model.Player;

public class ScoreActivity extends AppCompatActivity {

    private ExpandableListView mExpandableListView;
    private Player[] mPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mExpandableListView = findViewById(R.id.score_expandable_list_view);

    }
}