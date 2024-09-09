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
import com.hm.greencity.customermanagement.models.Gallery.Lstgallery;
import java.util.List;


public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.ViewHolder> {
    private List<Lstgallery> itemList;
    private Context context;


    public PdfAdapter(List<Lstgallery> itemList, Context context) {
        this.itemList = itemList;
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
        Lstgallery item = itemList.get(position);
        String mediaType = item.getMediaType();
        final String documentUrl = item.getDocuments();

        if ("Document".equals(mediaType)) {
            holder.iconImageView.setImageResource(R.drawable.baseline_picture_as_pdf_24);
            holder.titleTextView.setText("PDF Document");

            String fullUrl;
            if (documentUrl != null && !documentUrl.startsWith("http") && !documentUrl.startsWith("https")) {
                fullUrl = "https://crm.hmgreencity.com/" + documentUrl;
            } else {
                fullUrl = documentUrl;
            }

            holder.itemView.setOnClickListener(v -> {
                if (fullUrl != null && !fullUrl.isEmpty()) {
                    openDocument(fullUrl);
                } else {
                    Toast.makeText(context, "Document URL is empty", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            holder.iconImageView.setImageResource(R.drawable.camera);
            holder.titleTextView.setText("Unknown");

            holder.itemView.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private void openDocument(String documentUrl) {
        Uri documentUri = Uri.parse(documentUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(documentUri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "No PDF viewer found", Toast.LENGTH_SHORT).show();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImageView;
        TextView titleTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.pdf);
            titleTextView = itemView.findViewById(R.id.pdf_title);
        }
    }


}
