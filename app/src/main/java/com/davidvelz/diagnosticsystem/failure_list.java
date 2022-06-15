package com.davidvelz.diagnosticsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.davidvelz.diagnosticsystem.adapters.AdapterFailure;
import com.davidvelz.diagnosticsystem.adapters.AdapterSystem;
import com.davidvelz.diagnosticsystem.objects.FailureModel;
import com.davidvelz.diagnosticsystem.objects.SystemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class failure_list extends AppCompatActivity {
    private String system_id;
    private String name_ES_System;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference failureDBReference;

    private List<FailureModel> failureModelList;

    private AdapterFailure adapterFailure;

    private RecyclerView failureRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure_list);

        system_id = getIntent().getStringExtra("system_id");
        name_ES_System = getIntent().getStringExtra("name_ES");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(name_ES_System);

        firebaseDatabase = FirebaseDatabase.getInstance();

        failureDBReference = firebaseDatabase.getReference("failures");

        failureModelList = new ArrayList<>();

        failureRV = findViewById(R.id.failure_recycler);
        failureRV.setLayoutManager(new LinearLayoutManager(failure_list.this));

        getAllFailures();


    }
    private void getAllFailures(){
        Query queryFailures = failureDBReference.orderByChild("system_id").equalTo(system_id);
        queryFailures.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                failureModelList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {

                    FailureModel failureModel = ds.getValue(FailureModel.class);
                    failureModelList.add(failureModel);
                    adapterFailure = new AdapterFailure(failure_list.this, failureModelList);
                    failureRV.setAdapter(adapterFailure);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}