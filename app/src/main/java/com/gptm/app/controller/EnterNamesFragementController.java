package com.gptm.app.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;

import com.gptm.app.EnterScoreActivity;
import com.gptm.app.model.Player;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class EnterNamesFragementController extends AsyncTask<Void, Void, String> {

    @SuppressLint("StaticFieldLeak")
    private Activity mActivity;
    private Intent mIntent;

    public EnterNamesFragementController(Activity activity, Player[] players)  {

        try {

            mActivity = activity;

            mIntent = new Intent(mActivity, EnterScoreActivity.class);
            mIntent.putExtra("PLAYERS", players);

            execute();
        } catch (Exception e)   {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        mActivity.startActivity(mIntent);
    }
}