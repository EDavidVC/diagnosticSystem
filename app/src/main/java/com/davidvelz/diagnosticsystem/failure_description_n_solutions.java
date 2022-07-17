package com.davidvelz.diagnosticsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.davidvelz.diagnosticsystem.adapters.AdapterSolution;
import com.davidvelz.diagnosticsystem.adapters.AdapterSystem;
import com.davidvelz.diagnosticsystem.objects.FailureModel;
import com.davidvelz.diagnosticsystem.objects.SolutionModel;
import com.davidvelz.diagnosticsystem.objects.SystemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class failure_description_n_solutions extends AppCompatActivity {
    private DatabaseReference failuraDBReference;
    private FirebaseDatabase firebaseDatabase;

    private String failureID;
    private AdapterSolution adapterSolution;

    private List<SolutionModel> solutionModelList;

    //ACTIVITI PARTS

    private ImageView failureDescriptionImage;
    private TextView failureDescriptionKey;
    private TextView failureDescriptionAbstract;
    private RecyclerView solutionRecicler;

    private RecyclerView solutionRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure_description_nsolutions);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Detalles");

        failureID = getIntent().getStringExtra("f_ID");

        firebaseDatabase = FirebaseDatabase.getInstance();
        failuraDBReference = firebaseDatabase.getReference("failures");

        failureDescriptionKey = findViewById(R.id.failure_description_key);
        failureDescriptionImage = findViewById(R.id.failure_description_image);
        failureDescriptionAbstract = findViewById(R.id.failure_description_abstract);
        solutionRecicler = findViewById(R.id.solutions_recycler);
        solutionRecicler.setLayoutManager(new LinearLayoutManager(failure_description_n_solutions.this));
        solutionModelList = new ArrayList<>();

        getFailureInformation();

    }
    private void getFailureInformation(){
        Query queryProfessional = failuraDBReference.orderByChild("failure_id").equalTo(failureID);
        queryProfessional.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    FailureModel failureModel = ds.getValue(FailureModel.class);

                    getSolutions(failureModel.getSolutions());

                    String image_URL = failureModel.getFailure_image();

                    failureDescriptionKey.setText(failureModel.getKey());
                    failureDescriptionAbstract.setText(Html.fromHtml(failureModel.getFailure_abstract()));

                    try {
                        Picasso.get().load(image_URL).placeholder(R.drawable.system_logo).into(failureDescriptionImage);
                    }catch (Exception e){}


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getSolutions(List<String> solutions){
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("solutions");
            dbReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    solutionModelList.clear();
                    for(DataSnapshot ds:snapshot.getChildren()){

                        SolutionModel solutionModel = ds.getValue(SolutionModel.class);
                        if (solutions.contains(solutionModel.getSolution_id())){
                            solutionModelList.add(solutionModel);
                            adapterSolution = new AdapterSolution(failure_description_n_solutions.this, solutionModelList);
                            solutionRecicler.setAdapter(adapterSolution);
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
}