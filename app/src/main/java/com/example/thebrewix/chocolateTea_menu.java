package com.example.thebrewix;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class chocolateTea_menu extends Fragment {

    ImageView back;
    TextView chocolateText, chocolatePrice;
    TextView peppermintText, peppermintPrice;
    TextView teavanaText, teavanaPrice;
    TextView royalEngText, royalEngPrice;
    TextView matchaText, matchaPrice;
    TextView mintText, mintPrice;
    LinearLayout chocolate, peppermint, teavana, royalEng, matcha, mint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chocolate_tea_menu, container, false);

        back = view.findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });

        //Forward itemName, itemPrice, itemDescription, itemImage to the item_details fragment

        chocolate = view.findViewById(R.id.chocolate);
        chocolate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chocolateText = view.findViewById(R.id.chocolateText);
                String tempName = chocolateText.getText().toString();
                item_details.reName = tempName;
                chocolatePrice = view.findViewById(R.id.chocolatePrice);
                String tempPrice = chocolatePrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Steamed milk with vanilla- and chocolate-flavored syrups. Topped with sweetened whipped cream and chocolate-flavored drizzle. A timeless classic made to sweeten your spirits.";
                item_details.reImage = R.drawable.chocolate;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("chocolateTea_menu").commit();
            }
        });

        peppermint = view.findViewById(R.id.peppermint);
        peppermint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peppermintText = view.findViewById(R.id.peppermintText);
                String tempName = peppermintText.getText().toString();
                item_details.reName = tempName;
                peppermintPrice = view.findViewById(R.id.peppermintPrice);
                String tempPrice = peppermintPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "A sweet twist on holiday hot chocolate—white chocolate mocha sauce, peppermint-flavored syrup and steamed milk, topped with whipped cream and dark-chocolate curls.";
                item_details.reImage = R.drawable.peppermint;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("chocolateTea_menu").commit();
            }
        });

        teavana = view.findViewById(R.id.teavana);
        teavana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teavanaText = view.findViewById(R.id.teavanaText);
                String tempName = teavanaText.getText().toString();
                item_details.reName = tempName;
                teavanaPrice = view.findViewById(R.id.teavanaPrice);
                String tempPrice = teavanaPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Bright, citrusy spark of Italian bergamot blends with subtle hints of lavender, vanilla syrup, and steamed milk for this frothy reinvention of classic Earl Grey tea.";
                item_details.reImage = R.drawable.teavana;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("chocolateTea_menu").commit();
            }
        });

        royalEng = view.findViewById(R.id.royalEng);
        royalEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                royalEngText = view.findViewById(R.id.royalEngText);
                String tempName = royalEngText.getText().toString();
                item_details.reName = tempName;
                royalEngPrice = view.findViewById(R.id.royalEngPrice);
                String tempPrice = royalEngPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Each sip of this beloved morning black tea unfolds to reveal the complexity of the high-grown full leaves. An elegant, time-honored classic that brings a royal nod to every cup.";
                item_details.reImage = R.drawable.royal;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("chocolateTea_menu").commit();
            }
        });

        matcha = view.findViewById(R.id.matcha);
        matcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matchaText = view.findViewById(R.id.matchaText);
                String tempName = matchaText.getText().toString();
                item_details.reName = tempName;
                matchaPrice = view.findViewById(R.id.matchaPrice);
                String tempPrice = matchaPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Smooth and creamy matcha sweetened just right and served with steamed milk. This favorite will transport your senses to pure green delight.";
                item_details.reImage = R.drawable.matcha;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("chocolateTea_menu").commit();
            }
        });

        mint = view.findViewById(R.id.mint);
        mint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mintText = view.findViewById(R.id.mintText);
                String tempName = mintText.getText().toString();
                item_details.reName = tempName;
                mintPrice = view.findViewById(R.id.mintPrice);
                String tempPrice = mintPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "A blend of mint and a pinch of lemon verbena creates a refreshing flavor that's supercool for a caffeine-free herbal tea.";
                item_details.reImage = R.drawable.mint;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("chocolateTea_menu").commit();
            }
        });

        return view;
    }
}