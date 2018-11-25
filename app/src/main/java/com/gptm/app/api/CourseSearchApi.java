package com.gptm.app.api;

import android.os.AsyncTask;
import android.util.Log;

import com.gptm.app.utility.HttpConnection;

import static com.gptm.app.api.HostInformation.COURSESEARCH;
import static com.gptm.app.api.HostInformation.HOST;

public class CourseSearchApi extends AsyncTask<String, String, Boolean> {

    private String API_RESULT;
    CourseSearchApi.Delegate delegate;

    public CourseSearchApi(CourseSearchApi.Delegate delegate) {

        this.delegate = delegate;
        String API_URL = HOST + COURSESEARCH;

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
            delegate.allCourses(API_RESULT);

    }

    public interface Delegate   {
        void allCourses(String json);
    }
}
