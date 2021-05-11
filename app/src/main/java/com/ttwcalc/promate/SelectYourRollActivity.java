package com.ttwcalc.promate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.datatransport.runtime.dagger.Subcomponent;

public class SelectYourRollActivity extends AppCompatActivity {

    private CardView clientsCard;
    private CardView engineerCard;
    private CardView subContractorCard;

    private  static String USER_TYPE = "userRole";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_your_roll);

        clientsCard = findViewById(R.id.clientCard);
        engineerCard = findViewById(R.id.engineerCard);
        subContractorCard = findViewById(R.id.subContractorCard);

        final SharedPreferences sharedPreferences = getSharedPreferences("SelectUserRole",
                MODE_PRIVATE);

        clientsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectYourRollActivity.this,
                        ClientsActivity.class);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USER_TYPE, "client");
                editor.apply();
                startActivity(intent);
                finish();
            }
        });

        engineerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectYourRollActivity.this,
                        MainActivity.class);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USER_TYPE, "engineer");
                editor.apply();
                startActivity(intent);
                finish();
            }
        });

        subContractorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectYourRollActivity.this,
                        SubContractorMainActivity.class);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USER_TYPE, "subcontractor");
                editor.apply();
                startActivity(intent);
                finish();
            }
        });
    }
}