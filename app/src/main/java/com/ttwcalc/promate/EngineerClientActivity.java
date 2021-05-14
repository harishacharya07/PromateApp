package com.ttwcalc.promate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EngineerClientActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineer_client);

        floatingActionButton = findViewById(R.id.client_fab);

        Intent intent = getIntent();
        final String pid = intent.getStringExtra("pid");

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EngineerClientActivity.this,
                        AddClientExpenditureActivity.class);
                intent.putExtra("pid", pid);
                startActivity(intent);
            }
        });
    }
}