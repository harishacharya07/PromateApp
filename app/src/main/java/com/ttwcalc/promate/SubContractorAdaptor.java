package com.ttwcalc.promate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.name.setText(subContractors.get(position).getName());
        holder.place.setText(subContractors.get(position).getPlace());
        holder.amount.setText(subContractors.get(position).getAmount());
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

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        place = itemView.findViewById(R.id.palace);
        amount = itemView.findViewById(R.id.amount);
    }
}


