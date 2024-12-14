package com.hm.greencity.customermanagement.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.EmiTracking.LstEMIReport;
import java.util.List;


public class EmiAdapter extends RecyclerView.Adapter<EmiAdapter.ViewHolder> {
    private List<LstEMIReport> models;
    private Context context;

    public EmiAdapter(List<LstEMIReport> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public EmiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.emi_tracking_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmiAdapter.ViewHolder holder, int position) {
        LstEMIReport visitor = models.get(position);
        holder.PaidAmount.setText(visitor.getPaidAmount());
        holder.InstAmt.setText(visitor.getInstAmt());
        holder.PaymentStatus.setText(visitor.getPaymentStatus());
        holder.InstallmentNo.setText(visitor.getInstallmentNo());
        holder.BookingDate.setText(String.valueOf(visitor.getBookingDate()));
        holder.PaymentDate.setText(String.valueOf(visitor.getPaymentDate()));
        holder.Companay.setText(String.valueOf(visitor.getCompanay()));
        holder.AccountHeadId.setText(String.valueOf(visitor.getAccountHeadId()));
        holder.PaymentMode.setText(String.valueOf(visitor.getPaymentMode()));
        holder.Status.setText(String.valueOf(visitor.getStatus()));
        holder.LateCharge.setText(String.valueOf(visitor.getLateCharge()));
        holder.TransactionNumber.setText(String.valueOf(visitor.getTransactionNumber()));
        holder.TransactionDate.setText(String.valueOf(visitor.getTransactionDate()));

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView PaidAmount, InstAmt, PaymentStatus, InstallmentNo, BookingDate,PaymentDate,Companay,AccountHeadId,PaymentMode,Status,LateCharge,TransactionNumber,TransactionDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            PaidAmount = itemView.findViewById(R.id.PaidAmount);
            InstAmt = itemView.findViewById(R.id.InstAmt);
            PaymentStatus = itemView.findViewById(R.id.PaymentStatus);
            InstallmentNo = itemView.findViewById(R.id.InstallmentNo);
            BookingDate = itemView.findViewById(R.id.BookingDate);
            PaymentDate = itemView.findViewById(R.id.PaymentDate);
            Companay = itemView.findViewById(R.id.Companay);
            AccountHeadId = itemView.findViewById(R.id.AccountHeadId);
            PaymentMode = itemView.findViewById(R.id.PaymentMode);
            Status = itemView.findViewById(R.id.Status);
            LateCharge = itemView.findViewById(R.id.LateCharge);
            TransactionNumber = itemView.findViewById(R.id.TransactionNumber);
            TransactionDate = itemView.findViewById(R.id.TransactionDate);

        }
    }
}
