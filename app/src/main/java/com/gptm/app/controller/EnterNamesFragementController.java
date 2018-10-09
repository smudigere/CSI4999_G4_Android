package com.gptm.app.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.gptm.app.EnterScoreActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class EnterNamesFragementController extends AsyncTask<Void, Void, String> {

    @SuppressLint("StaticFieldLeak")
    private Activity mActivity;
    private Bundle mBundle;

    public EnterNamesFragementController(Activity activity, JSONObject playersJsonObj)  {

        try {

            mActivity = activity;

            mBundle = new Bundle();
            mBundle.putString("Players", playersJsonObj.toString());

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

        Intent mIntent = new Intent(mActivity, EnterScoreActivity.class);
        mIntent.putExtras(mBundle);

        mActivity.startActivity(mIntent);
    }
}