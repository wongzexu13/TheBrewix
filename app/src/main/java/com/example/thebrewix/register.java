package com.example.thebrewix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class register extends AppCompatActivity {

    EditText name, email, pwd1, pwd2;
    Button register;
    TextView feedback, login;
    String nameTemp, emailTemp, pwd1Temp, pwd2Temp;
    private SQLiteDatabase mDB, DBReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pwd1 = findViewById(R.id.pwd1);
        pwd2 = findViewById(R.id.pwd2);
        feedback = findViewById(R.id.feedback);
        register = findViewById(R.id.registerBtn);
        login = findViewById(R.id.login);


        //Initialize db
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        mDB = dbHelper.getWritableDatabase();

        //Register user with validations
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameTemp = name.getText().toString();
                emailTemp = email.getText().toString();
                pwd1Temp = pwd1.getText().toString();
                pwd2Temp = pwd2.getText().toString();

                if(nameTemp.trim().length()==0 || emailTemp.trim().length()==0 || pwd1Temp.trim().length()==0 || pwd2Temp.trim().length()==0){
                    feedback.setText("All fields must be filled");
                    feedback.setVisibility(View.VISIBLE);
                }else if(pwd1Temp.equals(pwd2Temp) == false){
                    feedback.setText("Password does not match");
                    feedback.setVisibility(View.VISIBLE);
                }else if(userExists(emailTemp)==true){
                    feedback.setText("Email has been used");
                    feedback.setVisibility(View.VISIBLE);
                }else{
                    registerUser();
                    Intent intent = new Intent(com.example.thebrewix.register.this, login.class);
                    startActivity(intent);
                }
            }
        });


        //Intent to login activity
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        });
    }

    //Register user into db (insert)
    private void registerUser(){
        ContentValues cv = new ContentValues();
        cv.put(BrewixContract.UserData.COLUMN_NAME, nameTemp);
        cv.put(BrewixContract.UserData.COLUMN_EMAIL, emailTemp);
        cv.put(BrewixContract.UserData.COLUMN_PASSWORD, pwd1Temp);
        cv.put(BrewixContract.UserData.COLUMN_REWARDS, 500);
        mDB.insert(BrewixContract.UserData.TABLE_NAME, null, cv);
        name.getText().clear();
        email.getText().clear();
        pwd1.getText().clear();
        pwd2.getText().clear();
    }

    //Check if user exists in db (select)
    private boolean userExists(String user) {
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        DBReader = dbHelper.getReadableDatabase();

        Cursor cursor = DBReader.rawQuery("SELECT * FROM " + BrewixContract.UserData.TABLE_NAME + " WHERE " + BrewixContract.UserData.COLUMN_EMAIL + " =? ", new String[]{user});
        if (cursor.moveToFirst()) {
            cursor.close();
            DBReader.close();
            return true;
        } else {
            cursor.close();
            DBReader.close();
            return false;
        }
    }
}