package com.ttwcalc.promate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOtpActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        button = findViewById(R.id.send);
        editText = findViewById(R.id.editTextPhone);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (editText.getText().toString().isEmpty()) {
                   Toast.makeText(SendOtpActivity.this, "Please enter your mobile number", Toast.LENGTH_SHORT).show();
               } else {
                String number = editText.getText().toString().trim();
                Intent intent = new Intent(SendOtpActivity.this, VerifyOtpActivity.class);
                intent.putExtra("number", number);
                startActivity(intent);
               }
            }
        });
    }
}