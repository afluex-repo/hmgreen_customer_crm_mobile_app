package com.hm.greencity.customermanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hm.greencity.customermanagement.ImageDetailActivity;
import com.hm.greencity.customermanagement.NotePad.DetailActivity;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.Gallery.PhototsModel;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.RecyclerViewViewHolder> {

    private final List<PhototsModel> lstdue;
    private final Context context;

    public GalleryAdapter(List<PhototsModel> lstdue, Context context) {
        this.lstdue = lstdue;
        this.context = context;
    }

    @NonNull
    @Override
    public GalleryAdapter.RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.RecyclerViewViewHolder holder, int position) {
        PhototsModel phototsModel = lstdue.get(position);
        holder.imageIV.setImageResource(phototsModel.getImage());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ImageDetailActivity.class);
            intent.putExtra("imageResource", phototsModel.getImage());
           // intent.putExtra("itemTitle", phototsModel.getTitle());
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
