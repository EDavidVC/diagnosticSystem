package com.davidvelz.diagnosticsystem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;

    private CardView systemButton;
    private CardView queriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        systemButton = findViewById(R.id.systemButton);
        queriesButton= findViewById(R.id.queryButton);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Sistema de diagnostico");

        systemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSystemListActivity();
            }
        });

        queriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQueriesListActivity();
            }
        });

    }
    private void openSystemListActivity(){
        Intent system_intent = new Intent(this, system_list.class);
        startActivity(system_intent);
    }
    private void openQueriesListActivity(){
        Intent queries_intent = new Intent(this, queries_list.class);
        startActivity(queries_intent);
    }
}