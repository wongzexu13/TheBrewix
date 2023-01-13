package com.example.thebrewix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class login extends AppCompatActivity {

    Button signIn;
    TextView register,feedback, forget;
    EditText email, password;
    String emailTemp, pwdTemp, pwdDB;
    SQLiteDatabase mDB, DBReader;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Proceed to home page if there is valid current user (db)
        if(logged() == true){
            Intent intent = new Intent(login.this, home.class);
            startActivity(intent);
        }

        signIn = findViewById(R.id.signInBtn);
        register = findViewById(R.id.register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pwd);
        feedback = findViewById(R.id.feedback);
        loading = findViewById(R.id.progressBar1);
        forget = findViewById(R.id.forgetPwd);

        //Sign in with validations
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailTemp = email.getText().toString();
                pwdTemp = password.getText().toString();
                pwdDB = userAuth(emailTemp);

                if(emailTemp.trim().length()==0) {
                    feedback.setText("Email Required");
                    feedback.setVisibility(View.VISIBLE);
                }else if(pwdTemp.trim().length()==0){
                    feedback.setText("Password Required");
                    feedback.setVisibility(View.VISIBLE);
                }else if(pwdDB.equals("NULL")==true){
                    feedback.setText("User Not Exists");
                    feedback.setVisibility(View.VISIBLE);
                }else if(pwdDB.equals(pwdTemp)==false){
                    feedback.setText("Incorrect Password");
                    feedback.setVisibility(View.VISIBLE);
                }else{
                    loading.setVisibility(View.VISIBLE);
                    setStatus();
                    email.getText().clear();
                    password.getText().clear();
                    Intent intent = new Intent(login.this, home.class);
                    startActivity(intent);
                }
            }
        });

        //Intent to register activity
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, com.example.thebrewix.register.class);
                startActivity(intent);
            }
        });

        //Intent to reset password activity
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, reset_Password.class);
                startActivity(intent);
            }
        });
    }

    ////Proceed to home page if there is valid current user (db)
    @Override
    protected void onStart() {
        super.onStart();

        if(logged() == true){
            Intent intent = new Intent(login.this, home.class);
            startActivity(intent);
        }
    }

    //Retrieve password of the provided email from db (select)
    public String userAuth(String email){
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        DBReader = dbHelper.getReadableDatabase();
        String password;

        Cursor cursor = DBReader.rawQuery("SELECT " + BrewixContract.UserData.COLUMN_PASSWORD + " FROM " + BrewixContract.UserData.TABLE_NAME + " WHERE " + BrewixContract.UserData.COLUMN_EMAIL + " =?", new String[]{email});
        if(cursor.moveToFirst()){
            password = cursor.getString(0);
        }else {
            return "NULL";
        }

        cursor.close();
        DBReader.close();
        return password;
    }

    //Set current user status to db (update)
    private void setStatus(){
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        mDB = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BrewixContract.Status.COLUMN_EMAIL, emailTemp);
        mDB.insert(BrewixContract.Status.TABLE_NAME, null, cv);
    }

    //Retrieve current user status from db (select)
    private boolean logged(){
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        DBReader = dbHelper.getReadableDatabase();
        String password;

        Cursor cursor = DBReader.rawQuery("SELECT * FROM " + BrewixContract.Status.TABLE_NAME, null);
        if(cursor.moveToFirst()){
            cursor.close();
            DBReader.close();
            return true;
        }else {
            cursor.close();
            DBReader.close();
            return false;
        }
    }
}