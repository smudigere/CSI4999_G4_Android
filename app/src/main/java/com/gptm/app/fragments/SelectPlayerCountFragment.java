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

import java.util.ArrayList;
import java.util.List;

public class SelectPlayerCountFragment extends Fragment implements
        AdapterView.OnItemSelectedListener{

    public static SelectPlayerCountFragment newInstance()    {

        SelectPlayerCountFragment fragment = new SelectPlayerCountFragment();

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
    }

    private void init_button() {

        mSubmit = mView.findViewById(R.id.submit_button);
        mSubmit.setOnClickListener((View.OnClickListener) mActivity);
    }


    private void init_spinner() {

        // Spinner element
        mSpinner = mView.findViewById(R.id.player_count_spinner);

        // Spinner click listener
        mSpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();

        for (int i = 1; i <= 10; i++)
            categories.add(String.valueOf(i));

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
