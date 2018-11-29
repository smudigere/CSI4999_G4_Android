package com.gptm.app.model;

import com.gptm.app.controller.HoleCount;

import org.json.JSONArray;
import org.json.JSONObject;

public class CourseInfo {

    private String mName;
    private int mHoleCount;
    private int mTotalRed;
    private int mTotalGold;
    private int mTotalBlack;
    private int mTotalWhite;

    public int getmSelectedTee() {
        return mSelectedTee;
    }

    public void setmSelectedTee(int mSelectedTee) {
        this.mSelectedTee = mSelectedTee;
    }

    private int mSelectedTee;

    private Hole[] mHoles;

    public String getmName() {
        return mName;
    }

    public int getmHoleCount() {
        return mHoleCount;
    }

    public Hole[] getmHoles() {
        return mHoles;
    }

    public int getmTotalRed() {
        return mTotalRed;
    }

    public int getmTotalGold() {
        return mTotalGold;
    }

    public int getmTotalBlack() {
        return mTotalBlack;
    }

    public int getmTotalWhite() {
        return mTotalWhite;
    }

    public CourseInfo(String json)  {

        try {

            mTotalRed = 0;
            mTotalGold = 0;
            mTotalBlack = 0;
            mTotalWhite = 0;

            JSONObject courseJson = new JSONObject(json);

            mName = courseJson.getString("name");
            mHoleCount = courseJson.getInt("holes");

            HoleCount.getInstance().setHoleCount(mHoleCount);

            mHoles = new Hole[mHoleCount];

            JSONArray holesArray = courseJson.getJSONArray("holeinfo");

            for (int i = 0; i < mHoleCount; i++)    {
                mHoles[i] = new Hole(
                        holesArray.getJSONObject(i).getInt("par"),
                        holesArray.getJSONObject(i).getInt("red"),
                        holesArray.getJSONObject(i).getInt("gold"),
                        holesArray.getJSONObject(i).getInt("black"),
                        holesArray.getJSONObject(i).getInt("white")
                );

                mTotalRed += mHoles[i].getmRedTee();
                mTotalGold += mHoles[i].getmGoldTee();
                mTotalBlack += mHoles[i].getmBlackTee();
                mTotalWhite += mHoles[i].getmWhiteTee();
            }


        } catch (Exception ignored) {}
    }


}
