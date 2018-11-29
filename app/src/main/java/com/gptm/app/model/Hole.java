package com.gptm.app.model;

public class Hole {

    public int getmPar() {
        return mPar;
    }

    public int getmRedTee() {
        return mRedTee;
    }

    public int getmGoldTee() {
        return mGoldTee;
    }

    public int getmBlackTee() {
        return mBlackTee;
    }

    public int getmWhiteTee() {
        return mWhiteTee;
    }

    public int getDistance(int mSelectedTee) {

        switch (mSelectedTee)   {
            case 0:
            default:
                return getmRedTee();
            case 1:
                return getmGoldTee();
            case 2:
                return getmBlackTee();
            case 3:
                return getmWhiteTee();
        }
    }

    private int mPar, mRedTee, mGoldTee, mBlackTee, mWhiteTee;


    public Hole(int mPar, int mRedTee, int mGoldTee, int mBlackTee, int mWhiteTee)  {
        this.mPar = mPar;
        this.mRedTee = mRedTee;
        this.mGoldTee = mGoldTee;
        this.mBlackTee = mBlackTee;
        this.mWhiteTee = mWhiteTee;
    }
}
