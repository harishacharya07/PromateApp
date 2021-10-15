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

import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class SubContractorsFragment extends Fragment {
    private TextView projectCount;
    private TextView onGoingProjects;
    private CardView subCreateNew;
    private CardView subGotoExisting;
    private FirebaseDatabase firebaseDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_subcontractors, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();

        projectCount = view.findViewById(R.id.sub_project_count);
        projectCount = view.findViewById(R.id.subcontractor_on_going_projects);

        subCreateNew = view.findViewById(R.id.sub_create_new);
        subGotoExisting = view.findViewById(R.id.sub_goto_existing);

        subCreateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SubContractorNewProject.class);
                startActivity(intent);
            }
        });

        subGotoExisting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SubContractorMainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
