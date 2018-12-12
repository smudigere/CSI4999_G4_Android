package com.gptm.app.api;

import android.os.AsyncTask;

import com.gptm.app.utility.Functions;
import com.gptm.app.utility.HttpConnection;

import static com.gptm.app.api.HostInformation.HOST;
import static com.gptm.app.api.HostInformation.STARTROUND;


public class StartRoundApi extends AsyncTask<String, String, Boolean> {

    private String API_RESULT;
    private Delegate delegate;

    public StartRoundApi(StartRoundApi.Delegate delegate, int course, int playerCount) {

        this.delegate = delegate;
        String[] params = {"course", "players"};

        String API_URL = HOST + STARTROUND;
        API_URL += params[0] + "=" + course + "&";
        API_URL += params[1] + "=" + playerCount;

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
            delegate.startRound(API_RESULT);

    }

    public interface Delegate   {
        void startRound(String data);
    }
}
