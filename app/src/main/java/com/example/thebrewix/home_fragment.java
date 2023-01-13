package com.example.thebrewix;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home_fragment extends Fragment {

    Button shop;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment,container,false);

        //Start promotional video
        VideoView video = (VideoView) view.findViewById(R.id.video);
        video.setVideoPath("android.resource://" + getActivity().getPackageName() + "/" + R.raw.intro);
        video.start();

        //Loop video
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                video.start();
            }
        });

        //Switch to store fragment
        shop = view.findViewById(R.id.shop);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new store_fragment()).commit();
                BottomNavigationView bottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.bottonNav);
                bottomNavigationView.setSelectedItemId(R.id.nav_store);
            }
        });

        return view;
    }
}
