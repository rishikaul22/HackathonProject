package com.example.hackathonproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private AnimationDrawable backgroundGradient;
    private ImageView imageView;
    private FirebaseAuth mAuth;
    private TextInputLayout email,password;
    private Button loginButton;
    private String e,p;
    private TextView registerLink;
    private static String TAG="Login Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.background_image);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        //imageView.setBackgroundResource(R.drawable.gradient_animation);
        backgroundGradient = (AnimationDrawable)imageView.getBackground();
        backgroundGradient.setEnterFadeDuration(5000);
        backgroundGradient.setExitFadeDuration(5000);
        backgroundGradient.start();
        mAuth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.loginButton);
        registerLink = findViewById(R.id.register_link);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

                finish();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = checkEmail();
                if (b) {
                    signInUser();
                }
            }
        });

    }
    private boolean checkEmail() {
        e = email.getEditText().getText().toString();
        p = password.getEditText().getText().toString();
        if (e.isEmpty() | p.isEmpty()) {
            if (e.isEmpty() && p.isEmpty()) {
                email.setError("E-mail cannot be empty");
                password.setError("Password cannot be empty");
            }
            else if (e.isEmpty()) {
                email.setError("E-mail cannot be empty");
                password.setError(null);

            }
            else if (p.isEmpty()) {
                password.setError("Password cannot be empty");
                email.setError(null);
            }
            return false;
        }
        else {
            return true;
        }
    }

    private void signInUser() {
        mAuth.signInWithEmailAndPassword(e,p)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: signInSuccess");
                            //Start new Activity
                        }
                        else {
                            Log.d(TAG, "onComplete: signInFailed");

                        }

                    }
                });
    }

}
