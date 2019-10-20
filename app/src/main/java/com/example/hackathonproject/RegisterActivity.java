package com.example.hackathonproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private TextView textView;
    private TextInputLayout email,password,confirm_password;
    private String e,p,cp;
    private FirebaseAuth mAuth;
    private Button next_button;
    private String TAG = "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textView = findViewById(R.id.login_link);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        mAuth = FirebaseAuth.getInstance();
        next_button = findViewById(R.id.loginButton);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        });


    }
    public void createUser() {

        e = email.getEditText().getText().toString();
        p = password.getEditText().getText().toString();
        cp = confirm_password.getEditText().getText().toString();
        if(e.isEmpty()) {
            email.setError("Email cannot be empty");
        }
        if(p.isEmpty()) {
            password.setError("Password cannot be empty");
        }
        if(cp.isEmpty()) {
            confirm_password.setError("Confirm Password cannot be empty");
        }
        if(!e.isEmpty() && !p.isEmpty() && !cp.isEmpty()) {
            if(cp.equals(p)) {
                mAuth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: Created User");
                            //Start Home Screen Activity
                            Toast.makeText(RegisterActivity.this,"User Added",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Log.d(TAG, "onComplete: Creation Failed");
                            Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else {
                password.setError("Does not match");
                confirm_password.setError("Does not match");
            }
        }

    }
}
