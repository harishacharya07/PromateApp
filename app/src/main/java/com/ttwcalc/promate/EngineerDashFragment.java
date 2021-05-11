package com.ttwcalc.promate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EngineerDashFragment extends Fragment {

    private CardView existingProject;
    private CardView createNew;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private TextView projectCount;
    private TextView onGoingProjects;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.engineer_dashboard_fragment, container, false);

        existingProject = view.findViewById(R.id.goto_existing);
        createNew = view.findViewById(R.id.create_new);
        projectCount = view.findViewById(R.id.project_count);
        onGoingProjects = view.findViewById(R.id.on_going_projects);

        String fireBase = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        existingProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPojectActivity.class);
                startActivity(intent);
            }
        });

        databaseReference.child("Projects").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int  totalValue = 0;

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    int count = (int) dataSnapshot.getChildrenCount();
                    projectCount.setText(Integer.toString(count));
                    onGoingProjects.setText(Integer.toString(count));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}
