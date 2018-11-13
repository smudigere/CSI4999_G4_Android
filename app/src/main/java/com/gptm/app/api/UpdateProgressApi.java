package com.gptm.app.api;

import android.os.AsyncTask;
import android.util.Log;

import com.gptm.app.utility.HttpConnection;

import static com.gptm.app.api.HostInformation.HOST;
import static com.gptm.app.api.HostInformation.UPDATEPROGRESS;

public class UpdateProgressApi extends AsyncTask<String, String, Boolean> {

    private String API_RESULT;

    public UpdateProgressApi(String round, String hole) {

        String[] params = {"round", "hole"};

        String API_URL = HOST + UPDATEPROGRESS;
        API_URL += params[0] + "=" + round + "&";
        API_URL += params[1] + "=" + hole;

        execute(API_URL);
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {

            API_RESULT = HttpConnection.httpConnection(params[0]);

            return true;
        } catch (Exception ignored) {}
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        if (result)
            Log.i(getClass().toString(), API_RESULT);

    }
}
