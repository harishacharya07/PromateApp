package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import java.util.Random;

import javax.sql.StatementEvent;

public class AddExpenditureActivity extends AppCompatActivity {

    private EditText sName;
    private EditText workName;
    private EditText place;
    private Button btnSubmit;
    private EditText amount;
    private TextView textView;
    private final boolean isApproved = true;

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

                    final String wName = workName.getText().toString();
                    final String subName = sName.getText().toString();
                    String pName = place.getText().toString();
                    String aName = amount.getText().toString();

                    String id = generateId();

                    Map<String, Object> map = new HashMap<>();
                    map.put("name", wName);
                    map.put("id", id);
                    map.put("pid", PID);
                    map.put("place", pName);
                    map.put("amount", aName);
                    map.put("date", dateFormat.format(date));
                    map.put("isApproved", "false");


                    FirebaseDatabase.getInstance("https://promate-e5e9a-default-rtdb.firebaseio.com/")
                            .getReference().child("Expenditure").child(PID).child(id).
                            setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent = new Intent(AddExpenditureActivity.this,
                                    ExpenditureActivity.class);
                            intent.putExtra("pid", PID);
                            intent.putExtra("wName", wName);
                            startActivity(intent);

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

                int length = 5;

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
