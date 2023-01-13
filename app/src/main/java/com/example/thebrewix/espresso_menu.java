package com.example.thebrewix;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class espresso_menu extends Fragment {

    ImageView back;
    TextView caffeLatteText, caffeLattePrice;
    TextView caffeMochaText, caffeMochaPrice;
    TextView espressoText, espressoPrice;
    TextView espressoCPText, espressoCPPrice;
    TextView flatWhiteText, flatWhitePrice;
    TextView caramelMacchiatoText, caramelMacchiatoPrice;
    TextView cappuccinoText, cappuccinoPrice;
    TextView americanoText, americanoPrice;
    TextView mistoText, mistoPrice;
    TextView verandaText, verandaPrice;
    LinearLayout caffeLatte, caffeMocha, espresso, espressoCP, flatWhite, caramelMacchiato, cappuccino, americano, misto, veranda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_espresso_menu, container, false);

        back = view.findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });

        //Forward itemName, itemPrice, itemDescription, itemImage to the item_details fragment

        caffeLatte = view.findViewById(R.id.caffeLatte);
        caffeLatte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caffeLatteText = view.findViewById(R.id.caffeLatteText);
                String tempName = caffeLatteText.getText().toString();
                item_details.reName = tempName;
                caffeLattePrice = view.findViewById(R.id.caffeLattePrice);
                String tempPrice = caffeLattePrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Our dark, rich espresso balanced with steamed milk and a light layer of foam. A perfect milk-forward warm-up.";
                item_details.reImage = R.drawable.latte;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("espresso_menu").commit();
            }
        });

        caffeMocha = view.findViewById(R.id.caffeMocha);
        caffeMocha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caffeMochaText = view.findViewById(R.id.caffeMochaText);
                String tempName = caffeMochaText.getText().toString();
                item_details.reName = tempName;
                caffeMochaPrice = view.findViewById(R.id.caffeMochaPrice);
                String tempPrice = caffeMochaPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Our rich, full-bodied espresso combined with bittersweet mocha sauce and steamed milk, then topped with sweetened whipped cream. The classic coffee drink that always sweetly satisfies.";
                item_details.reImage = R.drawable.mocha;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("espresso_menu").commit();
            }
        });

        espresso = view.findViewById(R.id.espresso);
        espresso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                espressoText = view.findViewById(R.id.espressoText);
                String tempName = espressoText.getText().toString();
                item_details.reName = tempName;
                espressoPrice = view.findViewById(R.id.espressoPrice);
                String tempPrice = espressoPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Our smooth signature Espresso Roast with rich flavor and caramelly sweetness is at the very heart of everything we do.";
                item_details.reImage = R.drawable.espresso;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("espresso_menu").commit();
            }
        });

        espressoCP = view.findViewById(R.id.espressoCP);
        espressoCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                espressoCPText = view.findViewById(R.id.espressoCPText);
                String tempName = espressoCPText.getText().toString();
                item_details.reName = tempName;
                espressoCPPrice = view.findViewById(R.id.espressoCPPrice);
                String tempPrice = espressoCPPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Espresso meets a dollop of whipped cream to enhance the rich and caramelly flavors of a straight-up shot.";
                item_details.reImage = R.drawable.espressocp;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("espresso_menu").commit();
            }
        });

        flatWhite = view.findViewById(R.id.flatWhite);
        flatWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flatWhiteText = view.findViewById(R.id.flatWhiteText);
                String tempName = flatWhiteText.getText().toString();
                item_details.reName = tempName;
                flatWhitePrice = view.findViewById(R.id.flatWhitePrice);
                String tempPrice = flatWhitePrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Smooth ristretto shots of espresso get the perfect amount of steamed whole milk to create a not-too-strong, not-too-creamy, just-right flavor.";
                item_details.reImage = R.drawable.flatwhite;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("espresso_menu").commit();
            }
        });

        caramelMacchiato = view.findViewById(R.id.caramelMacchiato);
        caramelMacchiato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caramelMacchiatoText = view.findViewById(R.id.caramelMacchiatoText);
                String tempName = caramelMacchiatoText.getText().toString();
                item_details.reName = tempName;
                caramelMacchiatoPrice = view.findViewById(R.id.caramelMacchiatoPrice);
                String tempPrice = caramelMacchiatoPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Freshly steamed milk with vanilla-flavored syrup marked with espresso and topped with a caramel drizzle for an oh-so-sweet finish.";
                item_details.reImage = R.drawable.macchiato;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("espresso_menu").commit();
            }
        });

        cappuccino = view.findViewById(R.id.cappuccino);
        cappuccino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cappuccinoText = view.findViewById(R.id.cappuccinoText);
                String tempName = cappuccinoText.getText().toString();
                item_details.reName = tempName;
                cappuccinoPrice = view.findViewById(R.id.cappuccinoPrice);
                String tempPrice = cappuccinoPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Dark, rich espresso lies in wait under a smoothed and stretched layer of thick milk foam. An alchemy of barista artistry and craft.";
                item_details.reImage = R.drawable.cappucino;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("espresso_menu").commit();
            }
        });

        americano = view.findViewById(R.id.americano);
        americano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                americanoText = view.findViewById(R.id.americanoText);
                String tempName = americanoText.getText().toString();
                item_details.reName = tempName;
                americanoPrice = view.findViewById(R.id.americanoPrice);
                String tempPrice = americanoPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "Espresso shots topped with hot water create a light layer of crema culminating in this wonderfully rich cup with depth and nuance.";
                item_details.reImage = R.drawable.americano;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("espresso_menu").commit();
            }
        });

        misto = view.findViewById(R.id.misto);
        misto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mistoText = view.findViewById(R.id.mistoText);
                String tempName = mistoText.getText().toString();
                item_details.reName = tempName;
                mistoPrice = view.findViewById(R.id.mistoPrice);
                String tempPrice = mistoPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "A one-to-one combination of fresh-brewed coffee and steamed milk add up to one distinctly delicious coffee drink remarkably mixed.";
                item_details.reImage = R.drawable.misto;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("espresso_menu").commit();
            }
        });

        veranda = view.findViewById(R.id.veranda);
        veranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verandaText = view.findViewById(R.id.verandaText);
                String tempName = verandaText.getText().toString();
                item_details.reName = tempName;
                verandaPrice = view.findViewById(R.id.verandaPrice);
                String tempPrice = verandaPrice.getText().toString();
                item_details.rePrice = tempPrice;
                item_details.reDes = "In Latin America, coffee farms are often run by families, with their own homes on the same\u200B land where their coffee grows. We’ve sipped coffee with these farmers for decades,\u200B sitting on their verandas, overlooking the lush beauty of the coffee trees rolling out in the\u200B distance. Most times it was a lightly roasted coffee like this one. It took us more than 80\u200B tries to get it right—mellow and flavorful with a nice softness.\u200B";
                item_details.reImage = R.drawable.veranda;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new item_details()).addToBackStack("espresso_menu").commit();
            }
        });



        return view;
    }
}