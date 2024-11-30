package com.hm.greencity.customermanagement.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.GetSite.LstVisitor;
import java.util.List;


public class SiteVisitAdapter extends RecyclerView.Adapter<SiteVisitAdapter.ViewHolder> {
    private List<LstVisitor> models;
    private Context context;

    public SiteVisitAdapter(List<LstVisitor> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public SiteVisitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.visotor_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SiteVisitAdapter.ViewHolder holder, int position) {
        LstVisitor visitor = models.get(position);
        holder.customername.setText(visitor.getCustomerName());
        holder.visitdate.setText(visitor.getVisitDate());
        holder.sitenmae.setText(visitor.getSiteName());
        holder.mobileno.setText(visitor.getMobileNo());
        holder.customerNo.setText(String.valueOf(visitor.getCustomerNo()));
    }

    @Override
    public int getItemCount() {
        // Return the size of the model list
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView customername, visitdate, sitenmae, mobileno, customerNo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customername = itemView.findViewById(R.id.customername);
            visitdate = itemView.findViewById(R.id.visitdate);
            sitenmae = itemView.findViewById(R.id.sitenmae);
            mobileno = itemView.findViewById(R.id.mobileno);
            customerNo = itemView.findViewById(R.id.CustomerNo);
        }
    }
}
