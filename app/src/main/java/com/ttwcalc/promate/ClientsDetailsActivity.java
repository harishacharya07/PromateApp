package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
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
    private FirebaseAuth firebaseAuth;

    private static String LOGIN = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients_details);

        recyclerView = findViewById(R.id.client_recyclerview);

        Intent intent = getIntent();
        String login = intent.getStringExtra("login");

        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        String userType = sharedPreferences.getString(LOGIN, null);

        firebaseAuth = FirebaseAuth.getInstance();

        String uid = firebaseAuth.getCurrentUser().getUid();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ClientsDetailsActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(login);

        clientModelList = new ArrayList<>();

        clientRecyclerAdapter  = new ClientRecyclerAdapter(ClientsDetailsActivity.this,
                clientModelList);
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