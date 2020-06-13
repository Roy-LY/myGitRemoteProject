package com.geekband.vol7;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    public static final String URI = "content://com.geekband.vol7.MyProvider/phonelist";
    private String id;
    List<String> contactList = new ArrayList<>();
    Button button;
    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.query);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                String uri = URI + "/" + name;
                query(uri);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putStringArrayListExtra("contactlist", (ArrayList<String>) contactList);
                startActivity(intent);
            }
        });
//        insert();

    }

//    public void insert(){
//        Uri uri = Uri.parse(URI);
//        ContentValues values = new ContentValues();
//        values.put("name","Lisa");
//        values.put("number","15112539838");
//        values.put("sex","男");
//        Uri newUri = getContentResolver().insert(uri,values);
//        id = newUri.getPathSegments().get(1);
//    }

    public void query(String URI){
        Uri uri = Uri.parse(URI);
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        if(cursor != null){
            while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
                String sex = cursor.getString(cursor.getColumnIndex("sex"));
                contactList.add(name + "\n" + number + "\n" + sex);
            }
            cursor.close();
        }
    }

//    public void update(){
//        Uri uri = Uri.parse(URI + "/" + id);
//        ContentValues values = new ContentValues();
//        values.put("name","小王");
//        values.put("number","15362943963");
//        getContentResolver().update(uri,values,null,null);
//    }
//
//    public int delete(){
//        Log.d("1111111111", "delete: " + id);
//        Uri uri = Uri.parse(URI + "/" + id);
//        int delete = getContentResolver().delete(uri, null, null);
//        return delete;
//    }
}
