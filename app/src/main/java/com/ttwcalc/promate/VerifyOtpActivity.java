package com.ttwcalc.promate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class VerifyOtpActivity extends AppCompatActivity {

    TextView mobileNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        mobileNumber = findViewById(R.id.mobilenumber);

        Intent intent = getIntent();
        String mobileNumberFrom = intent.getStringExtra("mobilenumber");

        mobileNumber.setText(mobileNumberFrom);

    }
}