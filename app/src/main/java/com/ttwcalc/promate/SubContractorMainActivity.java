package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubContractorMainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private List<SubContractor> subContractorList;
    private SubContractor subContractor;
    private SubContractorAdaptor subContractorAdaptor;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_contractor_main);

        recyclerView = findViewById(R.id.recyclerView);
        textView = findViewById(R.id.empty);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SubContractorMainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Exprnditure");

        subContractorList = new ArrayList<>();

        subContractorAdaptor  = new SubContractorAdaptor(SubContractorMainActivity.this, subContractorList);
        recyclerView.setAdapter(subContractorAdaptor);

        ValueEventListener eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                subContractorList.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    SubContractor subContractor = itemSnapshot.getValue(SubContractor.class);

                    if (dataSnapshot.exists()) {
                        textView.setVisibility(View.INVISIBLE);
                        subContractorList.add(subContractor);
                    } else {
                       textView.setVisibility(View.VISIBLE);
                    }

                }

                subContractorAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}