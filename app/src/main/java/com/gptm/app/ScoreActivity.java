package com.gptm.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.gptm.app.controller.HoleCount;
import com.gptm.app.controller.ScoreExpandableListAdapter;
import com.gptm.app.model.Player;
import com.gptm.app.utility.Functions;

public class ScoreActivity extends AppCompatActivity implements
        ExpandableListView.OnGroupExpandListener{

    private ExpandableListView mExpandableListView;
    private Player[] mPlayers;

    private int lastExpandedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mExpandableListView = findViewById(R.id.score_expandable_list_view);

        init_data();
    }


    private void init_data()    {

        mPlayers = Functions.getPlayers(this);

        int holeCount = HoleCount.getInstance().getHoleCount();

        ScoreExpandableListAdapter adapter =
                new ScoreExpandableListAdapter(this, holeCount, mPlayers);

        mExpandableListView.setAdapter(adapter);
        mExpandableListView.setOnGroupExpandListener(this);
    }

    @Override
    public void onGroupExpand(int groupPosition) {
        if (lastExpandedPosition != -1
                && groupPosition != lastExpandedPosition)
            mExpandableListView.collapseGroup(lastExpandedPosition);

        lastExpandedPosition = groupPosition;
    }
}