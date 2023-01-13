package com.example.thebrewix;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class frappuccino_menu extends Fragment {

    ImageView back;
    TextView peppermintMochaText, peppermintMochaPrice;
    TextView caramelBruleeText, caramelBruleePrice;
    TextView sugarCookieText, sugarCookiePrice;
    TextView mochaCookieText, mochaCookiePrice;
    TextView chestnutPText, chestnutPPrice;
    TextView toastedWhiteText, toastedWhitePrice;
    TextView strawberryText, strawberryPrice;
    TextView javaChipText, javaChipPrice;
    LinearLayout peppermintMocha, caramelBrulee, mochaCookie, chestnutP, toastedWhite, sugarCookie, strawberry, javaChip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frappuccino_menu, container, false);

        back = view.findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });

        //Forward itemName, itemPrice, itemDescription, itemImage to the item_details fragment

        peppermintMocha = view.findViewById(R.id.peppermintMocha);
        peppermintMocha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peppermintMochaText = view.findViewById(R.id.peppermintMochaText);
                String tempName = peppermintMochaText.getText().toString();
                item_details.reName = tempName;
                peppermintMochaPrice = view.findViewById(R.id.peppermintMochaPrice);
                String tempPrice = peppermintMochaPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "A delectable blend of chocolaty-pepperminty perfection in every cool sip—Frappuccino® Roast coffee, mocha sauce, peppermint-flavored syrup, milk and ice, topped with whipped cream and dark-chocolate curls.";
                item_details.reImage = R.drawable.peppermintmocha;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("frappuccino_menu").commit();
            }
        });

        caramelBrulee = view.findViewById(R.id.caramelBrulee);
        caramelBrulee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caramelBruleeText = view.findViewById(R.id.caramelBruleeText);
                String tempName = caramelBruleeText.getText().toString();
                item_details.reName = tempName;
                caramelBruleePrice = view.findViewById(R.id.caramelBruleePrice);
                String tempPrice = caramelBruleePrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Frappuccino® Roast coffee, milk and ice blended with rich caramel brulée sauce, and then topped with whipped cream and our crunchy caramel brulée bits for extra-gooey goodness.";
                item_details.reImage = R.drawable.caramelbrulee;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("frappuccino_menu").commit();
            }
        });

        mochaCookie = view.findViewById(R.id.mochaCookie);
        mochaCookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mochaCookieText = view.findViewById(R.id.mochaCookieText);
                String tempName = mochaCookieText.getText().toString();
                item_details.reName = tempName;
                mochaCookiePrice = view.findViewById(R.id.mochaCookiePrice);
                String tempPrice = mochaCookiePrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Frappuccino® Roast coffee, mocha sauce and Frappuccino® chips blended with milk and ice, layered on top of whipped cream and chocolate cookie crumble and topped with vanilla whipped cream, mocha drizzle and even more chocolate cookie crumble. Each sip is as good as the last . . . all the way to the end.";
                item_details.reImage = R.drawable.mochacookie;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("frappuccino_menu").commit();
            }
        });

        chestnutP = view.findViewById(R.id.chestnutP);
        chestnutP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chestnutPText = view.findViewById(R.id.chestnutPText);
                String tempName = chestnutPText.getText().toString();
                item_details.reName = tempName;
                chestnutPPrice = view.findViewById(R.id.chestnutPPrice);
                String tempPrice = chestnutPPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "A blissful world of deliciousness: festive flavors of caramelized chestnuts and spices blended with Frappuccino® Roast coffee, milk and ice, and topped with whipped cream and spiced praline crumbs.";
                item_details.reImage = R.drawable.chestnut;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("frappuccino_menu").commit();
            }
        });

        toastedWhite = view.findViewById(R.id.toastedWhite);
        toastedWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastedWhiteText = view.findViewById(R.id.toastedWhiteText);
                String tempName = toastedWhiteText.getText().toString();
                item_details.reName = tempName;
                toastedWhitePrice = view.findViewById(R.id.toastedWhitePrice);
                String tempPrice = toastedWhitePrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "A toasty holiday blend of wonder: Frappuccino® Roast coffee, milk, ice and flavors of caramelized white chocolate with a cheerful topping of whipped cream, festive holiday sugar sparkles and crispy white pearls.";
                item_details.reImage = R.drawable.toastedwhite;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("frappuccino_menu").commit();
            }
        });

        sugarCookie = view.findViewById(R.id.sugarCookie);
        sugarCookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sugarCookieText = view.findViewById(R.id.sugarCookieText);
                String tempName = sugarCookieText.getText().toString();
                item_details.reName = tempName;
                sugarCookiePrice = view.findViewById(R.id.sugarCookiePrice);
                String tempPrice = sugarCookiePrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Sugar cookie-flavored syrup combined with Frappuccino® Roast coffee and almondmilk and blended with ice, topped with whipped cream and red and green sprinkles.";
                item_details.reImage = R.drawable.sugarcookie;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("frappuccino_menu").commit();
            }
        });

        strawberry = view.findViewById(R.id.strawberry);
        strawberry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strawberryText = view.findViewById(R.id.strawberryText);
                String tempName = strawberryText.getText().toString();
                item_details.reName = tempName;
                strawberryPrice = view.findViewById(R.id.strawberryPrice);
                String tempPrice = strawberryPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Summer's favorite berry is the star of this delicious Frappuccino® Blended Beverage—a blend of ice, milk and strawberry puree layered on top of a splash of strawberry puree and finished with vanilla whipped cream.";
                item_details.reImage = R.drawable.strawberry;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("frappuccino_menu").commit();
            }
        });

        javaChip = view.findViewById(R.id.javaChip);
        javaChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                javaChipText = view.findViewById(R.id.javaChipText);
                String tempName = javaChipText.getText().toString();
                item_details.reName = tempName;
                javaChipPrice = view.findViewById(R.id.javaChipPrice);
                String tempPrice = javaChipPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "We blend mocha sauce and Frappuccino® chips with coffee, milk and ice, then top it off with whipped cream and a mocha drizzle to bring you endless java joy.";
                item_details.reImage = R.drawable.javachip;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("frappuccino_menu").commit();
            }
        });




        return view;
    }
}