package com.geekband.vol7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {
    List<String> contactsList = new ArrayList<>();
    ListView contactsView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        contactsView = findViewById(R.id.list_view);
        // 子布局一定要用simple_list_item_1
        Intent intent = getIntent();
        contactsList = intent.getStringArrayListExtra("contactlist");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactsList);
        contactsView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
