package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubContractorDetailsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<SubContractorModel> subContractorModels;
    private SubContractorExpenditureRvAdapter subContractorExpenditureRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_contractor_details);

        recyclerView = findViewById(R.id.subcontractor_expenditure_recyclerview);
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        String auth = firebaseAuth.getCurrentUser().getUid();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SubContractorDetailsActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("FotIVOyRCnYGTS9bWCPwRh31XB53");

        subContractorModels = new ArrayList<>();

        subContractorExpenditureRvAdapter  = new SubContractorExpenditureRvAdapter(SubContractorDetailsActivity.this, subContractorModels);
        recyclerView.setAdapter(subContractorExpenditureRvAdapter);

        ValueEventListener eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                subContractorModels.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    SubContractorModel subContractor = itemSnapshot.getValue(SubContractorModel.class);
                    subContractorModels.add(subContractor);
                }
                subContractorExpenditureRvAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}