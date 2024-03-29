package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button signupbtn;
    private Button loginbtn;
    private EditText usermail;
    private EditText password;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private  static String USER_TYPE = "userRole";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupbtn = findViewById(R.id.signupbtn);
        loginbtn = findViewById(R.id.loginbtn);

        usermail = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences("SelectUserRole",
                MODE_PRIVATE);
        String userType = sharedPreferences.getString(USER_TYPE, null);

        if (firebaseAuth.getCurrentUser() != null) {

            if (userType.equals("client")) {
                Intent intent = new Intent(LoginActivity.this, ClientsActivity.class);
                startActivity(intent);
                finish();
            } else if(userType.equals("engineer")) {
                Intent intent = new Intent(LoginActivity.this, EngineerDashBoardFragment.class);
                startActivity(intent);
                finish();
            } else if (userType.equals("subcontractor")) {
                Intent intent = new Intent(LoginActivity.this,
                        SubContractorDashBoard.class);
                startActivity(intent);
                finish();
            } else {
                return;
            }
        }

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SelectYourRollActivity.class);
                startActivity(intent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = usermail.getText().toString().trim();
                final String pass = password.getText().toString().trim();

                if (TextUtils.isEmpty(name)){
                    usermail.setError("email required");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    password.setError("password required");
                    return;
                }

                progressDialog.setTitle("login");
                progressDialog.setMessage("Please wait..");
                progressDialog.show();


                firebaseAuth.createUserWithEmailAndPassword(name, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"login successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this,SelectYourRollActivity .class);
                            startActivity(i);
                            finish();
                            progressDialog.dismiss();
                        } else {
                            Toast.makeText(LoginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

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