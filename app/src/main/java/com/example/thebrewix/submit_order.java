package com.example.thebrewix;

import static com.example.thebrewix.review_order.ORDER_CODE;
import static com.example.thebrewix.review_order.SUBMIT_CODE;
import static com.example.thebrewix.review_order.pass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;

public class submit_order extends AppCompatActivity {


    ProgressBar loading;
    String valueString;
    int value;
    final Handler handler = new Handler(Looper.getMainLooper());
    SQLiteDatabase DBReader, mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);

        //Process order and redirect to corresponding activity/fragment
        loading = findViewById(R.id.progressBar1);
        updateRewards();
        redirect();

    }

    protected void redirect(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( submit_order.this, home.class);
                intent.putExtra(pass, SUBMIT_CODE);
                startActivity(intent);
            }
        }, 2000);
    }

    //Retrieve current user rewards points from db (select)
    private int getRewards(){
        int value;
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        DBReader = dbHelper.getReadableDatabase();
        Cursor cursor = DBReader.rawQuery("SELECT " + BrewixContract.UserData.COLUMN_REWARDS + " FROM " + BrewixContract.UserData.TABLE_NAME + " WHERE " + BrewixContract.UserData.COLUMN_EMAIL + " =?", new String[]{getCurrentUser()});
        if(cursor.moveToFirst()){
            value = cursor.getInt(0);
        }else{
            return 0;
        }
        return value;
    }

    //Retrieve current user status from db (select)
    private String getCurrentUser(){
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        DBReader = dbHelper.getReadableDatabase();
        String current;
        Cursor cursor = DBReader.rawQuery("SELECT " + BrewixContract.Status.COLUMN_EMAIL + " FROM " + BrewixContract.Status.TABLE_NAME, null);
        if(cursor.moveToFirst()){
            current = cursor.getString(0);
        }else {
            return "Error";
        }
        return current;
    }

    //Update user rewards by 50 points to db (update)
    private void updateRewards(){
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        mDB = dbHelper.getWritableDatabase();
        int rewardTemp = getRewards();
        rewardTemp+=50;
        mDB.execSQL("UPDATE " + BrewixContract.UserData.TABLE_NAME + " SET " + BrewixContract.UserData.COLUMN_REWARDS + " = " + rewardTemp + " WHERE " + BrewixContract.UserData.COLUMN_EMAIL + " =? ", new String[]{getCurrentUser()});
    }
}