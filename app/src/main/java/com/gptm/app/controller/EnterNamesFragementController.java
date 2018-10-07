package com.gptm.app.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.gptm.app.EnterScoreActivity;

public class EnterNamesFragementController extends AsyncTask<Void, Void, String> {

    private Activity mActivity;

    public EnterNamesFragementController(Activity activity)  {
        mActivity = activity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        mActivity.startActivity(new Intent(mActivity, EnterScoreActivity.class));
    }
}