package com.gptm.app.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gptm.app.model.Player;

public class PlayersSql extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "dunhamsSports_Location_Coupons.db";
    private static final String TABLE_NAME = "coupons_location_list";

    public PlayersSql(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db <p> The database. </p>
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME STRING)");
    }

    /**
     * Called when the database needs to be upgraded.
     *
     * @param db    <p> The database. </p>
     * @param oldVersion    <p> The old database version. </p>
     * @param newVersion    <p> The new database version.  </p>
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public synchronized void  insertPlayers(Player[] players) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE from " + TABLE_NAME);

//ALTER TABLE users AUTO_INCREMENT=1001
        for (Player player: players) {

            ContentValues contentValues = new ContentValues();
            contentValues.put("NAME", player.getPlayerName());
            db.insert(TABLE_NAME, null, contentValues);
        }

        db.close();
    }

    /**
     * Retrive data from the database.
     *
     * @return  <p> All data. </p>
     */
    public Player[] getPlayers()   {

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select NAME from " + TABLE_NAME, null);

            if (res != null)
                res.moveToFirst();

            if (res != null)    {
                Player[] players = new Player[res.getCount()];

                Log.i("Res count", String.valueOf(res.getCount()));

                for (int i = 0; i < players.length; i++) {
                    players[i] = new Player(res.getString(res.getColumnIndex("NAME")));
                    res.moveToNext();
                    Log.i("Players" + i, players[i].getPlayerName());
                }

                res.close();
                db.close();

                return players;
            }
        } catch (Exception e)   {
            e.printStackTrace();
        }
        return null;
    }
}