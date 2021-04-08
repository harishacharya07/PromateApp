package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ExpenditureActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView textView;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    ExpenditureAdapter myadapter;
    TextView totalvalues;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);
        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //textView = findViewById(R.id.pid);
        Intent intent = getIntent();
        final String name1 = intent.getStringExtra("pid");
        // textView.setText(name1);

        FirebaseRecyclerOptions<ModelExpenditure> options = new FirebaseRecyclerOptions.Builder<ModelExpenditure>().
                setQuery(FirebaseDatabase.getInstance().
                        getReference().child("Exprnditure").child(name1), ModelExpenditure.class).build();

        myadapter = new ExpenditureAdapter(options);
        recyclerView.setAdapter(myadapter);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String uid = firebaseUser.getUid();


        final FirebaseDatabase database = FirebaseDatabase.getInstance("https://promate-e5e9a-default-rtdb.firebaseio.com/");
        floatingActionButton = findViewById(R.id.fab);
        totalvalues = (TextView) findViewById(R.id.total);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ExpenditureActivity.this, AddExpenditureActivity.class);
                intent.putExtra("pid", name1);
                startActivity(intent);
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(name1);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int totalValue = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Project project = dataSnapshot.getValue(Project.class);
                    int value = Integer.parseInt(dataSnapshot.child("amount").getValue().toString());
                    totalValue =  totalValue + value;
                    String sTotal = String.valueOf(totalValue);
                    totalvalues.setText(sTotal);

                //String value = dataSnapshot.child("pid").getValue().toString();
                //totalvalues.setText(value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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