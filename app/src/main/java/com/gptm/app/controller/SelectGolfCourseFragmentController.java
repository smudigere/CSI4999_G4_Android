package com.gptm.app.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.gptm.app.utility.HttpConnection;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class SelectGolfCourseFragmentController extends AsyncTask<Void, Void, Boolean> {

    private String API_RESULT;

    private Context context;
    private Spinner spinner;

    public SelectGolfCourseFragmentController(Context context, Spinner spinner) {

        this.context = context;
        this.spinner = spinner;

        execute();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {

            API_RESULT = HttpConnection.httpConnection(
                    "http://www.williambarden.com/Eagle/gis/nearestcourse.php?lat=42.6896593&lon=-83.2154821"
            );

            return true;
        } catch (Exception ignored)   {}

        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        try {

            //if (result)
                init_spinner_adapter();
            //else
                //Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_SHORT).show();

        } catch (Exception ignored) {}
    }

    private void init_spinner_adapter() {
        try {

            //JSONArray gcArray = new JSONArray(API_RESULT);

            // Spinner Drop down elements
            List<String> places = new ArrayList<>();

            //for (int i = 0; i < gcArray.length(); i++)
            places.add("Fieldstone Golf Club");
            places.add("Radrick Farms");

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, places);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);

        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }
}
