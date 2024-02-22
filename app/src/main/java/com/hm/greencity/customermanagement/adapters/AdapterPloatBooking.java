package com.hm.greencity.customermanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hm.greencity.customermanagement.Activity.CustomerLedgerReport;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.PloatBooking.PloatBookingLst;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPloatBooking extends RecyclerView.Adapter<AdapterPloatBooking.ViewHolder> {

    private List<PloatBookingLst> models;
    private Context context;

    public AdapterPloatBooking(List<PloatBookingLst> models, Context context) {
        this.models = models;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_plot_booking, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {


        viewHolder.tvBookingNo.setText(models.get(i).getPlotInformation());
        viewHolder.tvPlotDetails.setText(models.get(i).getBookingNumber());

        viewHolder.tvPaidAmt.setText("Total Paid " + "\u20B9" + " " + models.get(i).getTotalPaid());
        viewHolder.plotamt.setText("Total: " + "\u20B9" + " " + models.get(i).getPlotAmount());
        viewHolder.tvArea.setText("Area " + models.get(i).getPlotArea());
        viewHolder.tvNetAmt.setText("Payable " + "\u20B9" + " " + models.get(i).getNetPlotAmount());
        viewHolder.tvBookingRate.setText("\u20B9" + " " + models.get(i).getPlotRate());
        viewHolder.tvBookingDate.setText("Booked On -> " + models.get(i).getBookingDate());
        viewHolder.tv_remainingAmt.setText("Remaining " + "\u20B9" + " " + models.get(i).getRemainingBalance());
//        viewHolder.tvDiscount.setText(models.get(i).());
//
//        Cancelled
//        Registerd
//        Allotted
//        Booked

        if (models.get(i).getBookingStatus().equalsIgnoreCase("Allotted")) {
            viewHolder.tvBookingStatus.setTextColor(context.getResources().getColor(R.color.green_color));
        } else if (models.get(i).getBookingStatus().equalsIgnoreCase("Booked")) {
            viewHolder.tvBookingStatus.setTextColor(context.getResources().getColor(R.color.yellow));
        } else if (models.get(i).getBookingStatus().equalsIgnoreCase("Registerd")) {
            viewHolder.tvBookingStatus.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        } else if (models.get(i).getBookingStatus().equalsIgnoreCase("Cancelled")) {
            viewHolder.tvBookingStatus.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }

        viewHolder.tvBookingStatus.setText("Status: " + models.get(i).getBookingStatus());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CustomerLedgerReport.class);
                intent.putExtra("booking_id", models.get(viewHolder.getAdapterPosition()).getBookingNumber());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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
        @BindView(R.id.tv_area)
        TextView tvArea;
        @BindView(R.id.tv_netAmt)
        TextView tvNetAmt;
        @BindView(R.id.tv_paidAmt)
        TextView tvPaidAmt;

        @BindView(R.id.tv_booking_status)
        TextView tvBookingStatus;
        @BindView(R.id.tv_booking_date)
        TextView tvBookingDate;

        @BindView(R.id.tv_remainingAmt)
        TextView tv_remainingAmt;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
