package com.ttwcalc.promate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.ttwcalc.promate.R.id.amount;
import static com.ttwcalc.promate.R.id.approvedImageView;

public class ExpenditureAdapter extends FirebaseRecyclerAdapter<ModelExpenditure,
        ExpenditureAdapter.myviewHolder> {

    private DatabaseReference databaseReference;

    public ExpenditureAdapter(@NonNull FirebaseRecyclerOptions options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewHolder holder, final int position,
                                    @NonNull ModelExpenditure model) {

        holder.name.setText(model.getName());
        holder.id.setText(model.getId());
        holder.date.setText(model.getDate());
        holder.amount.setText(model.getAmount());

        final String pid = model.getPid();
        final String id = model.getId();
        final String name = model.getName();
        final String date = model.getDate();
        final String amount = model.getAmount();
        final String place = model.getPlace();
        final String isApprovedStatus = model.getIsApproved();


        holder.isApprovedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "this is status",
                        Toast.LENGTH_SHORT).show();
            }

        });

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.del.getContext());
                builder.setTitle("DeleteProject");
                builder.setMessage("Delete");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Exprnditure").child(pid)
                                .child(getRef(position).getKey()).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("id" + pid).child(id)
                                .child(getRef(position).getKey()).removeValue();

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

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailsExpenditureActivity.class);
                intent.putExtra("ids", id);
                intent.putExtra("amount", amount);
                intent.putExtra("date", date);
                intent.putExtra("name", name);
                intent.putExtra("pid", pid);
                intent.putExtra("place", place);
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenditure_layout, parent, false);
        return new myviewHolder(view);
    }

    public class myviewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView id;
        TextView date;
        TextView amount;
        ImageView isApprovedImage;
        ImageView del;
        CardView cardView;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.exp_name);
            id = itemView.findViewById(R.id.nameofthesub);
            date = itemView.findViewById(R.id.date);
            amount = itemView.findViewById(R.id.amount);
            del = itemView.findViewById(R.id.del);
            cardView = itemView.findViewById(R.id.expNames);
            isApprovedImage = itemView.findViewById(approvedImageView);

        }
    }
}