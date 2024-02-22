package com.hm.greencity.customermanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.AssociateLedgerList;
import com.hm.greencity.customermanagement.models.LedgerReport.PlotLadger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterLedgerAssociate extends RecyclerView.Adapter<AdapterLedgerAssociate.ViewHolder> {


    private List<AssociateLedgerList.LstLedger> models;
    private Context context;

    public AdapterLedgerAssociate(List<AssociateLedgerList.LstLedger> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterLedgerAssociate.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ladger_report, parent, false);
        return new AdapterLedgerAssociate.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLedgerAssociate.ViewHolder holder, int position) {
        holder.srno.setText("Sr. No."+(position+1)+"");
        holder.tvInstallmentDate.setText("Installment Date"+"("+models.get(position).getInstallmentDate()+")");
        holder.tvPaymentMode.setText("Payment Mode"+"("+models.get(position).getPaymentModeName()+")");
        holder.tvInstallmentAmount.setText("Installment"+"("+models.get(position).getInstAmt()+")");
        holder.tvPaidAmount.setText("Paid Amt"+"("+models.get(position).getPaidAmount()+")");
        holder.tvPaymentDate.setText("Payment Date"+"("+models.get(position).getPaymentDate()+")");
        holder.tv_installment_number.setText("Installment Number : "+"("+models.get(position).getInstallmentNo()+")");
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_installment_date)
        TextView tvInstallmentDate;
        @BindView(R.id.tv_payment_mode)
        TextView tvPaymentMode;
        @BindView(R.id.tv_installment_amount)
        TextView tvInstallmentAmount;
        @BindView(R.id.tv_paid_amount)
        TextView tvPaidAmount;
        @BindView(R.id.tv_payment_date)
        TextView tvPaymentDate;

        @BindView(R.id.srno)
        TextView srno;

        @BindView(R.id.tv_installment_number)
        TextView tv_installment_number;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
