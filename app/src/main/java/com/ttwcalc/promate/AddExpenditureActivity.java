package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.StatementEvent;

public class AddExpenditureActivity extends AppCompatActivity {

    private EditText projectname;
    private EditText projectid;
    private Button btnsubmit;
    private EditText projectId;
    private TextView textView;

    final FirebaseDatabase database = FirebaseDatabase.getInstance("https://promate-e5e9a-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenditure);

        projectname = findViewById(R.id.projectname1);
        projectid = findViewById(R.id.projectid1);
        btnsubmit = findViewById(R.id.btnsubmit1);
        projectId = findViewById(R.id.projectid);
        textView = findViewById(R.id.test);

        Intent intent = getIntent();
        final String PID = intent.getStringExtra("pid");
        textView.setText(PID);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = new Date();
                DateFormat dateFormat;

                dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                String name = projectname.getText().toString().trim();
                String id = projectid.getText().toString().trim();
                projectId.setText(PID);

                Map<String, Object> map = new HashMap<>();
                map.put("name", name);
                map.put("id", id);
                map.put("pid", PID);
                map.put("date", dateFormat.format(date));

                FirebaseDatabase.getInstance("https://promate-e5e9a-default-rtdb.firebaseio.com/").getReference()
                        .child("Exprnditure").child(PID).push()
                        .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(AddExpenditureActivity.this, ExpenditureActivity.class);
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });
    }
}
