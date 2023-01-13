package com.example.thebrewix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    ImageView back;
    TextView changePassword, feedback;
    EditText name, email, mobile;
    Button update;
    CheckBox emailPre;
    public static String currentUser;
    String nameTemp,emailTemp, mobileTemp;
    SQLiteDatabase mDB, DBReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        back = findViewById(R.id.backBtn);
        changePassword = findViewById(R.id.changePassword);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        update = findViewById(R.id.update);
        feedback = findViewById(R.id.feedback);
        emailPre = findViewById(R.id.emailPre);
        emailPre.setChecked(true);

        //Retrieve current user from db
        currentUser = getCurrentUser();

        //Update and show current user details in textHint
        email.setHint(currentUser);
        name.setHint(getNameHint());
        mobile.setHint(getMobileHint());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile.super.onBackPressed();
            }
        });

        //Intent to change password activity
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, change_password.class);
                startActivity(intent);
            }
        });

        //Update current user details with validations
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameTemp = name.getText().toString();
                emailTemp = email.getText().toString();
                mobileTemp = mobile.getText().toString();

                if(nameTemp.trim().length()==0){
                    nameTemp = getNameHint();
                }
                if(emailTemp.trim().length()==0) {
                    emailTemp = getCurrentUser();
                }
                if(mobileTemp.trim().length()==0) {
                    mobileTemp = getMobileHint();
                }

                update();
                email.setHint(getCurrentUser());
                name.setHint(getNameHint());
                mobile.setHint(getMobileHint());
                feedback.setText("Profile Updated");
                feedback.setVisibility(View.VISIBLE);
                currentUser = getCurrentUser();
            }
        });
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

    //Retrieve user name from db according to current user status (select)
    private String getNameHint(){
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        DBReader = dbHelper.getReadableDatabase();
        String name;
        Cursor cursor = DBReader.rawQuery("SELECT " + BrewixContract.UserData.COLUMN_NAME + " FROM " + BrewixContract.UserData.TABLE_NAME + " WHERE " + BrewixContract.UserData.COLUMN_EMAIL + " =?", new String[]{getCurrentUser()});
        if(cursor.moveToFirst()){
            name = cursor.getString(0);
        }else {
            return "Error";
        }
        return name;
    }

    //Retrieve user mobile from db according to current user status (select)
    private String getMobileHint(){
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        DBReader = dbHelper.getReadableDatabase();
        String mobile;
        Cursor cursor = DBReader.rawQuery("SELECT " + BrewixContract.UserData.COLUMN_MOBILE + " FROM " + BrewixContract.UserData.TABLE_NAME + " WHERE " + BrewixContract.UserData.COLUMN_EMAIL + " =?", new String[]{getCurrentUser()});
        if(cursor.moveToFirst()){
            mobile = cursor.getString(0);
        }else {
            return " ";
        }
        return mobile;
    }

    //Update current user details to db (update)
    private void update(){
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        mDB = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BrewixContract.UserData.COLUMN_NAME, nameTemp);
        cv.put(BrewixContract.UserData.COLUMN_EMAIL, emailTemp);
        cv.put(BrewixContract.UserData.COLUMN_MOBILE, mobileTemp);
        mDB.update(BrewixContract.UserData.TABLE_NAME, cv, BrewixContract.UserData.COLUMN_EMAIL + " =? ", new String[]{currentUser});
        mDB.execSQL("UPDATE " + BrewixContract.Status.TABLE_NAME + " SET " + BrewixContract.Status.COLUMN_EMAIL + " =? " + " WHERE " + BrewixContract.Status.COLUMN_EMAIL + " =? ", new String[]{emailTemp, currentUser});
        name.getText().clear();
        email.getText().clear();
        mobile.getText().clear();
    }
}