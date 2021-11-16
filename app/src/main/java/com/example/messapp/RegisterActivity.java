package com.example.messapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email, password, confirm_password;
    Button btn_register;
    TextView tv_login;

    FirebaseAuth mAuth;
    DatabaseReference reference;

    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        btn_register = findViewById(R.id.btn_register);
        tv_login = findViewById(R.id.tv_login);

        email.setTranslationX(800);
        password.setTranslationX(800);
        confirm_password.setTranslationX(800);
        username.setTranslationX(800);
        btn_register.setTranslationX(800);
        tv_login.setTranslationY(300);

        username.setAlpha(v);
        email.setAlpha(v);
        password.setAlpha(v);
        confirm_password.setAlpha(v);
        btn_register.setAlpha(v);
        tv_login.setAlpha(v);

        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        confirm_password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        btn_register.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1100).start();
        tv_login.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();

        mAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username = username.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_confirm = confirm_password.getText().toString();


                if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_email) ){
                    Toast.makeText(RegisterActivity.this, "Fill all information", Toast.LENGTH_SHORT).show();
                }else if (!validatePassword(txt_password)){

                }else if (!txt_confirm.equals(txt_password)){
                    Toast.makeText(RegisterActivity.this, "Password is not match !!", Toast.LENGTH_SHORT).show();
                }else{
                    register(txt_username, txt_email, txt_password);
                }
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private boolean validatePassword(String password){

        Pattern lowerCase = Pattern.compile("[a-z]");
        Pattern digitCase = Pattern.compile("[0-9]");

        if (password.length() < 6){
            Toast.makeText(RegisterActivity.this, "Password must be at least 6 characters" , Toast.LENGTH_SHORT).show();
            return false;
        }else if (!lowerCase.matcher(password).find()){
            Toast.makeText(RegisterActivity.this, "Password must has at least 1 lowercase" , Toast.LENGTH_SHORT).show();
            return false;
        }else if (!digitCase.matcher(password).find()) {
            Toast.makeText(RegisterActivity.this, "Password must has at least 1 number", Toast.LENGTH_SHORT).show();
            return false;
        }

    return true;
    }

    private void register(String username, String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {

            FirebaseUser user = mAuth.getCurrentUser();

            reference = FirebaseDatabase.getInstance().getReference("User").child(user.getUid());

            if ( user != null ) {
//
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("username", username);
                hashMap.put("email", email);
                hashMap.put("id", user.getUid());
                hashMap.put("imageURL", "default");
                hashMap.put("status", "offline");

                reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(getApplicationContext(), VerifyMail.class));
                        finish();
                    }
                });
            }
            Toast.makeText(RegisterActivity.this, "Register Success !! ", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "FAILLLLLLLL !!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}