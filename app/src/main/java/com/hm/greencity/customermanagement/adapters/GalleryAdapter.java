package com.hm.greencity.customermanagement.adapters;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.hm.greencity.customermanagement.ImageDetailActivity;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.Gallery.Lstgallery;
import java.util.List;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.RecyclerViewViewHolder> {

    private final List<Lstgallery> lstdue;
    private final Context context;

    public GalleryAdapter(List<Lstgallery> lstdue, Context context) {
        this.lstdue = lstdue;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
        final Lstgallery lstgallery = lstdue.get(position);
        final String mediaType = lstgallery.getMediaType();
        final String originalDocument = lstgallery.getDocuments();

        String documentUrl = originalDocument;

        if ("Image".equals(mediaType)) {
            Log.d("GalleryAdapter", "Loading image URL: " + documentUrl);


            if (!documentUrl.startsWith("http")) {
                documentUrl = "https://crm.hmgreencity.com/" + documentUrl;
            }

            Glide.with(context)
                    .load(documentUrl)
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.camera)
                    .fallback(R.drawable.chat)
                    .into(holder.imageIV);
        } else {
            holder.imageIV.setImageResource(R.drawable.hm_logo);
        }

        holder.itemView.setOnClickListener(v -> {
//            ImageDetailActivity imageDetailActivity=new ImageDetailActivity(""+originalDocument);
            Intent intent = new Intent(v.getContext(), ImageDetailActivity.class);
            Log.d("image",""+originalDocument);
            intent.putExtra("imageUrl", ""+originalDocument);
            v.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return lstdue.size();
    }

    public static class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageIV;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIV = itemView.findViewById(R.id.idIVImage);
        }
    }
}
