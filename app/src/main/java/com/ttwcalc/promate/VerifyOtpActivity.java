package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOtpActivity extends AppCompatActivity {

    Button verify;
    PinView pinView;


    TextView mobileNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        verify = findViewById(R.id.btnVerify);
        pinView = findViewById(R.id.verify);

        Intent intent = getIntent();
        String number = intent.getStringExtra("name");
        sendOtpToUser(number);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkcode();
            }
        });

    }

    private void checkcode() {

       String pinView1 = pinView.getText().toString().trim();
       if (pinView1.isEmpty() || pinView1.length() < 6) {

           Toast.makeText(VerifyOtpActivity.this, "wrong Otp", Toast.LENGTH_SHORT).show();
       } else {
           finish_everyThing(pinView1);
       }
    }

    private void sendOtpToUser(String number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + number, 60, TimeUnit.SECONDS, this, nCallback
        );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks nCallback = new PhoneAuthProvider
            .OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken
                forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            String codeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
           String code = phoneAuthCredential.getSmsCode();
           if (code != null) {
               finish_everyThing(code);
           }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }
    };

    private void finish_everyThing(String code) {
        pinView.setText(code);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(code, code);
        codeSignIn(credential);
    }

    private void codeSignIn(PhoneAuthCredential credential) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isComplete()) {

                    Toast.makeText(VerifyOtpActivity.this, "Verification successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VerifyOtpActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}