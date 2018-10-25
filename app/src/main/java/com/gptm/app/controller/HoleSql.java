package com.gptm.app.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HoleSql extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "dunhamsSports_Location_Coupons.db";
    private static final String TABLE_NAME = "coupons_location_list";

    HoleSql(Context context) {
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

        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT)");
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


    /**
     * Insert Data of the jobs in the database.
     *
     * @param coupons <p> ALl data from get_coupons_from_location </p>
     */
    void insertData(String coupons) {
/*
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE from " + TABLE_NAME);
        ContentValues contentValues = new ContentValues();
        contentValues.put("coupons", coupons);
        db.insert(TABLE_NAME, null, contentValues);*/
    }

    /**
     * Retrive data from the database.
     *
     * @return  <p> All data. </p>
     */
    public String getAllData()   {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select coupons from " + TABLE_NAME, null);


        if (res != null) {
            res.moveToFirst();
        }
        String result = null;
        try {

            if (res != null) {
                result = res.getString(res.getColumnIndex("coupons"));
                res.close();
            }
        } catch (Exception ignored) {}

        return result;
    }

}
