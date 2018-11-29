package com.gptm.app.controller;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.gptm.app.R;
import com.gptm.app.model.Player;


/**
 * Class extending to BaseExpandableListAdapter (predefined class).
 * All the methods within the class must be implemented.
 */
@SuppressWarnings("ConstantConditions")
public class ScoreExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private int holeCount;
    private Player[] mPlayers;

    public ScoreExpandableListAdapter(Context context, int holeCount, Player[] players) {
        this.context = context;
        this.holeCount = holeCount;
        this.mPlayers = players;

    }

    @Override
    public int getGroupCount() {
        return holeCount;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        try {
            return mPlayers.length;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        try {

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.score_view_list, parent, false);
            }

            TextView mHoleNumTextView = convertView.findViewById(R.id.hole_num_text_view);
            mHoleNumTextView.setText("Hole " + (groupPosition + 1));
            mHoleNumTextView.setGravity(Gravity.CENTER);

            if (isExpanded)
                convertView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
            else
                convertView.setBackgroundColor(context.getResources().getColor(R.color.itemTextColor));

        } catch (Exception ignored) {}

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.score_view_list, parent, false);
        }

        TextView mHoleNumTextView = convertView.findViewById(R.id.hole_num_text_view);
        mHoleNumTextView.setText(mPlayers[childPosition].getPlayerName());
        mHoleNumTextView.setGravity(Gravity.START);

        try {

            TextView mShotsTextView = convertView.findViewById(R.id.shots_text_view);
            mShotsTextView.setText(ScoreTrackMap.getInstance().getmScoreMapList().get(groupPosition).get(childPosition).toString());

        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}