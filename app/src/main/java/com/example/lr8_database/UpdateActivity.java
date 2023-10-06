package com.example.lr8_database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, band_input, date_input, place_input;
    Button update_button;
    String id, title, band, date, place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input_update);
        band_input = findViewById(R.id.band_input_update);
        date_input = findViewById(R.id.date_input_update);
        place_input = findViewById(R.id.place_input_update);
        update_button = findViewById(R.id.add_bt_update);
        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                band = band_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                place = place_input.getText().toString().trim();
                myDB.updateData(id, title, band, date, place);
            }
        });

    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("band") && getIntent().hasExtra("date") && getIntent().hasExtra("place")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            band = getIntent().getStringExtra("band");
            date = getIntent().getStringExtra("date");
            place = getIntent().getStringExtra("place");

            //Setting Intent Data
            title_input.setText(title);
            band_input.setText(band);
            date_input.setText(date);
            place_input.setText(place);
            Log.d("svet", title+" "+band+" "+date+" "+place);
        }else{
            Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
        }
    }

}