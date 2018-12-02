package com.gptm.app.api;

import android.os.AsyncTask;
import android.util.Log;

import com.gptm.app.utility.HttpConnection;

import static com.gptm.app.api.HostInformation.HOST;
import static com.gptm.app.api.HostInformation.ENDROUND;

public class EndRoundApi extends AsyncTask<String, String, Boolean> {

    private String API_RESULT;
    private Delegate delegate;

    public EndRoundApi(Delegate delegate, int round) {

        this.delegate = delegate;

        String[] params = {"round"};

        String API_URL = HOST + ENDROUND;
        API_URL += params[0] + "=" + round;

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
            delegate.endRound(API_RESULT);

    }

    public interface Delegate   {
        void endRound(String json);
    }
}
