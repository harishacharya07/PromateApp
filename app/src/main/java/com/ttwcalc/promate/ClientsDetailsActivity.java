package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClientsDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private List<ClientModel> clientModelList;
    private ClientModel clientModel;
    private ClientRecyclerAdapter clientRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients_details);

        recyclerView = findViewById(R.id.client_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ClientsDetailsActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("259");

        clientModelList = new ArrayList<>();

        clientRecyclerAdapter  = new ClientRecyclerAdapter(ClientsDetailsActivity.this, clientModelList);
        recyclerView.setAdapter(clientRecyclerAdapter);

        ValueEventListener eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                clientModelList.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    ClientModel clientModel = itemSnapshot.getValue(ClientModel.class);
                    clientModelList.add(clientModel);

                }
               clientRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}