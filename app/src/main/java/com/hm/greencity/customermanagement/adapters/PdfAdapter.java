package com.hm.greencity.customermanagement.adapters;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.PDF.PdfItem;
import java.util.List;



public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.ViewHolder> {

    private List<PdfItem> pdfList;
    private Context context;

    public PdfAdapter(List<PdfItem> pdfList, Context context) {
        this.pdfList = pdfList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pdf, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PdfItem pdfItem = pdfList.get(position);
        holder.titleTextView.setText(pdfItem.getTitle());

        holder.itemView.setOnClickListener(v -> {
            openPDF(pdfItem.getPdfUrl());
        });
    }

    @Override
    public int getItemCount() {
        return pdfList.size();
    }

    private void openPDF(String pdfUrl) {
        Uri pdfUri = Uri.parse(pdfUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(pdfUri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "No PDF viewer found", Toast.LENGTH_SHORT).show();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.pdf_title);
        }
    }


}
