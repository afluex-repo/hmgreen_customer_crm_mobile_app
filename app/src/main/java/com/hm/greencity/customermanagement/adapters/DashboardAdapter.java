package com.hm.greencity.customermanagement.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.DueInstallmentDashBoard;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.RecyclerViewViewHolder> {

    List<DueInstallmentDashBoard.Lstdue> lstdue;
    Context context;

    public DashboardAdapter(List<DueInstallmentDashBoard.Lstdue> lstdue, Context context) {

       this.lstdue=lstdue;
       this.context=context;
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_sales, viewGroup, false);
        return new RecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder recyclerViewViewHolder, int i) {

        recyclerViewViewHolder.tv_plotDetail.setText(lstdue.get(i).getPlotDetails());
        recyclerViewViewHolder.tv_amount.setText("\u20B9"+" " +lstdue.get(i).getInstallmentAmount());
        recyclerViewViewHolder.installmentNumber.setText("Installment no: "+lstdue.get(i).getInstallmentNo());
        recyclerViewViewHolder.dueDate.setText("Due Date -: "+lstdue.get(i).getDueDate());


    }

    @Override
    public int getItemCount() {
        return lstdue.size();
    }

    public class RecyclerViewViewHolder extends RecyclerView.ViewHolder {


        TextView tv_plotDetail,tv_amount,installmentNumber,dueDate;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_plotDetail=itemView.findViewById(R.id.plot_detail);
            tv_amount=itemView.findViewById(R.id.installmentAmount);
            installmentNumber=itemView.findViewById(R.id.installment_number);
            dueDate=itemView.findViewById(R.id.dueDate);

        }
    }
}
