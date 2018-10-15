/*
 * This file is added to help Categories Expand
 * @author Samartha Mudigere
 * @copyright Coupon Wallet, LLC
 * @Version 2.0
 * @build 0.1
 */

package com.gptm.app.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gptm.app.R;

import java.util.HashMap;
import java.util.List;


/**
 * Class extending to BaseExpandableListAdapter (predefined class).
 * All the methods within the class must be implemented.
 */
@SuppressWarnings("ConstantConditions")
public class ScoreExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHashMap;


    public ScoreExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        try {
            return listHashMap.get(listDataHeader.get(groupPosition)).size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition);
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

            String headerTitle = (String) getGroup(groupPosition);

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.score_view_list, parent, false);
            }

            convertView.setBackgroundColor(context.getResources().getColor(R.color.itemTextColor));

            TextView mHoleNumTextView = convertView.findViewById(R.id.hole_num_text_view);

            if (isExpanded) {
                convertView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));

            } else {
                convertView.setBackgroundColor(context.getResources().getColor(R.color.itemTextColor));

            }

        } catch (Exception ignored) {}

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.score_view_list, parent, false);
        }

        TextView mHoleNumTextView = convertView.findViewById(R.id.hole_num_text_view);


        return convertView;
    }

    /**
     * Decides whether the child categories is clickable or not.
     * @param groupPosition int <p the parent position />
     * @param childPosition <p the child position that is clicked />
     * @return boolean < if the child categories can be clicked or selected upon- set "true"
     *  else
     *  if the child categories must not be allowed to click (or selected)- set "false"
     * />
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}