package com.hm.greencity.customermanagement.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.ReceiptModel;
import java.util.List;


public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ViewHolder> {
    private List<ReceiptModel> models;
    private Context context;

    public ReceiptAdapter(List<ReceiptModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public ReceiptAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.receipt_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptAdapter.ViewHolder holder, int i) {
        holder.CustomerID.setText(models.get(i).getCustomerID());
        holder.CustomerName.setText(models.get(i).getCustomerName());
        holder.BusinessPartnerID.setText(models.get(i).getBusinessPartnerID());
        holder.amount.setText(models.get(i).getAmount());
        holder.BookingNumber.setText(models.get(i).getBookingNumber());
        holder.Plot.setText(models.get(i).getPlot());
        holder.Mode.setText(models.get(i).getMode());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView CustomerID,CustomerName,BusinessPartnerID,AllotmentDate,amount,BookingNumber,Plot,Mode;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CustomerID = itemView.findViewById(R.id.CustomerID);
            CustomerName = itemView.findViewById(R.id.CustomerName);
            BusinessPartnerID = itemView.findViewById(R.id.BusinessPartnerID);
            AllotmentDate = itemView.findViewById(R.id.AllotmentDate);
            amount = itemView.findViewById(R.id.amount);
            BookingNumber = itemView.findViewById(R.id.BookingNumber);
            Plot = itemView.findViewById(R.id.Plot);
            Mode = itemView.findViewById(R.id.Mode);
        }
    }
}
