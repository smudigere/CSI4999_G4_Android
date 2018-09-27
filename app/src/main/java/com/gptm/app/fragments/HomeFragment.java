package com.gptm.app.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gptm.app.MainActivity;
import com.gptm.app.R;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance()    {

        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    private View mView;
    private Button mPlayButton;

    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mActivity =  getActivity();
        mView = view;

        init_button();
    }

    private void init_button()  {
        mPlayButton = mView.findViewById(R.id.play_button);
        mPlayButton.setOnClickListener((View.OnClickListener) mActivity);
    }
}