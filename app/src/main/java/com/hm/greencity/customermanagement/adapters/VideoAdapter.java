package com.hm.greencity.customermanagement.adapters;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.Gallery.Lstgallery;
import com.squareup.picasso.Picasso;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final List<Lstgallery> mediaList;
    private final Context context;

    public VideoAdapter(List<Lstgallery> mediaList, Context context) {
        this.mediaList = mediaList;
        this.context = context;

    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Lstgallery mediaItem = mediaList.get(position);
        String mediaType = mediaItem.getMediaType();
        String document = mediaItem.getDocuments();

        if ("VideoLinks".equals(mediaType)) {
            Picasso.get()
                    .load(R.drawable.you)
                    .into(holder.thumbnail);

            holder.itemView.setOnClickListener(v -> {
                if (document != null && !document.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(document));
                    context.startActivity(intent);
                }
            });
        } else {
            holder.thumbnail.setImageResource(com.vansuita.pickimage.R.drawable.camera);
            holder.itemView.setOnClickListener(null);
        }

        holder.title.setText("Click to watch");
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
        }
    }


}
