package com.example.thebrewix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class change_password extends AppCompatActivity {

    ImageView back;
    EditText current, new1, new2;
    TextView feedback;
    Button update;
    String dbPwd, currentPwd, pwd1, pwd2;
    SQLiteDatabase mDB, DBReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        back = findViewById(R.id.backBtn);
        current = findViewById(R.id.currentPwd);
        new1 = findViewById(R.id.newPwd1);
        new2 = findViewById(R.id.newPwd2);
        update = findViewById(R.id.update);
        feedback = findViewById(R.id.feedback);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_password.super.onBackPressed();
            }
        });

        //Update password with validations
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPwd = current.getText().toString();
                pwd1 = new1.getText().toString();
                pwd2 = new2.getText().toString();
                dbPwd = getPwd();
                if(dbPwd.equals(currentPwd)==false){
                    feedback.setText("Incorrect Current Password");
                    feedback.setVisibility(View.VISIBLE);
                }else if(pwd1.equals(pwd2) == false) {
                    feedback.setText("Password does not match");
                    feedback.setVisibility(View.VISIBLE);
                }else{
                    updatePwd();
                    current.getText().clear();
                    new1.getText().clear();
                    new2.getText().clear();
                    feedback.setText("Password Updated");
                    feedback.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    //Retrieve password from db
    private String getPwd(){
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        DBReader = dbHelper.getReadableDatabase();
        String password;
        Cursor cursor = DBReader.rawQuery("SELECT " + BrewixContract.UserData.COLUMN_PASSWORD + " FROM " + BrewixContract.UserData.TABLE_NAME + " WHERE " + BrewixContract.UserData.COLUMN_EMAIL + " =?", new String[]{profile.currentUser});
        if(cursor.moveToFirst()){
            password = cursor.getString(0);
        }else {
            return "NULL";
        }
        cursor.close();
        DBReader.close();
        return password;
    }

    //Update new password to db (insert)
    private void updatePwd(){
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        mDB = dbHelper.getWritableDatabase();
        mDB.execSQL("UPDATE " + BrewixContract.UserData.TABLE_NAME + " SET " + BrewixContract.UserData.COLUMN_PASSWORD + " =? " + " WHERE " + BrewixContract.UserData.COLUMN_EMAIL + " =? ", new String[]{pwd1, profile.currentUser});
        current.getText().clear();
        new1.getText().clear();
        new2.getText().clear();
    }
}