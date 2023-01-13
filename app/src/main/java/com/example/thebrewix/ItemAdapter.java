package com.example.thebrewix;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    //Adapter to facilitate recycler view with database

    public ItemAdapter (Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        public TextView nameText;
        public TextView priceText;
        public TextView noteText;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText =  itemView.findViewById(R.id.itemNameView);
            priceText =  itemView.findViewById(R.id.itemPriceView);
            noteText =  itemView.findViewById(R.id.itemNoteView);
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndexOrThrow(BrewixContract.ItemEntry.COLUMN_NAME));
        double price = mCursor.getDouble(mCursor.getColumnIndexOrThrow(BrewixContract.ItemEntry.COLUMN_PRICE));
        String note = mCursor.getString(mCursor.getColumnIndexOrThrow(BrewixContract.ItemEntry.COLUMN_NOTE));
        long id = mCursor.getLong(mCursor.getColumnIndexOrThrow(BrewixContract.ItemEntry._ID));

        holder.nameText.setText(name);
        holder.priceText.setText(String.format("%.2f", price));
        holder.noteText.setText(note);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor!=null){
            mCursor.close();
        }
        mCursor = newCursor;
        if(newCursor != null){
            notifyDataSetChanged();
        }
    }
}
