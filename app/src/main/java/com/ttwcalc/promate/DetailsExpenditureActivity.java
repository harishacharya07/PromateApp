package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DetailsExpenditureActivity extends AppCompatActivity {
    private Button button;
    private DatabaseReference databaseReference;
    private TextView nameDetails;
    private TextView createdDate;
    private TextView createdAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_expenditure);

        button = findViewById(R.id.buttonAdd);
        nameDetails = findViewById(R.id.nameDetails);
        createdDate = findViewById(R.id.project_name);
        createdAmount = findViewById(R.id.created_date);

        Intent intent = getIntent();

        final String idFire = intent.getStringExtra("ids");
        final String name = intent.getStringExtra("name");
        final String date = intent.getStringExtra("date");
        final String pid = intent.getStringExtra("pid");
        final String place = intent.getStringExtra("place");
        final String amount = intent.getStringExtra("amount");

        databaseReference = FirebaseDatabase.getInstance().getReference(pid);
        String wName = intent.getStringExtra("wName");

        nameDetails.setText(name);
        createdDate.setText(amount);
        createdAmount.setText(date);

        final Map<String, Object> map = new HashMap<>();
        map.put("isApproved", true);
        map.put("name", name);
        map.put("id", idFire);
        map.put("pid", pid);
        map.put("place", place);
        map.put("amount", amount);
        map.put("date", date);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(name).child("isApproved").setValue("true").
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(DetailsExpenditureActivity.this, "Success",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}