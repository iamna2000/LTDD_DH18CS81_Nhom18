package com.example.messapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAO {
    private DatabaseReference reference;
    public FirebaseAuth mAuth;

    FirebaseUser user = mAuth.getCurrentUser();
    public DAO(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        reference = db.getReference("User").child(user.getUid());
    }
    public Task<Void> add(User user){

        return reference.push().setValue(user);
    }

}
