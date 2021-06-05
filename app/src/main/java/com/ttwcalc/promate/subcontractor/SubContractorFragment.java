package com.ttwcalc.promate.subcontractor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.ttwcalc.promate.AddClientExpenditureActivity;
import com.ttwcalc.promate.R;
import com.ttwcalc.promate.adapters.ProjectSubContractotAdapter;
import com.ttwcalc.promate.models.SubContractorsListModel;

import java.util.ArrayList;
import java.util.List;

public class SubContractorFragment extends Fragment {
    private RecyclerView recyclerView;
    private FirebaseAuth firebaseAuth;
    private List<SubContractorsListModel> subContractorsListModels;
    private ProjectSubContractotAdapter projectSubContractotAdapter;
    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subcontractor, container, false);
        recyclerView = view.findViewById(R.id.subcontractor_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        floatingActionButton = view.findViewById(R.id.sub_contractor_fab);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Subcontractors");

        subContractorsListModels = new ArrayList<>();

        projectSubContractotAdapter  = new ProjectSubContractotAdapter(getContext(), subContractorsListModels);
        recyclerView.setAdapter(projectSubContractotAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddNewSubContractors.class);
                startActivity(intent);
            }
        });

        ValueEventListener eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                subContractorsListModels.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    SubContractorsListModel subContractorsListModel = itemSnapshot.getValue(SubContractorsListModel.class);
                    subContractorsListModels.add(subContractorsListModel);
                }

                projectSubContractotAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}
