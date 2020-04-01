package com.example.tm01_sistemavotacionessms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ManagementCompetitors extends AppCompatActivity   {

    private List<Competitor> listCompetitors;
    private RecyclerView recyclerViewC;
    private AdapterC adapterCompetitors;
    private ControllerDB controllerDB;
    private FloatingActionButton fabAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_competitors);

        controllerDB = new ControllerDB(getApplicationContext());
        recyclerViewC = findViewById(R.id.recyclerViewCI);
        fabAdd = findViewById(R.id.fabAdd);

        listCompetitors = new ArrayList<>();
        adapterCompetitors = new AdapterC(listCompetitors);
        RecyclerView.LayoutManager myLM = new LinearLayoutManager(getApplicationContext());
        recyclerViewC.setLayoutManager(myLM);
        recyclerViewC.setItemAnimator(new DefaultItemAnimator());
        recyclerViewC.setAdapter(adapterCompetitors);


        getData();

        adapterCompetitors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Competitor rowSelected = listCompetitors.get(recyclerViewC.getChildAdapterPosition(v));
                Intent intent = new Intent(getApplicationContext(), UpdateCompetitor.class);
                Toast.makeText(getApplicationContext(), "Probando el click" + rowSelected.getName(), Toast.LENGTH_SHORT).show();

                intent.putExtra("id", String.valueOf(rowSelected.getId()));
                intent.putExtra("name", rowSelected.getName());
                intent.putExtra("surname", rowSelected.getSurname());
                intent.putExtra("nickname", rowSelected.getNickname());
                intent.putExtra("votes", String.valueOf(rowSelected.getVotes()));
                startActivity(intent);
            }
        });

        adapterCompetitors.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Competitor rowSelected = listCompetitors.get(recyclerViewC.getChildAdapterPosition(v));
                AlertDialog dialog = new AlertDialog
                        .Builder(ManagementCompetitors.this)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                controllerDB.deleteCompetitor(rowSelected);
                                getData();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setMessage("Delete " + rowSelected.getNickname() + "?")
                        .create();
                dialog.show();

                Toast.makeText(getApplicationContext(),"longClick", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateCompetitor.class);
                startActivity(intent);
            }
        });


    }
    public void getData() {
        if (adapterCompetitors == null) return;
        listCompetitors = controllerDB.getListCompetitors();
        adapterCompetitors.setListC(listCompetitors);
        adapterCompetitors.notifyDataSetChanged();
    }
   @Override
    protected void onResume() {
        super.onResume();
        getData();
    }


}
