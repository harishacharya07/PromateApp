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

public class SubContractorAdaptor extends RecyclerView.Adapter<FoodViewHolder> {

    private Context mContext;
    private List<SubContractor> subContractors;
    private int lastPosition = -1;

    public SubContractorAdaptor(Context mContext, List<SubContractor> subContractors) {
        this.mContext = mContext;
        this.subContractors = subContractors;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sub_contractor,viewGroup,
                false);

        return new FoodViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder holder, int position) {
        holder.name.setText(subContractors.get(position).getName());
        holder.place.setText(subContractors.get(position).getProjectLocation());
        holder.amount.setText(subContractors.get(position).getDate());
        holder.subContractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), SubContractorDetailsActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return subContractors.size();
    }

}

class FoodViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView place;
    TextView amount;
    CardView subContractor;

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        place = itemView.findViewById(R.id.sub_contractor_place);
        amount = itemView.findViewById(R.id.amount);
        subContractor = itemView.findViewById(R.id.subcontractor_card_view);
    }
}


