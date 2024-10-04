package com.hm.greencity.customermanagement.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.AssociateBookingList.LstBookingAssociate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AdapterAssociateBookingLst extends RecyclerView.Adapter<AdapterAssociateBookingLst.ViewHolder> {


    private List<LstBookingAssociate> models;
    private Context context;

    public AdapterAssociateBookingLst(List<LstBookingAssociate> models, Context context) {
        this.models = models;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.plot_booking, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tvCustomerDetail.setText(models.get(i).getCustomerLoginID()+"("+models.get(i).getCustomerName()+")");
        viewHolder.tvAssociateDetails.setText(models.get(i).getAssociateLoginID()+"("+models.get(i).getAssociateName()+")");
        viewHolder.tvBookingNo.setText(models.get(i).getBookingNumber());
        viewHolder.tvBookingDate.setText(models.get(i).getBookingDate());
        viewHolder.tvPlotNumber.setText(models.get(i).getPlotNumber());
        viewHolder.tvPltArea.setText(models.get(i).getPlotArea());
        viewHolder.tvPlotRate.setText(models.get(i).getPlotRate());
        viewHolder.tvPlotStatus.setText(models.get(i).getBookingStatus());
        viewHolder.tvPlotAmt.setText(models.get(i).getPlotAmount());
        viewHolder.tvDiscount.setText(models.get(i).getDiscount());
        viewHolder.tvPaidAmt.setText(models.get(i).getPaidAmount());
        viewHolder.tvNetAmnt.setText(models.get(i).getPaymentPlanID());

    }

    @Override
    public int getItemCount() {
        return models.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_customer_detail)
        TextView tvCustomerDetail;
        @BindView(R.id.tv_associate_details)
        TextView tvAssociateDetails;
        @BindView(R.id.tv_booking_no)
        TextView tvBookingNo;
        @BindView(R.id.tv_booking_date)
        TextView tvBookingDate;
        @BindView(R.id.tv_plot_number)
        TextView tvPlotNumber;
        @BindView(R.id.tv_pltArea)
        TextView tvPltArea;
        @BindView(R.id.tv_plot_status)
        TextView tvPlotStatus;

        @BindView(R.id.tv_plot_amt)
        TextView tvPlotAmt;
        @BindView(R.id.tv_plot_rate)
        TextView tvPlotRate;
        @BindView(R.id.tv_discount)
        TextView tvDiscount;
        @BindView(R.id.tv_net_amnt)
        TextView tvNetAmnt;
        @BindView(R.id.tv_paid_amt)
        TextView tvPaidAmt;
        @BindView(R.id.tv_Action)
        TextView tvAction;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
