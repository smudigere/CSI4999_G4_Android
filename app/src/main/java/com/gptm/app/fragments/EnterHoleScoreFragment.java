package com.gptm.app.fragments;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

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
import com.gptm.app.utility.Functions;

import org.json.JSONObject;

import static com.gptm.app.utility.Functions.mCourseInfo;

public class EnterHoleScoreFragment extends Fragment implements
        View.OnClickListener,
        WaitTimeApi.Delegate,
        EndRoundApi.Delegate,
        TimestampApi.Delegate, DialogInterface.OnDismissListener, DialogInterface.OnShowListener {

    public static EnterHoleScoreFragment newInstance(int holeNumber) {

        EnterHoleScoreFragment fragment = new EnterHoleScoreFragment();
        fragment.holeNumber = holeNumber + 1;

        return fragment;
    }

    private int holeNumber;

    private EnterScoreActivity mActivity;
    private EnterScoreRecyclerAdapter adapter;

    private View mView;

    private AlertDialog dialog;
    private boolean shouldDialogDismiss;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_enter_hole_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new WaitTimeApi(this, Functions.roundId, holeNumber - 1);

        mActivity = (EnterScoreActivity) getActivity();
        mView = view;

        Glide.with(this).load("https://wallpapercave.com/wp/eOZbIG4.jpg").into((ImageView) view.findViewById(R.id.image_view));

        init_ui();
        init_dialog();
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


    private void init_dialog()  {

        dialog = new AlertDialog.Builder(mActivity)
                //.setView(v)
                .setTitle(R.string.app_name)
                //.setMessage("Your est wait time")
                .setPositiveButton(R.string.Try_Again, null) //Set to null. We override the onclick
                //.setNegativeButton(android.R.string.cancel, null)
                .create();
        dialog.setOnShowListener(this);
        dialog.setOnDismissListener(this);
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

            JSONObject jsonObject = new JSONObject(json);
            int esttime = (jsonObject.getInt("esttime")) / 60;
            int ahead = jsonObject.getInt("ahead");

            if (esttime != 0) {
                dialog.setMessage("Your estimated wait time is: " + esttime + " minutes\n" +
                        ahead + " rounds playing ahead of you.");
                shouldDialogDismiss = false;
                dialog.show();
            } else {
                new TimestampApi(this, Functions.roundId, (holeNumber - 1), true);
                shouldDialogDismiss = true;
                dialog.dismiss();
            }


        } catch (Exception ignored) {}
    }

    @Override
    public void endRound(String json) {
        try {

            Log.i(getClass().toString(), json);

        } catch (Exception ignored) {}
    }

    @Override
    public void updateProgress(String data, boolean event) {
        try {
            if (event)
                Log.i("", "");
 //               new WaitTimeApi(this, Functions.roundId, holeNumber - 1);
        } catch (Exception ignored) {}
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        if (shouldDialogDismiss)
            dialog.dismiss();
        else
            dialog.show();

    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
        Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                new WaitTimeApi(EnterHoleScoreFragment.this, Functions.roundId, holeNumber - 1);
                //Toast.makeText(mActivity, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}