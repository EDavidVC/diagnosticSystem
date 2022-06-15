package com.davidvelz.diagnosticsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.davidvelz.diagnosticsystem.adapters.AdapterSystem;
import com.davidvelz.diagnosticsystem.objects.SystemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class system_list extends AppCompatActivity {
    private ActionBar actionBar;
    private RecyclerView systemRV; // Systems Recicler View
    private AdapterSystem adapterSystem;

    private List<SystemModel> systemModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_list);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Sistemas");

        systemRV = findViewById(R.id.system_recycler);
        systemRV.setLayoutManager(new LinearLayoutManager(system_list.this));

        systemModelList = new ArrayList<>();

        getAllSystems();
    }
    private void getAllSystems() {
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("systems");
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                systemModelList.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    SystemModel systemModel = ds.getValue(SystemModel.class);
                    systemModelList.add(systemModel);
                    adapterSystem = new AdapterSystem(system_list.this, systemModelList);
                    systemRV.setAdapter(adapterSystem);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}