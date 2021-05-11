package com.ttwcalc.promate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AllProjectFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    private ProjectAdapter myadapter;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private ImageView imageView;
    private TextView textView;

    private FirebaseAuth firebaseAuth;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        firebaseAuth = FirebaseAuth.getInstance();
        final String userId;
        userId = firebaseAuth.getCurrentUser().getUid();
        imageView = view.findViewById(R.id.emptyImage);
        textView = view.findViewById(R.id.nothing);

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        progressDialog.show();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().
                getReference("Projects");

        FirebaseRecyclerOptions<Project> options = new FirebaseRecyclerOptions.Builder<Project>().
                setQuery(FirebaseDatabase.getInstance().
                        getReference().child("Projects").child(userId), Project.class).build();

        myadapter = new ProjectAdapter(options);

       // progressDialog.dismiss();
        myadapter.notifyDataSetChanged();


        databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    progressDialog.dismiss();
                    imageView.setVisibility(view.INVISIBLE);
                    textView.setVisibility(view.INVISIBLE);
                    recyclerView.setAdapter(myadapter);
                } else {
                    imageView.setVisibility(view.VISIBLE);
                    textView.setVisibility(view.VISIBLE);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        myadapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        myadapter.stopListening();
    }

}
