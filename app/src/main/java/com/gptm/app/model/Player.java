package com.gptm.app.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements
        Parcelable{

    private String playerName;

    public Player(String playerName)    {
        this.playerName = playerName;
    }

    private Player(Parcel in) {
        playerName = in.readString();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(playerName);
    }
}
