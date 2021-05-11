package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ExpenditureActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView textView;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    ExpenditureAdapter myadapter;
    TextView totalValues;
    FirebaseAuth firebaseAuth;
    EditText search;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);
        recyclerView = findViewById(R.id.recylerview);
        search = findViewById(R.id.search);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //textView = findViewById(R.id.pid);
        Intent intent = getIntent();
        final String names = intent.getStringExtra("pid");
        final String wName = intent.getStringExtra("wName");

        FirebaseRecyclerOptions<ModelExpenditure> options = new FirebaseRecyclerOptions.Builder<ModelExpenditure>().
                setQuery(FirebaseDatabase.getInstance().
                        getReference().child(names), ModelExpenditure.class).build();

        myadapter = new ExpenditureAdapter(options);
        recyclerView.setAdapter(myadapter);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseUser.getUid();


        final FirebaseDatabase database = FirebaseDatabase.getInstance("https://promate-e5e9a-default-rtdb.firebaseio.com/");
        floatingActionButton = findViewById(R.id.fab);
        totalValues = (TextView) findViewById(R.id.total);
        totalValues.setText(names);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ExpenditureActivity.this, AddExpenditureActivity.class);
                intent.putExtra("pid", names);
                startActivity(intent);
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        //databaseReference = firebaseDatabase.getReference(names);

        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int totalValue = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Project project = dataSnapshot.getValue(Project.class);
                    int value = Integer.parseInt(dataSnapshot.child("amount").getValue().toString());
                    totalValue =  totalValue + value;
                    String sTotal = String.valueOf(totalValue);

                //String value = dataSnapshot.child("pid").getValue().toString();
                //totalvalues.setText(value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchName = search.getText().toString();
                DatabaseReference databaseReference;
                databaseReference = FirebaseDatabase.getInstance().getReference("Exprnditure");

                Query query = databaseReference.orderByChild("name").startAt(searchName).endAt(searchName + "\uf8ff");

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