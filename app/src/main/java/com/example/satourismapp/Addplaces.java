package com.example.satourismapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addplaces extends AppCompatActivity {
DBHelper db = new DBHelper(this);
PlaceDetails d = new PlaceDetails();
 EditText place, attraction;
 Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplaces);

place = (EditText) findViewById(R.id.placetxt);
attraction = (EditText) findViewById(R.id.attracttxt);
add = (Button) findViewById(R.id.addbtn);

add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String prov = place.getText().toString();
        String attr = attraction.getText().toString();

        if(prov.equals("") || attr.equals("")){
            Toast.makeText(Addplaces.this, "Empty", Toast.LENGTH_SHORT).show();
        }else{
            d.setPlace(prov);
            d.setAttraction(attr);

            db.addPlaces(d);
            Toast.makeText(Addplaces.this, "added", Toast.LENGTH_SHORT).show();
        }
    }
});
    }
}