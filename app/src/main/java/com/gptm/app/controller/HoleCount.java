package com.gptm.app.controller;

public class HoleCount {

    private static HoleCount mHoleCount;
    private int holeCount;

    public static HoleCount getInstance() {
        init_obj();

        return mHoleCount;
    }

    public void setHoleCount(int holeCount)  {
        init_obj();

        mHoleCount.holeCount = holeCount;
    }

    private static void init_obj() {
        if (mHoleCount == null)
            mHoleCount = new HoleCount();
    }

    public int getHoleCount() {
        return holeCount;
    }

    private HoleCount() {}
}
