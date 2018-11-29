package com.gptm.app.api;

import android.os.AsyncTask;
import android.util.Log;

import com.gptm.app.utility.HttpConnection;

import static com.gptm.app.api.HostInformation.COURSEINFO;
import static com.gptm.app.api.HostInformation.COURSESEARCH;
import static com.gptm.app.api.HostInformation.HOST;

public class CourseInfoApi extends AsyncTask<String, String, Boolean> {

    private String API_RESULT;
    private CourseInfoApi.Delegate delegate;

    public CourseInfoApi(Delegate delegate, int id) {

        String[] params = {"id"};

        this.delegate = delegate;
        String API_URL = HOST + COURSEINFO;
        API_URL += params[0] + "=" + id;

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
            delegate.courseInfo(API_RESULT);

    }

    public interface Delegate   {
        void courseInfo(String json);
    }
}
