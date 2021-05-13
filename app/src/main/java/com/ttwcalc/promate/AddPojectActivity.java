package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AddPojectActivity extends AppCompatActivity {

    private EditText nameOfProject;
    private EditText nameOfClient;
    private EditText descriptionOfProject;
    private EditText projectLocation;
    private EditText totalCost;
    private TextView projectId;
    private Button buttonSubmit;
    private FirebaseAuth firebaseAuth;
    private String userId;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poject);

        nameOfProject = findViewById(R.id.project_name);
        nameOfClient = findViewById(R.id.project_client);
        descriptionOfProject = findViewById(R.id.project_description);
        projectLocation = findViewById(R.id.project_location);
        totalCost = findViewById(R.id.project_total_amount);
        projectId = findViewById(R.id.project_id);
        buttonSubmit = findViewById(R.id.project_button);

        firebaseAuth = FirebaseAuth.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance
                ("https://promate-e5e9a-default-rtdb.firebaseio.com/");

        final ProgressDialog progressDialog = new ProgressDialog(AddPojectActivity.this);
        progressDialog.setTitle("Project Is Creating");

        final Date date = new Date();
        final DateFormat dateFormat;

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                if (nameOfClient.getText().toString().isEmpty() ||
                        nameOfProject.getText().toString().isEmpty()||
                descriptionOfProject.getText().toString().isEmpty() ||
                        projectLocation.getText().toString().isEmpty() ||
                totalCost.getText().toString().isEmpty()) {
                    Toast.makeText(AddPojectActivity.this, "Please Fill all " +
                            "the details", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                } else {

                    final String pid = generateId();

                    Map<String, Object> map = new HashMap<>();

                    String name = nameOfProject.getText().toString();
                    String clientName = nameOfClient.getText().toString();
                    String location = projectLocation.getText().toString();
                    String description = descriptionOfProject.getText().toString();
                    String total = totalCost.getText().toString();

                    map.put("name", name);
                    map.put("date", dateFormat.format(date));
                    map.put("pid", pid);
                    map.put("clientName", clientName);
                    map.put("totalCost", total);
                    map.put("description", description);
                    map.put("projectLocation", location);

                    FirebaseDatabase.getInstance()
                        .getReference().child("Projects").child(userId).child(pid)
                        .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(AddPojectActivity.this,
                                        MainActivity.class);
                                intent.putExtra("id", pid);
                                startActivity(intent);
                                progressDialog.dismiss();
                                finish();
                            }
                        },5000);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
                }
            }

            private String generateId() {
                String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

                StringBuilder sb = new StringBuilder();

                Random random = new Random();

                int length = 7;

                for(int i = 0; i < length; i++) {

                    int index = random.nextInt(alphabet.length());
                    char randomChar = alphabet.charAt(index);

                    sb.append(randomChar);
                }

                String randomString = sb.toString();
                return  randomString;
            }
        });
    }
}