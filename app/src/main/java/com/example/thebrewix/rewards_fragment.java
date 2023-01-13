package com.example.thebrewix;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class rewards_fragment extends Fragment {

    public static TextView reward;
    int rewardValue;
    SQLiteDatabase DBReader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rewards_fragment,container,false);

        BrewixDBHelper brewixDBHelper = new BrewixDBHelper(getActivity());
        DBReader = brewixDBHelper.getReadableDatabase();

        reward = view.findViewById(R.id.rewardsValue);
        rewardValue = getRewards();
        reward.setText(String.valueOf(rewardValue));

        return view;
    }

    //Retrieve current user rewards points from db (select)
    private int getRewards(){
        int value;
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
        BrewixDBHelper dbHelper = new BrewixDBHelper(getActivity());
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
}
