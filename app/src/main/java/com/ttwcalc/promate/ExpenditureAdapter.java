package com.ttwcalc.promate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ExpenditureAdapter extends FirebaseRecyclerAdapter<ModelExpenditure, ExpenditureAdapter.myviewHolder> {

    public ExpenditureAdapter(@NonNull FirebaseRecyclerOptions options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, int position, @NonNull ModelExpenditure model) {

        holder.name.setText(model.getName());
        holder.id.setText(model.getId());
        holder.date.setText(model.getDate());
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenditure_layout, parent, false);
        return new myviewHolder(view);
    }

    public class myviewHolder extends RecyclerView.ViewHolder {

        TextView name, id, date;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.exp_name);
            id = itemView.findViewById(R.id.nameofthesub);
            date = itemView.findViewById(R.id.date);
        }
    }
}