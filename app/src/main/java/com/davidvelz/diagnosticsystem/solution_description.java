package com.davidvelz.diagnosticsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.davidvelz.diagnosticsystem.objects.SolutionModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class solution_description extends AppCompatActivity {
    private String solutionID;
    private TextView solutionKey;
    private TextView solutionDescription;
    private TextView priceSolution;
    //Firebase
    private DatabaseReference solutionDR;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_description);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Mas Detalles [+]");

        solutionID = getIntent().getStringExtra("solution_id");

        solutionDescription = findViewById(R.id.solution_description_imformation);
        solutionKey = findViewById(R.id.solution_key_imformation);
        priceSolution = findViewById(R.id.solution_price_appr_information);

        firebaseDatabase = FirebaseDatabase.getInstance();
        solutionDR = firebaseDatabase.getReference("solutions");

        getSolutionInformation();
    }
    private void getSolutionInformation(){
        Query queryMyDocuments = solutionDR.orderByChild("solution_id").equalTo(solutionID);
        queryMyDocuments.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    SolutionModel solutionModel = ds.getValue(SolutionModel.class);

                    solutionKey.setText(solutionModel.getKey());
                    solutionDescription.setText(Html.fromHtml(solutionModel.getDescription()));
                    priceSolution.setText(""+solutionModel.getPrice());

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}