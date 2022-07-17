package com.davidvelz.diagnosticsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class query_details_view extends AppCompatActivity {

    private String key_msj;
    private String details_msj;

    private TextView key_TV;
    private TextView details_TV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_details_view);

        key_msj = getIntent().getStringExtra("key");
        details_msj = getIntent().getStringExtra("details");

        key_TV = findViewById(R.id.query_key_information);
        details_TV = findViewById(R.id.query_details_information);
        //details_TV = stringRecived.replacingOccurrences(of: "\n", with: "\n")

        key_TV.setText(key_msj);
        //.replace("/n", "\n").replace("<b>", "<b>").replace("</b>", "</b>")

        details_TV.setText(Html.fromHtml(details_msj));
    }
}