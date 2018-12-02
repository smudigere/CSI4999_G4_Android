package com.gptm.app.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gptm.app.EnterScoreActivity;
import com.gptm.app.GridScoreActivity;
import com.gptm.app.R;
import com.gptm.app.api.EndRoundApi;
import com.gptm.app.api.TimestampApi;
import com.gptm.app.api.WaitTimeApi;
import com.gptm.app.controller.EnterScoreRecyclerAdapter;
import com.gptm.app.controller.HoleCount;
import com.gptm.app.controller.ScoreTrackMap;
import com.gptm.app.utility.FontManager;
import com.gptm.app.utility.Functions;

import org.json.JSONException;
import org.json.JSONObject;

import static com.gptm.app.utility.Functions.mCourseInfo;

public class EnterHoleScoreFragment extends Fragment implements
        View.OnClickListener,
        WaitTimeApi.Delegate,
        EndRoundApi.Delegate,
        TimestampApi.Delegate{

    public static EnterHoleScoreFragment newInstance(int holeNumber) {

        EnterHoleScoreFragment fragment = new EnterHoleScoreFragment();
        fragment.holeNumber = holeNumber + 1;

        return fragment;
    }

    private int holeNumber;

    private EnterScoreActivity mActivity;
    private EnterScoreRecyclerAdapter adapter;

    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_enter_hole_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new TimestampApi(this, Functions.roundId, holeNumber, true);

        mActivity = (EnterScoreActivity) getActivity();
        mView = view;

        Typeface iconFont = FontManager.getTypeface(mActivity, FontManager.FONTAWESOME);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        /*
        try {

            builder.setTitle("")
                    .setMessage("Other rounds waiting in line. Please move faster!")
                    .show();

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        Glide.with(this).load("https://wallpapercave.com/wp/eOZbIG4.jpg").into((ImageView) view.findViewById(R.id.image_view));

        init_ui();
    }

    private void init_ui()  {

        TextView mHoleNumTextView = mView.findViewById(R.id.hole_num_text_view);
        mHoleNumTextView.setText("Hole " + holeNumber);

        TextView mParNumTextView = mView.findViewById(R.id.par_num_text_view);
        mParNumTextView.setText("Par " +
                String.valueOf(mCourseInfo.getmHoles()[holeNumber - 1].getmPar())
        );

        TextView mYardNumTextView = mView.findViewById(R.id.yard_num_text_view);
        mYardNumTextView.setText(
                String.valueOf(
                        mCourseInfo.getmHoles()[holeNumber - 1].getDistance(mCourseInfo.getmSelectedTee())
                ) + " Yards"
        );

        RecyclerView mRecylerView = mView.findViewById(R.id.recylerView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecylerView.setLayoutManager(mLayoutManager);

        adapter =
                new EnterScoreRecyclerAdapter(
                        mActivity,
                        mActivity.getPlayers(),
                        mActivity.getPlayers().length,
                        (holeNumber - 1));

        mRecylerView.setAdapter(adapter);

        Button mNextButton = mView.findViewById(R.id.next_button);

        if (holeNumber == HoleCount.getInstance().getHoleCount())
            mNextButton.setText("End Game");

        mNextButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.next_button:
                ScoreTrackMap.getInstance().getmScoreMapList().add(holeNumber - 1, adapter.getmScoreMap());

                new TimestampApi(this, Functions.roundId, holeNumber - 1, false);

                if (holeNumber == HoleCount.getInstance().getHoleCount()) {

                    Intent mIntent = new Intent(mActivity, GridScoreActivity.class);
                    startActivity(mIntent);

                    new EndRoundApi(this, Functions.roundId);
                } else
                    mActivity.addEnterHoleScoreFragment(holeNumber);

                break;
        }
    }

    @Override
    public void waitTime(String json) {
        try {

            Log.i(getClass().toString(), json);

        } catch (Exception ignored) {}
    }

    @Override
    public void endRound(String json) {
        try {

            Log.i(getClass().toString(), json);

        } catch (Exception ignored) {}
    }

    @Override
    public void updateProgress(String data) {
        try {

            Log.i(getClass().toString(), data);

        } catch (Exception ignored) {}
    }
}