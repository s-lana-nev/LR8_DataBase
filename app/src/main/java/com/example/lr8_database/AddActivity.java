package com.example.lr8_database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText title_input, band_input, date_input, place_input;
    Button add_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        band_input = findViewById(R.id.band_input);
        date_input = findViewById(R.id.date_input);
        place_input = findViewById(R.id.place_input);
        add_bt = findViewById(R.id.add_bt);
        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addConcert(title_input.getText().toString().trim(),
                        band_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        place_input.getText().toString().trim());
            }
        });

    }
}