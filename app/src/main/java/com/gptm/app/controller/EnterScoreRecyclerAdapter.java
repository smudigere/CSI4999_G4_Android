package com.gptm.app.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gptm.app.EnterScoreActivity;
import com.gptm.app.R;

public class EnterScoreRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private int totalPlayers;

    public EnterScoreRecyclerAdapter(Context context, int totalPlayers)    {

        mContext = context;
        this.totalPlayers = totalPlayers;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {



        ViewHolder(View view) {
            super(view);

            try {



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

    }


    @Override
    public int getItemCount() {
        return totalPlayers;
    }
}
