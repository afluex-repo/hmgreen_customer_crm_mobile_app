package com.hm.greencity.customermanagement.adapters;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.BroucherModel.Brochurelst;
import com.hm.greencity.customermanagement.models.LayoutModel.LstSiteLayout;

import java.util.List;


public class BroucherAdapter extends RecyclerView.Adapter<BroucherAdapter.Viewholder> {
    private List<Brochurelst> itemList;
    private Context context;

    public BroucherAdapter(List<Brochurelst> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public BroucherAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pdf_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BroucherAdapter.Viewholder holder, int position) {
        Brochurelst item = itemList.get(position);
        final String documentUrl = item.getSiteBrochure();

        if (documentUrl != null && documentUrl.endsWith(".pdf")) {
            holder.iconImageView.setImageResource(R.drawable.broucher);
            holder.titleTextView.setText(item.getSiteName());

            String fullUrl;
            if (!documentUrl.startsWith("http") && !documentUrl.startsWith("https")) {
                fullUrl = "https://crm.hmgreencity.com" + documentUrl;
            } else {
                fullUrl = documentUrl;
            }

            if (fullUrl != null && !fullUrl.isEmpty()) {
                holder.itemView.setOnClickListener(v -> {
                    openDocument(fullUrl);
                });
            } else {
                holder.itemView.setOnClickListener(null);
            }
        } else {
            holder.titleTextView.setText("Unknown");
            holder.iconImageView.setImageResource(R.drawable.logoh);
            holder.itemView.setOnClickListener(null);
        }
    }


    private void openDocument(String documentUrl) {
        try {
            Uri documentUri = Uri.parse(documentUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(documentUri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);

            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "No PDF viewer found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error opening document: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView iconImageView;
        TextView titleTextView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            iconImageView = itemView.findViewById(R.id.pdf_thumbnail);
            titleTextView = itemView.findViewById(R.id.pdf_title);
        }
    }
}
