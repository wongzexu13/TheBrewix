package com.example.thebrewix;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.thebrewix.BrewixContract.*;

import androidx.annotation.Nullable;

public class BrewixDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "brewix.db";
    public static final int DATABASE_VERSION = 1;

    public BrewixDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ITEMLIST_TABLE = "CREATE TABLE " +
                ItemEntry.TABLE_NAME + " (" +
                ItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ItemEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                ItemEntry.COLUMN_NOTE + " TEXT, " +
                ItemEntry.COLUMN_PRICE + " DOUBLE NOT NULL " +
                " );";

        final String SQL_CREATE_USERDATA_TABLE = "CREATE TABLE " +
                UserData.TABLE_NAME + " (" +
                UserData._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UserData.COLUMN_NAME + " TEXT NOT NULL, " +
                UserData.COLUMN_EMAIL + " TEXT NOT NULL, " +
                UserData.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                UserData.COLUMN_MOBILE + " TEXT, " +
                UserData.COLUMN_REWARDS + " INTEGER NOT NULL " +
                " );";

        final String SQL_CREATE_STATUS_TABLE = "CREATE TABLE " +
                Status.TABLE_NAME + " (" +
                Status._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Status.COLUMN_EMAIL + " TEXT " +
                " );";

        db.execSQL(SQL_CREATE_ITEMLIST_TABLE);
        db.execSQL(SQL_CREATE_USERDATA_TABLE);
        db.execSQL(SQL_CREATE_STATUS_TABLE);

    }

    //Drop table if exists in db
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + ItemEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserData.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Status.TABLE_NAME);
        onCreate(db);
    }

}
