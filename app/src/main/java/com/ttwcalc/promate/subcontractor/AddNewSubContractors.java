package com.ttwcalc.promate.subcontractor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.ttwcalc.promate.R;
import com.ttwcalc.promate.SubContractor;

import java.util.HashMap;
import java.util.Map;

public class AddNewSubContractors extends AppCompatActivity {

    private EditText name;
    private EditText workName;
    private Button button;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sub_contractors);

        name = findViewById(R.id.sub_contractor_name);
        workName = findViewById(R.id.sub_contractor_work);
        button = findViewById(R.id.submit_button);

        firebaseDatabase = FirebaseDatabase.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty() || workName.getText().toString().isEmpty()) {
                    Toast.makeText(AddNewSubContractors.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    String subName = name.getText().toString();
                    String wName = name.getText().toString();

                    Map<String, Object> map = new HashMap<>();
                    map.put("name", subName);
                    map.put("work", wName);

                    firebaseDatabase.getReference().child("Subcontractors").setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                           Toast.makeText(AddNewSubContractors.this, "Success", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });

    }
}