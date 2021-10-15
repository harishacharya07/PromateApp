package com.ttwcalc.promate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SubContractorNewProject extends AppCompatActivity {
    private EditText subProjectName;
    private EditText subProjectLocation;
    private EditText subDescription;
    private EditText subProjectId;
    private Button subSubButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_contractor_new_project);

        subProjectName = findViewById(R.id.sub_project_name);
        subProjectLocation = findViewById(R.id.sub_project_location);
        subDescription = findViewById(R.id.sub_project_description);
        subProjectId = findViewById(R.id.subcontractor_project_id);
        subSubButton = findViewById(R.id.sub_project_button);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final String auth = firebaseAuth.getCurrentUser().getUid();

        final Date date = new Date();
        final DateFormat dateFormat;

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        final ProgressDialog progressDialog = new ProgressDialog(SubContractorNewProject.this);
        progressDialog.setTitle("Wait");

        subSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if(subProjectName.getText().toString().isEmpty() ||
                        subProjectLocation.getText().toString().isEmpty()
                        || subDescription.getText().toString().isEmpty() ||
                        subProjectId.getText().toString().isEmpty()) {
                    Toast.makeText(SubContractorNewProject.this, "Please fill all " +
                            "the details", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {

                    progressDialog.show();

                    Map<String, Object> map = new HashMap<>();

                    String name = subProjectName.getText().toString();
                    String location = subProjectLocation.getText().toString();
                    String description = subDescription.getText().toString();
                    final String projectId = subProjectId.getText().toString();

                    map.put("name", name);
                    map.put("date", dateFormat.format(date));
                    map.put("description", description);
                    map.put("projectLocation", location);
                    map.put("projectId", projectId);

                    FirebaseDatabase.getInstance().getReference().child(auth).child(projectId).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(SubContractorNewProject.this, "Success" +
                                    "the details", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SubContractorNewProject.this, SubContractorMainActivity.class);
                            intent.putExtra("projectId", projectId);
                            startActivity(intent);
                            progressDialog.dismiss();
                        }
                    });

                }
            }
        });
    }
}