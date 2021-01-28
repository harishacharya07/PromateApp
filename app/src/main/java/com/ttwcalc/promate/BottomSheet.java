package com.ttwcalc.promate;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BottomSheet extends BottomSheetDialogFragment {

    private EditText projectname;
    private EditText projectid;
    private Button btnsubmit;
    public FirebaseDatabase firebaseDatabase;
    public String firebaseAuth;



    public BottomSheet() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);

       // firebaseDatabase = FirebaseDatabase.getInstance();
       // firebaseAuth = FirebaseAuth.getInstance().getCurrentUser().getUid();

        projectname = view.findViewById(R.id.projectname);
        projectid = view.findViewById(R.id.projectid);
        btnsubmit = view.findViewById(R.id.btnsubmit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = projectname.getText().toString().trim();
                String id = projectid.getText().toString().trim();
               // firebaseDatabase.getReference().child("Project").child(firebaseAuth);

            }
        });
        return view;
    }
}
