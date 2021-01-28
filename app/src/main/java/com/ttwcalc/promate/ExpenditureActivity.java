package com.ttwcalc.promate;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExpenditureActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView textView;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    ExpenditureAdapter myadapter;


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


        final FirebaseDatabase database = FirebaseDatabase.getInstance("https://promate-e5e9a-default-rtdb.firebaseio.com/");
        floatingActionButton = findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ExpenditureActivity.this, AddExpenditureActivity.class);
                intent.putExtra("pid", name1);
                startActivity(intent);
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