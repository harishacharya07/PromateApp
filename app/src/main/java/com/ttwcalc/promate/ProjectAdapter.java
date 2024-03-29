package com.ttwcalc.promate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.ttwcalc.promate.subcontractor.DetailsMainActivity;
import com.ttwcalc.promate.subcontractor.SubContractorFragment;

public class ProjectAdapter extends FirebaseRecyclerAdapter<Project, ProjectAdapter.myviewHolder> {


    public ProjectAdapter(@NonNull FirebaseRecyclerOptions<Project> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewHolder holder, final int position, @NonNull Project model) {
        holder.name.setText(model.getName());
        holder.date.setText(model.getDate());
        holder.ids.setText(model.getPid());
        final String pid = model.getPid();
        FirebaseAuth firebaseAuth;

        firebaseAuth = FirebaseAuth.getInstance();

        final String userId = firebaseAuth.getCurrentUser().getUid();

        final String uid = firebaseAuth.getUid();

        final Context context;


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailsMainActivity.class);
                SubContractorFragment subContractorFragment = new SubContractorFragment();
                intent.putExtra("pid", pid);
                holder.itemView.getContext().startActivity(intent);

            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.imageView.getContext());
                builder.setTitle("DeleteProject");
                builder.setMessage("Delete");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child(userId).child
                                (getRef(position).getKey()).removeValue();

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview, parent,
                false);
        return new myviewHolder(view);
    }

    public static class myviewHolder extends RecyclerView.ViewHolder {

        TextView name, date, id, ids;
        ImageView imageView;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            id = itemView.findViewById(R.id.place);
            ids = itemView.findViewById(R.id.id);
            imageView = itemView.findViewById(R.id.delete);
        }
    }
}
