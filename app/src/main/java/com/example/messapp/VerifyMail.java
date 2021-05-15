package com.example.messapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class VerifyMail extends AppCompatActivity {

    TextView txt_verify;
    Button btn_login, btn_verify;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_mail);

        txt_verify = findViewById(R.id.txt_verify);
        btn_login = findViewById(R.id.login);
        btn_verify = findViewById(R.id.btn_verify);

        auth = FirebaseAuth.getInstance();

        if (!auth.getCurrentUser().isEmailVerified()){
            btn_verify.setVisibility(View.VISIBLE);
            txt_verify.setVisibility(View.VISIBLE);
        }

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(VerifyMail.this, "Verification Email Sent.", Toast.LENGTH_SHORT).show();
                        btn_verify.setVisibility(View.GONE);
                        txt_verify.setVisibility(View.GONE);
                    }
                });
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerifyMail.this, LoginActivity.class));
                finish();
            }
        });
    }
}