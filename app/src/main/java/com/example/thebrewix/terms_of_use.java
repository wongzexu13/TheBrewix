package com.example.thebrewix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class terms_of_use extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_use);

        back = findViewById(R.id.backBtn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                terms_of_use.super.onBackPressed();
            }
        });

    }
}