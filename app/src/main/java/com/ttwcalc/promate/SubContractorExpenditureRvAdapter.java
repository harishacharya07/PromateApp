package com.ttwcalc.promate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SubContractorExpenditureRvAdapter  extends RecyclerView.Adapter<SubContractorViewHolder> {
    private Context context;
    private List<SubContractorModel> arrayList;
    private int lastPosition = -1;

    public SubContractorExpenditureRvAdapter(Context context, List<SubContractorModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public SubContractorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcontractor_expenditure,parent,
                false);
        return  new SubContractorViewHolder(mView);

    }

    @Override
    public void onBindViewHolder(@NonNull SubContractorViewHolder holder, int position) {
        holder.subContractorName.setText(arrayList.get(position).getName());
        holder.subContractorDate.setText(arrayList.get(position).getDate());
        holder.subContractorAmount.setText(arrayList.get(position).amount);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

class SubContractorViewHolder extends ViewHolder {
    TextView subContractorName;
    TextView subContractorDate;
    TextView subContractorAmount;

    public SubContractorViewHolder(@NonNull View itemView) {
        super(itemView);
        subContractorName = itemView.findViewById(R.id.sub_exp_name);
        subContractorDate = itemView.findViewById(R.id.sub_contractor_date);
        subContractorAmount = itemView.findViewById(R.id.sub_exp_contractor_amount);
    }
}
