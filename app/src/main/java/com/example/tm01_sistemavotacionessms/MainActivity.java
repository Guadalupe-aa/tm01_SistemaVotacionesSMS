package com.example.tm01_sistemavotacionessms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.sppiner_m);
        String[] options = {"-Select-", "Management Competitors", "Graph"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options));
        spinner.setOnItemSelectedListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                        != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                        != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]
                    { Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS},0);
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS}, 225);

            //finish();  ERA ESTO
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(position == 1) {
            //Toast.makeText(getApplicationContext(), "Hola " + position, Toast.LENGTH_SHORT).show();
            Intent inn =  new Intent(getApplicationContext(), ManagementCompetitors.class);
            startActivity(inn);
        }else if(position == 2){
            Intent intentFirst = new Intent(this, Graph.class);
            startActivity(intentFirst);
        }else {

        }
        spinner.setSelection(0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}