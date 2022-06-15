package com.davidvelz.diagnosticsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.davidvelz.diagnosticsystem.adapters.AdapterQuery;
import com.davidvelz.diagnosticsystem.adapters.AdapterSystem;
import com.davidvelz.diagnosticsystem.objects.QueryModel;
import com.davidvelz.diagnosticsystem.objects.SystemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class queries_list extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference queryDBR;

    private RecyclerView queriesRV;

    private List<QueryModel> queryModelList;

    private AdapterQuery adapterQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Consultas");

        queriesRV = findViewById(R.id.query_recycler);
        queriesRV.setLayoutManager(new LinearLayoutManager(queries_list.this));

        queryModelList = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        queryDBR = firebaseDatabase.getReference("queries");

        getAllQueries();

    }
    private void getAllQueries(){
        queryDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                queryModelList.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    QueryModel QueryModel = ds.getValue(QueryModel.class);
                    queryModelList.add(QueryModel);
                    adapterQuery = new AdapterQuery(queries_list.this, queryModelList);
                    queriesRV.setAdapter(adapterQuery);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}