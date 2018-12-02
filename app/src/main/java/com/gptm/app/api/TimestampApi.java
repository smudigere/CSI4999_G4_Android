package com.gptm.app.api;

import android.os.AsyncTask;

import com.gptm.app.utility.HttpConnection;

import static com.gptm.app.api.HostInformation.HOST;
import static com.gptm.app.api.HostInformation.STARTROUND;
import static com.gptm.app.api.HostInformation.TIMESTAMP;


public class TimestampApi extends AsyncTask<String, String, Boolean> {

    private String API_RESULT;
    private Delegate delegate;

    public TimestampApi(TimestampApi.Delegate delegate, int round, int hole, boolean event) {

        this.delegate = delegate;
        String[] params = {"round", "hole", "event"};

        String API_URL = HOST + TIMESTAMP;
        API_URL += params[0] + "=" + round + "&";
        API_URL += params[1] + "=" + hole + "&";

        if (event)
            API_URL += params[2] + "=" + "start";
        else
            API_URL += params[2] + "=" + "end";

        execute(API_URL);
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {

            API_RESULT = HttpConnection.httpGetConnection(params[0]);

            return true;
        } catch (Exception ignored) {}
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        if (result)
            delegate.updateProgress(API_RESULT);

    }

    public interface Delegate   {
        void updateProgress(String data);
    }
}
