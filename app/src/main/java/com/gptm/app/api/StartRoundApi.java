package com.gptm.app.api;

import android.os.AsyncTask;

import com.gptm.app.utility.HttpConnection;

import static com.gptm.app.api.HostInformation.HOST;
import static com.gptm.app.api.HostInformation.STARTROUND;


public class StartRoundApi extends AsyncTask<String, String, Boolean> {

    private String API_RESULT;
    private Delegate delegate;

    public StartRoundApi(StartRoundApi.Delegate delegate, String course, String user) {

        this.delegate = delegate;
        String[] params = {"course", "user"};

        String API_URL = HOST + STARTROUND;
        API_URL += params[0] + "=" + course;

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
            delegate.deliverData(API_RESULT);

    }

    public interface Delegate   {
        void deliverData(String data);
    }
}
