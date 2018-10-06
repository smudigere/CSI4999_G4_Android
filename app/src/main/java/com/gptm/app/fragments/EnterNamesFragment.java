package com.gptm.app.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gptm.app.R;

import java.util.ArrayList;
import java.util.List;

public class EnterNamesFragment extends Fragment {

    public static EnterNamesFragment newInstance(int playersCount)  {

        EnterNamesFragment fragment = new EnterNamesFragment();
        fragment.playersCount = playersCount;

        return fragment;
    }

    private Activity mActivity;

    private int playersCount;

    private View mView;
    private ListView mListView;
    private Button mSubmitButton;

    private ListViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_enter_names, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mView = view;
        mActivity = getActivity();

        init_list_view();
        init_button();
    }

    private void init_list_view()   {

        mListView = mView.findViewById(R.id.listView);

        adapter = new ListViewAdapter();
        mListView.setAdapter(adapter);

    }

    private void init_button()  {
        mSubmitButton = mView.findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < playersCount; i++)  {
                    EditText editText = mListView.getChildAt(i).findViewById(R.id.player_edit_text);
                    Log.i("edittext", editText.getText().toString());
                }

            }
        });
    }

    private class ListViewAdapter extends BaseAdapter {

        private List<EditText> mEditTexts;

        private ListViewAdapter()   {
            mEditTexts = new ArrayList<>(playersCount);
        }

        @Override
        public int getCount() {
            return playersCount;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater)
                        mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.players_name_enter_list, parent, false);
            }

            TextView mPlayerTextView = convertView.findViewById(R.id.player_text_view);
            mPlayerTextView.setText("Player " + (position + 1) + ":");
            mPlayerTextView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);



            mEditTexts.add(position, (EditText) convertView.findViewById(R.id.player_edit_text));
            //mEditTexts[position] = convertView.findViewById(R.id.player_edit_text);

            return convertView;
        }
    }
}