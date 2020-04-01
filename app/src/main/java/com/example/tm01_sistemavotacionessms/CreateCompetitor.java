package com.example.tm01_sistemavotacionessms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateCompetitor extends AppCompatActivity implements View.OnClickListener{
    protected ControllerDB control;
    protected EditText nameC, surnameC, nicknameC;
    protected Button saveC, cancelC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_competitor);
        nameC =  findViewById(R.id.etName_c);
        surnameC =  findViewById(R.id.etSurname_c);
        nicknameC = findViewById(R.id.etNickname_c);

        saveC  = findViewById(R.id.btnAccept_c);
        cancelC  = findViewById(R.id.btnCancel_c);

        saveC.setOnClickListener(this);
        cancelC.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == saveC.getId()){
            addCompetitor();
        }else{
            finish();
        }
    }

    public void addCompetitor(){
        String sname, ssurname, snickname;
        sname = nameC.getText().toString();
        ssurname = surnameC.getText().toString();
        snickname = nicknameC.getText().toString();
        control = new ControllerDB(this);

        if(sname.isEmpty()){
            nameC.setError("Required");
        }else if(ssurname.isEmpty()){
            surnameC.setError("Required");
        }else if(snickname.isEmpty()){
            nicknameC.setError("Required");
        }else{
            Competitor c =  new Competitor(sname, ssurname, snickname);
            long id = control.newCompetitor(c);
            if (id == -1) {
                Toast.makeText(CreateCompetitor.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
            } else {
                // Terminar
                finish();
                Toast.makeText(CreateCompetitor.this, "Se guardó", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
