package com.geekband.vol7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper{
    public static final String CREATE_PHONEMES = "create table phoneList("
            + "number String,"
            + "name text,"
            + "sex String)";
    private Context mContext;

    public MyDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PHONEMES);
        Toast.makeText(mContext,"Create succeed",Toast.LENGTH_SHORT).show();
        Log.d("SqlLiteActivity", "onCreate: 1111111111111111111111111111");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
