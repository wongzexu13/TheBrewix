package com.example.thebrewix;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class order_fragment extends Fragment {

    TextView location;
    public static TextView order;
    LinearLayout espressoMenu, frappuccinoMenu, chocolateTeaMenu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order_fragment,container,false);

        //Intent to store fragment
        location = view.findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new store_fragment()).commit();
                BottomNavigationView bottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.bottonNav);
                bottomNavigationView.setSelectedItemId(R.id.nav_store);

             }
        });

        //Intent to review order activity
        order = view.findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), review_order.class);
                startActivity(intent);
            }
        });

        //Intent to corresponding menu fragment
        espressoMenu = view.findViewById(R.id.espresso);
        espressoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new espresso_menu()).addToBackStack("order_fragment").commit();
            }
        });

        //Intent to corresponding menu fragment
        frappuccinoMenu = view.findViewById(R.id.frappuccino);
        frappuccinoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new frappuccino_menu()).addToBackStack("order_fragment").commit();
            }
        });

        //Intent to corresponding menu fragment
        chocolateTeaMenu = view.findViewById(R.id.chocolateTea);
        chocolateTeaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new chocolateTea_menu()).addToBackStack("order_fragment").commit();
            }
        });

        return view;
    }
}
