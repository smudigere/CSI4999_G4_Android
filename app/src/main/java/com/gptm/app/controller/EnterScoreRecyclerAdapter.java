package com.gptm.app.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gptm.app.R;
import com.gptm.app.model.Player;

import java.util.HashMap;

public class EnterScoreRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private int totalPlayers, holeNumber;
    private Player[] players;
    private HashMap<Integer, Integer> mScoreMap;

    public EnterScoreRecyclerAdapter(Context context, Player[] players, int totalPlayers, int holeNumber)    {

        mContext = context;
        this.totalPlayers = totalPlayers;
        this.holeNumber = holeNumber;
        this.players = players;
        mScoreMap = new HashMap<>();

        for (int i = 0; i < players.length; i++)
            mScoreMap.put(i, 0);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mPlayerName, mShotTextView;
        private Button mAddButton, mSubtractButton;

        ViewHolder(View view) {
            super(view);

            try {

                mPlayerName = view.findViewById(R.id.player_name_text_view);
                mShotTextView = view.findViewById(R.id.shots_text_view);
                mAddButton = view.findViewById(R.id.increase_button);
                mSubtractButton = view.findViewById(R.id.decrease_button);

            } catch (Exception ignored) {}
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.score_enter_list, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.mPlayerName.setText(players[position].getPlayerName());
        viewHolder.mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(viewHolder.mShotTextView.getText().toString());
                count++;
                viewHolder.mShotTextView.setText(String.valueOf(count));
                mScoreMap.put(position, count);
            }
        });

        viewHolder.mSubtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(viewHolder.mShotTextView.getText().toString());

                if (count > 0)
                    count--;

                viewHolder.mShotTextView.setText(String.valueOf(count));
                mScoreMap.put(position, count);
            }
        });
    }

    public HashMap<Integer, Integer> getmScoreMap() {
        return mScoreMap;
    }

    @Override
    public int getItemCount() {
        return totalPlayers;
    }
}