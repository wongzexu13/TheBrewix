package com.example.thebrewix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class store_detail extends AppCompatActivity {

    ImageView back;
    TextView phone, map;
    String phoneValue, name="The Brewix | Xiamen University Malaysia DT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);

        back = findViewById(R.id.backBtn);
        phone = findViewById(R.id.phone);
        map = findViewById(R.id.mapDirection);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                store_detail.super.onBackPressed();
            }
        });

        //Explicit intent to phone
        phoneValue=phone.getText().toString();
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneValue, null));
                startActivity(intent);
            }
        });

        //Explicit intent to map
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String geoUri = "http://maps.google.com/maps?q=loc:" + "2.832776015074785" + "," + "101.70742154454055" + " (" + name + ")";
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
}