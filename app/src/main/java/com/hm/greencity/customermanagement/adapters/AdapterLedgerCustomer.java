package com.hm.greencity.customermanagement.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.LedgerReport.PlotLadger;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;



public class AdapterLedgerCustomer extends RecyclerView.Adapter<AdapterLedgerCustomer.ViewHolder> {
    private List<PlotLadger> models;
    private Context context;
    public AdapterLedgerCustomer(List<PlotLadger> models, Context context) {
        this.models = models;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_ladger_report, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tvInstallmentDate.setText("Installment Date"+"("+models.get(i).getInstallmentDate()+")");
        viewHolder.srno.setText("Sr. No."+(i+1)+"");
        viewHolder.tvPaymentMode.setText("Payment Mode"+"("+models.get(i).getPaymentMode()+")");
        viewHolder.tvInstallmentAmount.setText("Installment"+"("+models.get(i).getInstallmentAmount()+")");
        viewHolder.tvPaidAmount.setText("Paid Amt"+"("+models.get(i).getPaidAmount()+")");
        viewHolder.tvPaymentDate.setText("Payment Date"+"("+models.get(i).getPaymentDate()+")");
        viewHolder.tv_installment_number.setText("Installment Number : "+"("+models.get(i).getInstallmentNo()+")");
        }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_installment_date)
        TextView tvInstallmentDate;

        @BindView(R.id.srno)
        TextView srno;


        @BindView(R.id.tv_installment_number)
        TextView tv_installment_number;
        @BindView(R.id.tv_payment_mode)
        TextView tvPaymentMode;
        @BindView(R.id.tv_installment_amount)
        TextView tvInstallmentAmount;
        @BindView(R.id.tv_paid_amount)
        TextView tvPaidAmount;
        @BindView(R.id.tv_payment_date)
        TextView tvPaymentDate;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
