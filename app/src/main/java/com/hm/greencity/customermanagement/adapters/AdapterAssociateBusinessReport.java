package com.hm.greencity.customermanagement.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.AssociateBusinessReport.ResponseAssociateBusinessReport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterAssociateBusinessReport extends RecyclerView.Adapter<AdapterAssociateBusinessReport.ViewHolderAssociateBusinessReport> {

    List<ResponseAssociateBusinessReport.BussinessLst> bussinessLsts;
    Activity context;

    public AdapterAssociateBusinessReport(List<ResponseAssociateBusinessReport.BussinessLst> bussinessLst, Activity context) {

        this.bussinessLsts=bussinessLst;
        this.context=context;

    }

    @NonNull
    @Override
    public AdapterAssociateBusinessReport.ViewHolderAssociateBusinessReport onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_associate_business_report, parent, false);
        return new AdapterAssociateBusinessReport.ViewHolderAssociateBusinessReport(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAssociateBusinessReport.ViewHolderAssociateBusinessReport holder, int position) {


        holder.tvBookingNo.setText(bussinessLsts.get(position).getCustomername()+"("+bussinessLsts.get(position).getCustomerLoginID()+")");

//        holder.tvAssociateDetails.setText(bussinessLsts.get(position).getAssociateID());
//        holder.tvBookingNo.setText(bussinessLsts.get(position).getBlockName());

        holder.tvBookingStatus.setText(bussinessLsts.get(position).getAssociateName()+"("+bussinessLsts.get(position).getAssociateID()+")");
        holder.tvBookingDate.setText(bussinessLsts.get(position).getPaymentDate());
        holder.tvBookingRate.setText(bussinessLsts.get(position).getPaidAmount());
        holder.tvNetAmt.setText(bussinessLsts.get(position).getPlotNumber()+" "+bussinessLsts.get(position).getSectorName()+" "+bussinessLsts.get(position).getSiteName());


        //        holder.tvPltArea.setText(models.get(i).getPlotArea());
//        holder.tvPlotRate.setText(models.get(i).getPlotRate());
//        holder.tvPlotStatus.setText(models.get(i).getBookingStatus());
//        holder.tvPlotAmt.setText(models.get(i).getPlotAmount());
//        holder.tvDiscount.setText(models.get(i).getDiscount());
//        holder.tvPaidAmt.setText(models.get(i).getPaidAmount());



    }

    @Override
    public int getItemCount() {
        return bussinessLsts.size();
    }

    public class ViewHolderAssociateBusinessReport extends RecyclerView.ViewHolder {


        @BindView(R.id.tv_bookingNo)
        TextView tvBookingNo;
        @BindView(R.id.tv_plot_details)
        TextView tvPlotDetails;
        @BindView(R.id.plotamt)
        TextView plotamt;
        @BindView(R.id.tvAmount)
        TextView tvAmount;
        @BindView(R.id.tv_plotRate)
        TextView tvPlotRate;
        @BindView(R.id.tv_booking_rate)
        TextView tvBookingRate;

        @BindView(R.id.tv_netAmt)
        TextView tvNetAmt;


        @BindView(R.id.tv_booking_status)
        TextView tvBookingStatus;
        @BindView(R.id.tv_booking_date)
        TextView tvBookingDate;

        public ViewHolderAssociateBusinessReport(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
