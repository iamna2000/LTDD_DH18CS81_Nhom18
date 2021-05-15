package com.example.messapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email, password;
    Button btn_register;

    FirebaseAuth mAuth;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_register = findViewById(R.id.btn_register);

        mAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username = username.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_email) ){
                    Toast.makeText(RegisterActivity.this, "Fill all information", Toast.LENGTH_SHORT).show();
                }else if (txt_password.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Password must be at least 6 characters" , Toast.LENGTH_SHORT).show();
                }else {
                    register(txt_username, txt_email, txt_password);
                }
            }
        });
    }


//    private void addValidation(){
//        awesomeValidation = new AwesomeValidation(BASIC);
//        awesomeValidation.addValidation(RegistActivity.this, R.id.edt_fullname, RegexTemplate.NOT_EMPTY ,R.string.err_fullname);
//        awesomeValidation.addValidation(RegistActivity.this,R.id.edt_address,RegexTemplate.NOT_EMPTY,R.string.err_value);
//        awesomeValidation.addValidation(RegistActivity.this, R.id.edt_cellphone, RegexTemplate.TELEPHONE + RegexTemplate.NOT_EMPTY , R.string
//                .err_phone);
//        awesomeValidation.addValidation(RegistActivity.this,R.id.edt_cellphone,new SimpleCustomValidation() {
//            @Override
//            public boolean compare(String s) {
//                if (s.length() < 8 || s.length() > 13){
//                    return false;
//                }else {
//                    return true;
//                }
//            }
//        },R.string.err_value);
//        awesomeValidation.addValidation(RegistActivity.this,R.id.edt_username, Patterns.EMAIL_ADDRESS,R.string
//                .err_email);
//        String regexPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
//        awesomeValidation.addValidation(RegistActivity.this,R.id.edt_password, regexPassword,R.string
//                .err_password);
//        awesomeValidation.addValidation(RegistActivity.this,R.id.edt_confirmpassword,R.id.edt_password,R.string.err_confirmpassword);
//    }

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