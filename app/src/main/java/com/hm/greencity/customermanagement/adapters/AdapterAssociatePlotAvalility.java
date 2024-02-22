package com.hm.greencity.customermanagement.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.AssociatePlotAvalibility.AssociateLstAvailability;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterAssociatePlotAvalility extends RecyclerView.Adapter<AdapterAssociatePlotAvalility.ViewHolder> {


    private List<AssociateLstAvailability> models;
    private Context context;

    public AdapterAssociatePlotAvalility(List<AssociateLstAvailability> models, Context context) {
        this.models = models;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_associate_plotavalibility, viewGroup, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

//        viewHolder.tvLoginid.setText(models.get(i).getCustomerName() + " (" + models.get(i).getCustomerLoginID() + ")");
//        viewHolder.layout.setBackgroundColor(Color.parseColor(models.get(i).getColorCSS().trim()));
        viewHolder.tvPlotNumber.setText(models.get(i).getPlotNumber()+" "+models.get(i).getSiteName()+" "+models.get(i).getSectorName()+" Block: "+models.get(i).getBlockName());
        viewHolder.tvPlotArea.setText(models.get(i).getPlotArea());
        viewHolder.tvPlotSize.setText(models.get(i).getPlotSize());
        if(models.get(i).getPlotStatus().equals("B")){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                viewHolder.layout.setBackground(context.getDrawable(R.drawable.color_gradient_booked));
            }
        }else if(models.get(i).getPlotStatus().equals("R")){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                viewHolder.layout.setBackgroundColor(Color.parseColor("#c10202"));
            }
        }else if(models.get(i).getPlotStatus().equals("A")){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                viewHolder.layout.setBackgroundColor(Color.parseColor("#69ce69"));
            }
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                viewHolder.layout.setBackground(context.getDrawable(R.drawable.color_alloted_gradient));
            }
        }


//        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_plot_number)
        TextView tvPlotNumber;
        @BindView(R.id.tv_Plot_Area)
        TextView tvPlotArea;
        @BindView(R.id.tv_plot_size)
        TextView tvPlotSize;

        @BindView(R.id.card_item)
        CardView card_item;

        @BindView(R.id.layout)
        LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
