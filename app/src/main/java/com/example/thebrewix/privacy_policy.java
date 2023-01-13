package com.example.thebrewix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class privacy_policy extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        back = findViewById(R.id.backBtn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                privacy_policy.super.onBackPressed();
            }
        });
    }
}