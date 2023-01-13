package com.example.thebrewix;

import android.provider.BaseColumns;

public class BrewixContract {

    private BrewixContract(){}

    //Declare the name for each tables and their corresponding column name

    public static final class ItemEntry implements BaseColumns{
        public static final String TABLE_NAME = "itemList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_NOTE = "note";
        public static final String COLUMN_PRICE = "price";
    }

    public static final class UserData implements BaseColumns{
        public static final String TABLE_NAME = "userData";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_MOBILE = "mobile";
        public static final String COLUMN_REWARDS = "rewards";
    }

    public static final class Status implements BaseColumns{
        public static final String TABLE_NAME = "status";
        public static final String COLUMN_EMAIL = "email";
    }
}
