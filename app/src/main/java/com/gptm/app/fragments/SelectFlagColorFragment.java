package com.gptm.app.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gptm.app.R;
import com.gptm.app.api.CourseInfoApi;
import com.gptm.app.controller.SelectFlagColorController;
import com.gptm.app.model.CourseInfo;
import com.gptm.app.model.Player;
import com.gptm.app.utility.CustomTypefaceSpan;
import com.gptm.app.utility.FontManager;
import com.gptm.app.utility.Functions;

import java.util.ArrayList;
import java.util.List;

import static com.gptm.app.utility.Functions.mCourseInfo;

public class SelectFlagColorFragment extends Fragment implements
        AdapterView.OnItemSelectedListener,
        CourseInfoApi.Delegate{

    public static SelectFlagColorFragment newInstance()    {

        SelectFlagColorFragment fragment = new SelectFlagColorFragment();

        return fragment;
    }

    private View mView;

    private Spinner mSpinner;
    private Button mSubmit;

    private Player[] mPlayers;

    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_flag_color, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mActivity = getActivity();
        mView = view;

        mPlayers = Functions.getPlayers(mActivity);

        init_button();
        init_spinner();

        new CourseInfoApi(this, Functions.getSelectedCourseId());
    }

    private void init_button() {

        mSubmit = mView.findViewById(R.id.submit_button);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SelectFlagColorController(mActivity);
            }
        });
    }


    private void init_spinner() {

        // Spinner element
        mSpinner = mView.findViewById(R.id.player_count_spinner);

        // Spinner click listener
        mSpinner.setOnItemSelectedListener(this);
        //init_spinner_adapter();
    }

    private void init_spinner_adapter(List<String> flags) {
        try {

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mActivity, R.layout.my_spinner, flags);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            mSpinner.setAdapter(dataAdapter);

        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        mCourseInfo.setmSelectedTee(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    @Override
    public void courseInfo(String json) {
        try {

            mCourseInfo = new CourseInfo(json);

            // Spinner Drop down elements
            List<String> flags = new ArrayList<>();
            flags.add("RED - "+ mCourseInfo.getmTotalRed() + " Y");
            flags.add("GOLD - "+ mCourseInfo.getmTotalGold() + " Y");
            flags.add("BLACK - "+ mCourseInfo.getmTotalBlack() + " Y");
            flags.add("WHITE - "+ mCourseInfo.getmTotalWhite() + " Y");

            init_spinner_adapter(flags);
        } catch (Exception ignored) {}
    }
}
