package com.example.satourismapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView attraction,add;
Spinner spinner;
DBHelper db = new DBHelper(this);
PlaceDetails d = new PlaceDetails();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attraction = (TextView) findViewById(R.id.attraction);
        spinner = (Spinner) findViewById(R.id.spinner);
        add = (TextView) findViewById(R.id.add);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinner));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String choice = spinner.getSelectedItem().toString();

              db.getPlaceDetails(choice);
              attraction.setText(d.getAttraction());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//used to add places to database for the purpose of this app
        /*add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Addplaces.class);
                startActivity(intent);
            }
        });*/
    }
}