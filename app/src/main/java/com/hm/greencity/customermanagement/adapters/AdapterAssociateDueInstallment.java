package com.hm.greencity.customermanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.AssociateDueInstallment.Lstdue;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterAssociateDueInstallment extends RecyclerView.Adapter<AdapterAssociateDueInstallment.ViewHolder> {
    private List<Lstdue> models;
    private Context context;
    private boolean showAll;

    public AdapterAssociateDueInstallment(List<Lstdue> models, Context context, boolean showAll) {
        this.models = models;
        this.context = context;
        this.showAll = showAll;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_dueinstallment_associate, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//    i=10;

//        viewHolder.tvLoginid.setText(models.get(i).getCustomerName() + " (" + models.get(i).getCustomerLoginID() + ")");
        viewHolder.tv_plotDetail.setText(models.get(i).getPlotDetails());
        viewHolder.tv_amount.setText("\u20B9" + " " + models.get(i).getInstallmentAmount());
        viewHolder.installmentNumber.setText("Installment No" + "(" + models.get(i).getInstallmentNo() + ")");
        viewHolder.clientDetail.setText(models.get(i).getCustomerLoginID()+" ("+models.get(i).getCustomerName()+")");
        viewHolder.dueInstallment.setText("Due Date -: "+models.get(i).getDueDate());

    }

    @Override
    public int getItemCount() {
        int returnSize;
        if (showAll) {
            returnSize = models.size();
        } else {
            returnSize = Math.min(models.size(), 10);
        }

        return returnSize;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //        @BindView(R.id.tvLoginid)
//        TextView tvLoginid;
//        @BindView(R.id.tv_plot_details)
//        TextView tvPlotDetails;
//        @BindView(R.id.InstallmentNo)
//        TextView InstallmentNo;
//        @BindView(R.id.tvAmount)
//        TextView tvAmount;
        @BindView(R.id.card_view)
        CardView cardView;

        TextView tv_plotDetail, tv_amount, installmentNumber,clientDetail,dueInstallment;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


            tv_plotDetail = itemView.findViewById(R.id.plot_detail);
            tv_amount = itemView.findViewById(R.id.installmentAmount);
            installmentNumber = itemView.findViewById(R.id.installment_number);
            clientDetail = itemView.findViewById(R.id.clientDetail);
            dueInstallment=itemView.findViewById(R.id.installment_number);


        }
    }

}
