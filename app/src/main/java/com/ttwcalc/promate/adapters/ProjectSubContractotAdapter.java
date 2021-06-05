package com.ttwcalc.promate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttwcalc.promate.R;
import com.ttwcalc.promate.models.SubContractorsListModel;

import java.util.List;

public class ProjectSubContractotAdapter extends RecyclerView.Adapter<ProjectViewHolder> {

    private Context context;
    private List<SubContractorsListModel> subContractorsListModelList;
    private int lastPosition = -1;

    public ProjectSubContractotAdapter(Context context, List<SubContractorsListModel> subContractorsListModelList) {
        this.context = context;
        this.subContractorsListModelList = subContractorsListModelList;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcontractors_list,parent,
                false);

        return new ProjectViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.subContractorName.setText(subContractorsListModelList.get(position).getName());
        holder.subContractorWork.setText(subContractorsListModelList.get(position).getNameOfWork());
    }

    @Override
    public int getItemCount() {
        return subContractorsListModelList.size();
    }
}

class ProjectViewHolder extends RecyclerView.ViewHolder {
    TextView subContractorName;
    TextView subContractorWork;

    public ProjectViewHolder(@NonNull View itemView) {
        super(itemView);
        subContractorName = itemView.findViewById(R.id.name_sub_contractor);
        subContractorWork = itemView.findViewById(R.id.name_of_works);
    }
}