package com.gptm.app.api;

import android.os.AsyncTask;
import android.util.Log;

import com.gptm.app.utility.HttpConnection;

import static com.gptm.app.api.HostInformation.HOST;
import static com.gptm.app.api.HostInformation.WAITTIME;

public class WaitTimeApi extends AsyncTask<String, String, Boolean> {

    private String API_RESULT;
    private Delegate delegate;

    public WaitTimeApi(Delegate delegate, String round, String hole) {

        this.delegate = delegate;

        String[] params = {"round", "hole"};

        String API_URL = HOST + WAITTIME;
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
            delegate.waitTime(API_RESULT);

    }

    public interface Delegate   {
        void waitTime(String json);
    }
}
