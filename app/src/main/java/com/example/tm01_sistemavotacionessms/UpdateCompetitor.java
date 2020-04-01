package com.example.tm01_sistemavotacionessms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCompetitor extends AppCompatActivity implements View.OnClickListener {
    protected ControllerDB control;
    protected EditText nameU, surnameU, nicknameU;
    protected Button saveU, cancelU;
    private Bundle extras;
    protected String idd, n, surname, nick, votes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_competitor);
        control = new ControllerDB(this);

        nameU =  findViewById(R.id.etName_u);
        surnameU =  findViewById(R.id.etSurname_u);
        nicknameU = findViewById(R.id.etNickname_u);

        saveU  = findViewById(R.id.btnAccept_u);
        cancelU  = findViewById(R.id.btnCancel_u);

        saveU.setOnClickListener(this);
        cancelU.setOnClickListener(this);

        extras = getIntent().getExtras();
        // Si no hay datos (cosa rara) salimos
        if (extras == null) {
            finish();
            return;
        }
        idd = extras.getString("id");
        n = extras.getString("name");
        surname = extras.getString("surname");
        nick = extras.getString("nickname");
        votes = extras.getString("votes");

        nameU.setText(n);
        surnameU.setText(surname);
        nicknameU.setText(nick);

    }

    public void editCompetitor(){

        String sname, ssurname, snickname;
        sname = nameU.getText().toString();
        ssurname = surnameU.getText().toString();
        snickname = nicknameU.getText().toString();

        if(sname.isEmpty()){
            nameU.setError("Required");
        }else if(ssurname.isEmpty()){
            surnameU.setError("Required");
        }else if(snickname.isEmpty()){
            nicknameU.setError("Required");
        }else{
            Competitor c =  new Competitor(idd, sname, ssurname, snickname, Integer.parseInt(votes));
            long id = control.updateC(c);
            if (id == -1) {
                Toast.makeText(UpdateCompetitor.this, "Error al actualizar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
            } else {
                // Terminar
                finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == saveU.getId()){
            editCompetitor();
        }else{
            finish();
        }
    }
}
