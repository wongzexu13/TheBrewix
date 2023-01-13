package com.example.thebrewix;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class item_details extends Fragment {

    Button addOrder;
    ImageView back, itemImage;
    TextView name, description;
    EditText comment;
    static String reName, rePrice, reDes;
    String reComment;
    static int reImage;
    private SQLiteDatabase mDB;
    private ItemAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);

        //Initialize db
        BrewixDBHelper dbHelper = new BrewixDBHelper(getActivity());
        mDB = dbHelper.getWritableDatabase();

        //Update selected item details
        name = view.findViewById(R.id.itemName);
        name.setText(reName);
        description = view.findViewById(R.id.description);
        description.setText(reDes);
        itemImage = view.findViewById(R.id.itemImage);
        itemImage.setImageResource(reImage);
        comment = view.findViewById(R.id.comment);
        addOrder = view.findViewById(R.id.addToOrder);
        addOrder.setText("Add to Order - " + rePrice);

        //Add order to cart (db)
        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(comment.getText().toString().trim().length() == 0){
                    reComment = "No Note to Barista";
                }else{
                    reComment = comment.getText().toString();
                }
                addItem();
                Intent intent = new Intent(getActivity(), review_order.class);
                startActivity(intent);
            }
        });

        back = view.findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }

    //Add item into card (insert)
    private void addItem(){
        ContentValues cv = new ContentValues();
        cv.put(BrewixContract.ItemEntry.COLUMN_NAME, reName);
        cv.put(BrewixContract.ItemEntry.COLUMN_NOTE, reComment);
        cv.put(BrewixContract.ItemEntry.COLUMN_PRICE, Double.valueOf(rePrice));
        mDB.insert(BrewixContract.ItemEntry.TABLE_NAME, null, cv);
        comment.getText().clear();
    }
}