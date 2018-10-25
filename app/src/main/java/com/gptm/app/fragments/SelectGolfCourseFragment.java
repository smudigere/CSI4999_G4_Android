package com.gptm.app.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.gptm.app.R;
import com.gptm.app.controller.SelectGolfCourseFragmentController;
import com.gptm.app.utility.Functions;

import java.util.ArrayList;
import java.util.List;

public class SelectGolfCourseFragment extends Fragment implements
        AdapterView.OnItemSelectedListener{

    public static SelectGolfCourseFragment newInstance()    {

        SelectGolfCourseFragment fragment = new SelectGolfCourseFragment();

        return fragment;
    }

    private View mView;

    private Spinner mSpinner;
    private Button mSubmit;

    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_player_count, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mActivity = getActivity();
        mView = view;

        init_button();
        init_spinner();

        new SelectGolfCourseFragmentController(mActivity, mSpinner);
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

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
