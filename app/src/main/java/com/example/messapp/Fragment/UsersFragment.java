package com.example.messapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messapp.Adapter.UserAdapter;
import com.example.messapp.Model.Chatlist;
import com.example.messapp.R;
import com.example.messapp.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView communityView;

    private UserAdapter userAdapter;
    private List<User> mUsers;
    private List<User> cUsers;

    FirebaseUser fuser;
    DatabaseReference reference;

    private List<Chatlist> userList;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          View view = inflater.inflate(R.layout.fragment_users, container, false);

          recyclerView = view.findViewById(R.id.recycle_view);
          recyclerView.setHasFixedSize(true);
          recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

          communityView = view.findViewById(R.id.community_recycle_view);
          communityView.setHasFixedSize(true);
          communityView.setLayoutManager(new LinearLayoutManager(getContext()));

          mUsers = new ArrayList<>();
          cUsers = new ArrayList<>();
          userList = new ArrayList<>();

          fuser = FirebaseAuth.getInstance().getCurrentUser();

          reference = FirebaseDatabase.getInstance().getReference("Chatlist").child(fuser.getUid());
          reference.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Chatlist chatlist = dataSnapshot.getValue(Chatlist.class);
                    userList.add(chatlist);
                }
              }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

          readUsers();

          return view;
    }

    private void readUsers() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("User");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUsers.clear();
                cUsers.clear();
                for( DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);


                    assert user != null;
                    assert firebaseUser != null;

                    if (!user.getId().equals(firebaseUser.getUid())){

                        for (Chatlist chatlist : userList){
                            if (user.getId().equals(chatlist.getId())){
                                mUsers.add(user);
                            }
                        }
                        cUsers.add(user);
                    }
                }
                userAdapter = new UserAdapter(getContext(), mUsers);
                communityView.setAdapter(userAdapter);
                userAdapter = new UserAdapter(getContext(), cUsers);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}