package com.ttwcalc.promate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClientRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<ClientModel> clientModel;
    private int lastPosition = -1;


    public ClientRecyclerAdapter(Context mContext, List<ClientModel> clientModel) {
        this.mContext = mContext;
        this.clientModel = clientModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_recylerview,parent,
                false);

        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.nameOfClient.setText(clientModel.get(position).getName());
        holder.dateOfClient.setText(clientModel.get(position).getDate());
        holder.amountOfClient.setText(clientModel.get(position).getAmount());

        final String pid = clientModel.get(position).getPid();

        holder.clientCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ClientExpenditureActivity.class);
                intent.putExtra("id", pid);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clientModel.size();
    }

}

class ViewHolder extends RecyclerView.ViewHolder {
    public TextView nameOfClient;
    public TextView amountOfClient;
    public TextView dateOfClient;
    public CardView clientCardView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        nameOfClient = itemView.findViewById(R.id.name_client);
        amountOfClient = itemView.findViewById(R.id.client_amount);
        dateOfClient = itemView.findViewById(R.id.client_date);
        clientCardView = itemView.findViewById(R.id.client_card_view);
    }
}
