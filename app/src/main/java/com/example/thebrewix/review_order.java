package com.example.thebrewix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class review_order extends AppCompatActivity {

    TextView add, cancel, count , subTotal, tax, total;
    Button proceedOrder;
    double subTotalTemp, taxTemp, totalTemp;
    int itemCount;

    private static SQLiteDatabase mDB, DBReader;
    private ItemAdapter mAdapter;

    public static final String pass = "pass";
    public static final int ORDER_CODE = 1013;
    public static final int SUBMIT_CODE = 1014;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_order);

        //Initialize db
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        mDB = dbHelper.getWritableDatabase();

        //Bind recycler view to itemAdapter
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ItemAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);

        //Swipe to remove item from cart
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long)viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);


        count = findViewById(R.id.itemCountView);
        subTotal = findViewById(R.id.subtotalView);
        tax = findViewById(R.id.taxView);
        total = findViewById(R.id.orderTotalView);
        proceedOrder = findViewById(R.id.proceedOrder);
        add = findViewById(R.id.addItems);
        cancel = findViewById(R.id.cancelOrder);

        //Update cart pricing
        refreshCart();

        //Intent to menu fragment to add more items
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( review_order.this, home.class);
                intent.putExtra(pass, ORDER_CODE);
                startActivity(intent);
            }
        });

        //Remove cart
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemCount = mAdapter.getItemCount();
                if(itemCount==0){
                    Intent i = new Intent(review_order.this, home.class);
                    startActivity(i);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(review_order.this);
                    builder.setMessage("Confirmation to remove your cart. All items will be removed.");
                    builder.setTitle("Remove Cart");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Remove".toLowerCase(), (DialogInterface.OnClickListener) (dialog, which) -> {
                        delete();
                    });
                    builder.setNegativeButton("Cancel".toLowerCase(), (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

        //Proceed with order
        proceedOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemCount = mAdapter.getItemCount();
                if(itemCount==0){
                    return;
                }else{

                    AlertDialog.Builder builder = new AlertDialog.Builder(review_order.this);
                    builder.setMessage("Confirmation to proceed with your order.");
                    builder.setTitle("Proceed Order");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Proceed".toLowerCase(), (DialogInterface.OnClickListener) (dialog, which) -> {
                        delete();
                        Intent i = new Intent(review_order.this, submit_order.class);
                        startActivity(i);
                    });
                    builder.setNegativeButton("Cancel".toLowerCase(), (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }

    //Retrieve all items from db and order by id in descending order (latest item will be showing on top)
    public static Cursor getAllItems(){
        return mDB.query(
                BrewixContract.ItemEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                BrewixContract.ItemEntry._ID + " DESC"
        );
    }

    //Remove item from cart by id
    private void removeItem(long id){
        mDB.delete(BrewixContract.ItemEntry.TABLE_NAME,BrewixContract.ItemEntry._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
        refreshCart();
    }

    //Remove all items from cart
    public void delete(){
        mDB.execSQL("DELETE FROM " + BrewixContract.ItemEntry.TABLE_NAME);
        Intent intent = new Intent( review_order.this, home.class);
        startActivity(intent);
    }

    //Update cart pricing details
    public void refreshCart(){
        itemCount = mAdapter.getItemCount();
        count.setText(String.valueOf(itemCount));
        subTotalTemp = getTotalItem();
        subTotal.setText(String.format("%.2f", subTotalTemp));
        taxTemp = subTotalTemp*0.06;
        tax.setText(String.format("%.2f", taxTemp));
        totalTemp = subTotalTemp+taxTemp;
        total.setText(String.format("%.2f", totalTemp));
        proceedOrder.setText("Continue - RM" + String.format("%.2f", totalTemp));
    }

    //Determine total price in db
    public double getTotalItem(){
        BrewixDBHelper dbHelper = new BrewixDBHelper(this);
        DBReader = dbHelper.getReadableDatabase();
        double total;
        String query = "SELECT SUM(" + BrewixContract.ItemEntry.COLUMN_PRICE + ") FROM " + BrewixContract.ItemEntry.TABLE_NAME;
        Cursor cursor = DBReader.rawQuery(query, null);
        if(cursor.moveToFirst()){
            total = cursor.getDouble(0);
        }else{
            return 0.00;
        }
        cursor.close();
        DBReader.close();
        return total;
    }
}