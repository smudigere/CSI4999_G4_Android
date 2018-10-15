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

public class EnterScoreRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private int totalPlayers;
    private Player[] players;

    public EnterScoreRecyclerAdapter(Context context, Player[] players, int totalPlayers)    {

        mContext = context;
        this.totalPlayers = totalPlayers;
        this.players = players;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mPlayerName;

        ViewHolder(View view) {
            super(view);

            try {

                mPlayerName = view.findViewById(R.id.player_name_text_view);

                final TextView mShotTextView = view.findViewById(R.id.shots_text_view);

                Button mAddButton = view.findViewById(R.id.increase_button);
                mAddButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int count = Integer.parseInt(mShotTextView.getText().toString());
                        count++;
                        mShotTextView.setText(String.valueOf(count));
                    }
                });

                Button mSubtractButton = view.findViewById(R.id.decrease_button);
                mSubtractButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int count = Integer.parseInt(mShotTextView.getText().toString());

                        if (count > 0)
                            count--;

                        mShotTextView.setText(String.valueOf(count));
                    }
                });

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder) holder).mPlayerName.setText(players[position].getPlayerName());
    }

    @Override
    public int getItemCount() {
        return totalPlayers;
    }
}
