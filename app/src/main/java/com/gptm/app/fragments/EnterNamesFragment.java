package com.gptm.app.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gptm.app.R;

public class EnterNamesFragment extends Fragment {

    public static EnterNamesFragment newInstance(int playersCount)  {

        EnterNamesFragment fragment = new EnterNamesFragment();
        fragment.playersCount = playersCount;

        return fragment;
    }

    private int playersCount;

    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_enter_names, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mView = view;

        init_ui_array();
    }


    private void init_ui_array() {
        init_edit_text_array();
        init_text_view_array();
    }

    private void init_edit_text_array() {
        EditText[] mEditTextPlayers = new EditText[playersCount];

        RelativeLayout relativeLayout = mView.findViewById(R.id.fragment_relative);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < playersCount; i++) {
            //Pass the parent as the second parameter to retain layout attributes
            mEditTextPlayers[i] = new EditText(getContext());
            mEditTextPlayers[i].setLayoutParams(params);
            mEditTextPlayers[i].setId(i + 100);
            mEditTextPlayers[i].setImeOptions(EditorInfo.IME_ACTION_GO);

            Log.i("View ID", String.valueOf(mEditTextPlayers[i].getId()));

            if (i != 0) {

                RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params1.addRule(RelativeLayout.BELOW, mEditTextPlayers[i - 1].getId());

                mEditTextPlayers[i].setLayoutParams(params1);
            }

            relativeLayout.addView(mEditTextPlayers[i]);
        }
    }

    private void init_text_view_array() {
        TextView[] mTextViewPlayers = new TextView[playersCount];

        RelativeLayout relativeLayout = mView.findViewById(R.id.fragment_relative);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < playersCount; i++) {
            //Pass the parent as the second parameter to retain layout attributes
            mTextViewPlayers[i] = new TextView(getContext());
            mTextViewPlayers[i].setText("Player " + (i + 1));
            mTextViewPlayers[i].setLayoutParams(params);
            mTextViewPlayers[i].setId(i + 200);
            mTextViewPlayers[i].setImeOptions(EditorInfo.IME_ACTION_GO);


            if (i != 0) {

                RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params1.addRule(RelativeLayout.BELOW, mTextViewPlayers[i - 1].getId());

                mTextViewPlayers[i].setLayoutParams(params1);
            }

            relativeLayout.addView(mTextViewPlayers[i]);
        }
    }
}
