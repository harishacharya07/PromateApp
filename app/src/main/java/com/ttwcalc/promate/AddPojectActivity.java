package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AddPojectActivity extends AppCompatActivity {

    private EditText projectname;
    private EditText projectid;
    private Button btnsubmit;
    private EditText projectId;

    String firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poject);

        final FirebaseDatabase database = FirebaseDatabase.getInstance("https://promate-e5e9a-default-rtdb.firebaseio.com/");
       // firebaseAuth = FirebaseAuth.getInstance().getCurrentUser().getUid();

        projectname = findViewById(R.id.projectname);
        projectid = findViewById(R.id.projectid);
        btnsubmit = findViewById(R.id.btnsubmit);
        projectId = findViewById(R.id.projectid);

        final Date date = new Date();


        //firebaseDatabase = FirebaseDatabase.getInstance();
       // firebaseAuth = FirebaseAuth.getInstance().getCurrentUser().getUid();


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onClick(View v) {

                final int pid  = genarateProjectId();
                String n = Integer.toString(pid);
                projectId.setText(n);

                DateFormat dateFormat;

                dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                String name = projectname.getText().toString().trim();
                //String id = projectid.getText().toString().trim();
                Map<String, Object> map = new HashMap<>();
                map.put("name", name);
                //map.put("id", id);
                map.put("pid", "Id:" + n);
                map.put("date", dateFormat.format(date));
                FirebaseDatabase.getInstance()
                        .getReference().child("Projects").child(n)
                        .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(AddPojectActivity.this, MainActivity.class);
                        intent.putExtra("id", pid);
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }

            private int genarateProjectId() {
                String pid;
                Random random = new Random();
                int randomNumber = random.nextInt(1000);
                return randomNumber;
            }
        });
    }

}