package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    private EditText sName;
    private EditText workName;
    private EditText place;
    private Button btnSubmit;
    private EditText amount;
    private TextView textView;

    final FirebaseDatabase database = FirebaseDatabase.getInstance("https://promate-e5e9a-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenditure);

        workName = findViewById(R.id.work);
        sName = findViewById(R.id.sName);
        btnSubmit = findViewById(R.id.btnsubmit1);
        place = findViewById(R.id.place);
        amount = findViewById(R.id.amount);
        textView = findViewById(R.id.test);

        Intent intent = getIntent();
        final String PID = intent.getStringExtra("pid");
        textView.setText(PID);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = new Date();
                DateFormat dateFormat;

                dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                if (workName.getText().toString().isEmpty() || sName.getText().toString().isEmpty()
                        || place.getText().toString().isEmpty() || amount.getText().toString().isEmpty()) {
                    Toast.makeText(AddExpenditureActivity.this, "Please  fill all the details", Toast.LENGTH_SHORT).show();
                } else {

                    String wName = workName.getText().toString();
                    String subName = sName.getText().toString();
                    String pName = place.getText().toString();
                    String aName = amount.getText().toString();
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", wName);
                    map.put("id", subName );
                    map.put("pid", PID);
                    map.put("place", pName);
                    map.put("amount", aName);
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

                    FirebaseDatabase.getInstance("https://promate-e5e9a-default-rtdb.firebaseio.com/").getReference().child(PID).child(wName).
                            setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                }
            }
        });
    }
}
