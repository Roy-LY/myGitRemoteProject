package com.geekband.vol7;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UtilActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteOpenHelper dbHelper = new MyDataBaseHelper(this, "PhoneList.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d("111111111111", "onCreate: " + new MyProvider());
    }
}
