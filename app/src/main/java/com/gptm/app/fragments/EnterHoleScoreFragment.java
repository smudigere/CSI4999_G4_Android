package com.gptm.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gptm.app.EnterScoreActivity;
import com.gptm.app.R;
import com.gptm.app.controller.EnterScoreRecyclerAdapter;

public class EnterHoleScoreFragment extends Fragment implements
        View.OnClickListener    {

    public static EnterHoleScoreFragment newInstance(int holeNumber, int totalHoleCount) {

        EnterHoleScoreFragment fragment = new EnterHoleScoreFragment();
        fragment.holeNumber = holeNumber + 1;
        fragment.totalHoleCount = totalHoleCount;

        return fragment;
    }

    private int holeNumber, totalHoleCount;

    private EnterScoreActivity mActivity;

    private View mView;
    private TextView mHoleNumTextView;
    private Button mNextButton;
    private RecyclerView mRecylerView;

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

        init_ui();
    }

    private void init_ui()  {

        mHoleNumTextView = mView.findViewById(R.id.hole_num_text_view);
        mHoleNumTextView.setText("Hole " + holeNumber);

        mRecylerView = mView.findViewById(R.id.recylerView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecylerView.setLayoutManager(mLayoutManager);

        mRecylerView.setAdapter(new EnterScoreRecyclerAdapter(mActivity, mActivity.getPlayers(), mActivity.getPlayers().length));

        mNextButton = mView.findViewById(R.id.next_button);
        mNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.next_button:
                mActivity.addEnterHoleScoreFragment(holeNumber);
                break;
        }
    }
}