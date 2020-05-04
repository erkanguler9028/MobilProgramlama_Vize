package com.example.mobilprogramlama_vize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView tvHeader,tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvHeader = findViewById(R.id.ac_detail_header);
        tvDescription = findViewById(R.id.ac_detail_description);

        Intent getData = getIntent();

        String header = getData.getStringExtra("HEADER");
        String description = getData.getStringExtra("DESCRIPTION");

        tvHeader.setText(header);
        tvDescription.setText(description);

    }
}
