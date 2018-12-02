package com.gptm.app.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.gptm.app.R;
import com.gptm.app.api.CourseSearchApi;
import com.gptm.app.utility.Functions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SelectGolfCourseFragment extends Fragment implements
        AdapterView.OnItemSelectedListener,
        CourseSearchApi.Delegate{

    public static SelectGolfCourseFragment newInstance()    {

        SelectGolfCourseFragment fragment = new SelectGolfCourseFragment();

        return fragment;
    }

    List<String> places, placesId;

    private View mView;

    private Spinner mSpinner;
    private Button mSubmit;

    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_golf_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mActivity = getActivity();
        mView = view;

        init_button();
        init_spinner();

        new CourseSearchApi(this);
    }

    private void init_button() {

        mSubmit = mView.findViewById(R.id.submit_button);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Functions.fragment_replacement(
                        getFragmentManager(),
                        R.id.relative,
                        SelectPlayerCountFragment.newInstance(),
                        false
                );
            }
        });
    }


    private void init_spinner() {

        // Spinner element
        mSpinner = mView.findViewById(R.id.player_count_spinner);

        // Spinner click listener
        mSpinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Functions.selectCourseId(Integer.parseInt(placesId.get(i)));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    @Override
    public void allCourses(String json) {

        try {


            JSONArray courseArray = new JSONObject(json).getJSONArray("results");

            places = new ArrayList<>();
            placesId = new ArrayList<>();

            HashMap<String, String> courseIdNameMap = new HashMap<>();

            for (int i = 0; i < courseArray.length(); i++)  {

                if (courseArray.getJSONArray(i).getString(1).length() != 0) {
                    placesId.add(courseArray.getJSONArray(i).getString(0));
                    places.add(courseArray.getJSONArray(i).getString(1));

                    courseIdNameMap.put(courseArray.getJSONArray(i).getString(0),
                            courseArray.getJSONArray(i).getString(1));
                }
            }

            Functions.setCourseIdNameMap(courseIdNameMap);

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, places);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            mSpinner.setAdapter(dataAdapter);

        } catch (Exception ignored) {}
/*
        try {

            JSONObject courseArray = new JSONObject(json);
            places = new ArrayList<>();
            placesId = new ArrayList<>();

            HashMap<String, String> courseIdNameMap = new HashMap<>();

            for (Iterator<String> it = courseArray.keys(); it.hasNext(); ) {
                String id = it.next();

                if (courseArray.getString(id).length() != 0) {

                    placesId.add(id);
                    places.add(courseArray.getString(id));

                    courseIdNameMap.put(id, courseArray.getString(id));

                    Log.i(id, courseArray.getString(id));
                }
            }

            Functions.setCourseIdNameMap(courseIdNameMap);

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, places);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            mSpinner.setAdapter(dataAdapter);

        } catch (Exception ignored) {}
        */
    }
}
