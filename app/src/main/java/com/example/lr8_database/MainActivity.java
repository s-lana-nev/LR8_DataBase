package com.example.lr8_database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabaseHelper myDB;
    ArrayList<String> concert_id, concert_title, concert_band, concert_date, concert_place;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        concert_id = new ArrayList<>();
        concert_title = new ArrayList<>();
        concert_band = new ArrayList<>();
        concert_date = new ArrayList<>();
        concert_place = new ArrayList<>();
        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, concert_id, concert_title, concert_band,
                concert_date, concert_place);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            /*empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);*/
            Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                concert_id.add(cursor.getString(0));
                concert_title.add(cursor.getString(1));
                concert_band.add(cursor.getString(2));
                concert_date.add(cursor.getString(3));
                concert_place.add(cursor.getString(4));
            }
            /*empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);*/
        }
    }
}