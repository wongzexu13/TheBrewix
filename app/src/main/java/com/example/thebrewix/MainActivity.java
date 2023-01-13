package com.example.thebrewix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    TextView splash;
    Animation anim1;
    ProgressBar loading;
    final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Splash screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        anim1= AnimationUtils.loadAnimation(this, R.anim.animation);
        splash = findViewById(R.id.splashLogo);
        splash.setAnimation(anim1);
        loading = findViewById(R.id.progressBar1);
        loading();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    protected void loading(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.setVisibility(View.VISIBLE);
            }
        }, 1000);
    }
}