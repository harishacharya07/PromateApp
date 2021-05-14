package com.ttwcalc.promate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
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

public class AddClientExpenditureActivity extends AppCompatActivity {

    private EditText clientExpName;
    private EditText clientExpAmount;
    private EditText clientExpDesc;
    private Button clientSub;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client_expenditure);

        clientExpName = findViewById(R.id.client_exp_name);
        clientExpAmount = findViewById(R.id.client_exp_amount);
        clientExpDesc = findViewById(R.id.client_exp_disc);

        clientSub = findViewById(R.id.expenditure_button);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        final String uid = firebaseAuth.getCurrentUser().getUid();

        final Date date = new Date();
        final DateFormat dateFormat;

        Intent intent = getIntent();
        final String pid = intent.getStringExtra("pid");

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        clientSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clientExpName.getText().toString().isEmpty() ||  clientExpAmount.getText().toString().isEmpty()
                        || clientExpDesc.getText().toString().isEmpty()) {
                    Toast.makeText(AddClientExpenditureActivity.this,
                            "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    String expName = clientExpName.getText().toString();
                    String expAmount = clientExpAmount.getText().toString();
                    String expDesc = clientExpDesc.getText().toString();

                    Map<String, Object> map = new HashMap<>();
                    map.put("name", expName);
                    map.put("amount", expAmount);
                    map.put("desc", expDesc);
                    map.put("date", dateFormat.format(date));

                    firebaseDatabase.getReference().child(pid).
                            push().setValue(map).
                            addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(AddClientExpenditureActivity.this,
                                            "Expenditure Added", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}