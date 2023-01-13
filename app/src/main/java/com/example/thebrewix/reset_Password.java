package com.example.thebrewix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class reset_Password extends AppCompatActivity {

    EditText name, email, pwd1, pwd2;
    Button reset;
    TextView feedback, login;
    String emailTemp, pwd1Temp, pwd2Temp;
    private SQLiteDatabase mDB, DBReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email = findViewById(R.id.email);
        pwd1 = findViewById(R.id.pwd1);
        pwd2 = findViewById(R.id.pwd2);
        feedback = findViewById(R.id.feedback);
        reset = findViewById(R.id.reset);
        login = findViewById(R.id.login);

        //Reset password with validations
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailTemp = email.getText().toString();
                pwd1Temp = pwd1.getText().toString();
                pwd2Temp = pwd2.getText().toString();

                if(emailTemp.trim().length()==0){
                    feedback.setText("Email is required");
                    feedback.setVisibility(View.VISIBLE);
                }else if(pwd1Temp.trim().length()==0 || pwd2Temp.trim().length()==0){
                    feedback.setText("Password is required");
                    feedback.setVisibility(View.VISIBLE);
                }else if(valid(emailTemp)==false){
                    feedback.setText("User not exists");
                    feedback.setVisibility(View.VISIBLE);
                }else if(pwd1Temp.equals(pwd2Temp) == false){
                    feedback.setText("Password does not match");
                    feedback.setVisibility(View.VISIBLE);
                }
                else{
                    reset();
                    Intent intent = new Intent(reset_Password.this, login.class);
                    startActivity(intent);
                }
            }
        });

        //Intent to login activity
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(reset_Password.this, login.class);
                startActivity(intent);
            }
        });
    }

    //Check if email is valid in db (select)
    private boolean valid(String user) {
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

    //Reset new password into db (update)
    private void reset(){
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        mDB = dbHelper.getWritableDatabase();
        mDB.execSQL("UPDATE " + BrewixContract.UserData.TABLE_NAME + " SET " + BrewixContract.UserData.COLUMN_PASSWORD + " =? " + " WHERE " + BrewixContract.UserData.COLUMN_EMAIL + " =? ", new String[]{pwd1Temp, emailTemp});
        email.getText().clear();
        pwd1.getText().clear();
        pwd2.getText().clear();
    }
}