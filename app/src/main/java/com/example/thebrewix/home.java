package com.example.thebrewix;


import static com.example.thebrewix.review_order.ORDER_CODE;
import static com.example.thebrewix.review_order.SUBMIT_CODE;
import static com.example.thebrewix.review_order.pass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity {

    int i=0;
    private long pressedTime;
    final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottonNav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //Determine which fragment to commit onCreate according to the Intent ref
        int code = getIntent().getIntExtra(pass, 0);
        if (code == ORDER_CODE) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new order_fragment()).commit();
            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottonNav);
            bottomNavigationView.setSelectedItemId(R.id.nav_order);
        }else if(code == SUBMIT_CODE){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new order_fragment()).commit();
            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottonNav);
            bottomNavigationView.setSelectedItemId(R.id.nav_order);
            preparing();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new home_fragment()).commit();
        }
    }

    //Switch fragment according to bottomNav selection
    private  BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;

            switch(item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new home_fragment();
                    break;
                case R.id.nav_order:
                    selectedFragment = new order_fragment();
                    break;
                case R.id.nav_store:
                    selectedFragment = new store_fragment();
                    break;
                case R.id.nav_rewards:
                    selectedFragment = new rewards_fragment();
                    break;
                case R.id.nav_setting:
                    selectedFragment = new setting_fragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFragment).commit();
            return true;
        }
    };

    //Exit application if there is two onBackPressed within 2secs
    @Override
    public void onBackPressed() {
        if(pressedTime + 2000 > System.currentTimeMillis()){
            //super.onBackPressed();
            finish();
            moveTaskToBack(true);
        }else{
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

    //Set order status to preparing
    private void preparing(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                order_fragment.order.setText("Preparing - 15mins");
                done();
            }
        }, 500);
    }

    //Set order status to ready for collection
    private void done(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                order_fragment.order.setText("Ready for Collection");
                reset();
            }
        }, 5000);
    }

    //Set order status to accept order
    private void reset(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                order_fragment.order.setText("Accepting Order");
            }
        }, 5000);
    }
}