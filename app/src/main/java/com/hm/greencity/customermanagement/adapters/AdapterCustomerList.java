package com.hm.greencity.customermanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.CustomerList.CustomerLst;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterCustomerList extends RecyclerView.Adapter<AdapterCustomerList.ViewHolder> {

    private List<CustomerLst> models;
    private Context context;

    public AdapterCustomerList(List<CustomerLst> models, Context context) {
        this.models = models;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_customer, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

//        viewHolder.tvLoginid.setText(models.get(i).getCustomerName() + " (" + models.get(i).getCustomerLoginID() + ")");
        viewHolder.tvCustomerId.setText(models.get(i).getCustomerName()+" "+"("+models.get(i).getCustomerID()+")");
        viewHolder.tvContact.setText(models.get(i).getContact());
        viewHolder.tvBranch.setText( models.get(i).getBranchName());
        viewHolder.tvPan.setText(models.get(i).getPanNo());
        viewHolder.tvJoinDate.setText(models.get(i).getJoiningDate());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_customerId)
        TextView tvCustomerId;
        @BindView(R.id.tv_contact)
        TextView tvContact;
        @BindView(R.id.tv_pan)
        TextView tvPan;
        @BindView(R.id.tv_joinDate)
        TextView tvJoinDate;
        @BindView(R.id.tv_branch)
        TextView tvBranch;
        @BindView(R.id.card_view)
        CardView cardView;



        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);





        }
    }
}
