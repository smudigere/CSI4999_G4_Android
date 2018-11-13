package com.gptm.app.fragments;

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
import android.widget.TextView;

import com.gptm.app.EnterScoreActivity;
import com.gptm.app.R;
import com.gptm.app.controller.EnterScoreRecyclerAdapter;
import com.gptm.app.controller.ScoreTrackMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class EnterHoleScoreFragment extends Fragment implements
        View.OnClickListener    {

    public static EnterHoleScoreFragment newInstance(int holeNumber, int totalHoleCount) {

        EnterHoleScoreFragment fragment = new EnterHoleScoreFragment();
        fragment.holeNumber = holeNumber + 1;
        fragment.totalHoleCount = totalHoleCount;

        return fragment;
    }

    private int holeNumber, totalHoleCount, clickCounter;

    private EnterScoreActivity mActivity;
    private EnterScoreRecyclerAdapter adapter;

    private View mView;

    private JSONObject[] jsonObjects;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_enter_hole_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mActivity = (EnterScoreActivity) getActivity();
        mView = view;

        clickCounter = 0;
        jsonObjects = new JSONObject[3];
        try {
            jsonObjects[0] = new JSONObject("{\"playing\":3,\"waiting\":2,\"esttime\":350}");
            jsonObjects[1] = new JSONObject("{\"playing\":3,\"waiting\":2,\"esttime\":340}");
            jsonObjects[2] = new JSONObject("{\"playing\":3,\"waiting\":1,\"esttime\":240}");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        init_ui();
    }

    private void init_ui()  {

        TextView mHoleNumTextView = mView.findViewById(R.id.hole_num_text_view);
        mHoleNumTextView.setText("Hole " + holeNumber);

        String[] parText = {
                "Par 4",
                "Par 3",
                "Par 4",
                "Par 4",
                "Par 4"
        };
        TextView mParNumTextView = mView.findViewById(R.id.par_num_text_view);
        mParNumTextView.setText(parText[holeNumber - 1]);

        String[] yardText = {
                "376 Yards",
                "130 Yards",
                "279 Yards",
                "324 Yards",
                "325 Yards"
        };
        TextView mYardNumTextView = mView.findViewById(R.id.yard_num_text_view);
        mYardNumTextView.setText(yardText[holeNumber - 1]);

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
        mNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.next_button:

                if (holeNumber == 2)    {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    try {
                        long waitTime = TimeUnit.SECONDS.toMinutes(Long.parseLong(jsonObjects[clickCounter].getString("esttime")));

                        builder.setTitle("Estimated Wait Time")
                                .setMessage("There are " + jsonObjects[clickCounter].getString("waiting") + " rounds playing ahead of you.\nYour estimated" +
                                        " wait time is " + waitTime + " minutes.")
                                .show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    clickCounter++;
                } else {
                    ScoreTrackMap.getInstance().getmScoreMapList().add(holeNumber - 1, adapter.getmScoreMap());
                    mActivity.addEnterHoleScoreFragment(holeNumber);
                }

                break;
        }
    }
}