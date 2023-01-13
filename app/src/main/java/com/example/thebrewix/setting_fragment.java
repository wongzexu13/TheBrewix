package com.example.thebrewix;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class setting_fragment extends Fragment {

    TextView profile, termsOfUse, privacyPolicy, share, support;
    Button signOut, delete;
    SQLiteDatabase mDB;
    Switch bio;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.setting_fragment,container,false);

        bio = view.findViewById(R.id.biometric);
        bio.setChecked(true);

        //Intent to corresponding activity
        profile =  view.findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), profile.class);
                startActivity(intent);
            }
        });

        //Intent to corresponding activity
        termsOfUse =  view.findViewById(R.id.termsOfUse);
        termsOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), terms_of_use.class);
                startActivity(intent);
            }
        });

        //Intent to corresponding activity
        privacyPolicy =  view.findViewById(R.id.privacyPolicy);
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), privacy_policy.class);
                startActivity(intent);
            }
        });

        //Intent to corresponding activity
        share = view.findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                String shareBody = "Redeem your free first coffee! Download The Brewix from Google Play Store to learn more.";
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "The Brewix"));
            }
        });

        //Intent to corresponding activity
        support = view.findViewById(R.id.support);
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto: support@thebrewix.com"));
                startActivity(intent);
            }
        });

        //Sign out
        signOut = view.findViewById(R.id.signOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Confirmation to proceed with signing out your account.");
                builder.setTitle("Sign Out");
                builder.setCancelable(false);
                builder.setPositiveButton("Sign Out", (DialogInterface.OnClickListener) (dialog, which) -> {
                    signUserOut();
                    Intent intent = new Intent(getActivity(), login.class);
                    startActivity(intent);
                });
                builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //Delete account
        delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Confirmation to delete your account. Your account will be permanently deleted.");
                builder.setTitle("Delete Account");
                builder.setCancelable(false);
                builder.setPositiveButton("Delete", (DialogInterface.OnClickListener) (dialog, which) -> {
                    deleteAccount();
                    Intent intent = new Intent(getActivity(), login.class);
                    startActivity(intent);
                });
                builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return view;
    }

    //Remove current user status from db (delete)
    private void signUserOut(){
        BrewixDBHelper dbHelper = new BrewixDBHelper(getActivity());
        mDB = dbHelper.getWritableDatabase();
        mDB.execSQL("DELETE FROM " + BrewixContract.Status.TABLE_NAME);
    }

    //Remove account from db (delete)
    private void deleteAccount(){
        BrewixDBHelper dbHelper = new BrewixDBHelper(getActivity());
        mDB = dbHelper.getWritableDatabase();
        mDB.execSQL("DELETE FROM " + BrewixContract.UserData.TABLE_NAME + " WHERE " + BrewixContract.UserData.COLUMN_EMAIL + " =? ", new String[]{com.example.thebrewix.profile.currentUser});
        mDB.execSQL("DELETE FROM " + BrewixContract.Status.TABLE_NAME);
    }
}
