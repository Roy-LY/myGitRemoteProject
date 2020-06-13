package com.geekband.vol7;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyProvider extends ContentProvider {
    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    private static UriMatcher uriMatcher;
    private static MyDataBaseHelper dbHelper;

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.geekband.vol7.MyProvider","phonelist",TABLE1_DIR);
        uriMatcher.addURI("com.geekband.vol7.MyProvider","phonelist/*",TABLE1_ITEM);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDataBaseHelper(getContext(), "phoneList", null, 1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                cursor = db.query("PhoneList",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case TABLE1_ITEM:
                String name = uri.getPathSegments().get(1);
                cursor = db.query("phonelist",projection,"name=?",new String[]{name},null,null,sortOrder);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.geekband.vol7.phonelist";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.geekband.vol7.phonelist";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Uri uriReturn = null;
       long id = db.insert("phonelist", null, values);
       uriReturn = Uri.parse("content://" + "com.geekband.vol7.MyProvider" + "/phoneList/" + id);
        return uriReturn;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int deleteRows = 0;
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                deleteRows = db.delete("phonelist", selection, selectionArgs);
                break;
            case TABLE1_ITEM:
                String id = uri.getPathSegments().get(1);
                deleteRows = db.delete("phonelist", "id=?", new String[]{id});
                break;
        }
        return deleteRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int updateRows = 0;
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                updateRows = db.update("phonelist", values, selection, selectionArgs);
                break;
            case TABLE1_ITEM:
                String id = uri.getPathSegments().get(1);
                updateRows = db.update("phonelist", values, "id=?", new String[]{id});
                break;
        }
        return updateRows;
    }
}
