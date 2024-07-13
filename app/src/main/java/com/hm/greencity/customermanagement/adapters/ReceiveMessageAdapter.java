package com.hm.greencity.customermanagement.adapters;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.models.chatModel.QueryItem;
import com.hm.greencity.customermanagement.models.chatModel.RequestChat;
import com.squareup.picasso.Picasso;
import java.util.List;



public class ReceiveMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> messageList;
    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;


    public ReceiveMessageAdapter(List<Object> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int getItemViewType(int position) {
        if (messageList.get(position) instanceof RequestChat) {
            return VIEW_TYPE_SENT;
        } else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_SENT) {
            view = inflater.inflate(R.layout.item_sent_message, parent, false);
            return new SentMessageViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.item_received_message, parent, false);
            return new ReceivedMessageViewHolder(view, this); // Pass adapter instance
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_SENT) {
            ((SentMessageViewHolder) holder).bind((RequestChat) messageList.get(position));
        } else {
            ((ReceivedMessageViewHolder) holder).bind((QueryItem) messageList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMessage;

        public SentMessageViewHolder(View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.textViewMessage);
        }

        public void bind(RequestChat message) {
            textViewMessage.setText(message.getDescription());
        }
    }

    public static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewAddedDate, admintextViewAddedDate, replyDate;
        ImageView imageView, ReplyimageView; // ImageView for displaying image
        LinearLayout userreply, adminreply;
        AppCompatButton viewReply;

        private ReceiveMessageAdapter adapter; // Adapter instance

        public ReceivedMessageViewHolder(View itemView, ReceiveMessageAdapter adapter) {
            super(itemView);
            this.adapter = adapter;

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewAddedDate = itemView.findViewById(R.id.textViewAddedDate);
            admintextViewAddedDate = itemView.findViewById(R.id.admintextViewAddedDate);
            imageView = itemView.findViewById(R.id.imageView);
            userreply = itemView.findViewById(R.id.userreply);
            adminreply = itemView.findViewById(R.id.adminreply);
            replyDate = itemView.findViewById(R.id.replyDate);
            ReplyimageView = itemView.findViewById(R.id.ReplyimageView);
            viewReply = itemView.findViewById(R.id.viewReply);

            // Set click listeners
            viewReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        QueryItem message = (QueryItem) adapter.messageList.get(position);
                        showAdminReplyDialog(v.getContext(), message);
                    }
                }
            });

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showEnlargedImageDialog(imageView.getContext(), imageView.getDrawable());
                }
            });

            ReplyimageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showEnlargedImageDialog(ReplyimageView.getContext(), ReplyimageView.getDrawable());
                }
            });
        }

        public void bind(QueryItem message) {
            textViewTitle.setText(message.getTitle());
            textViewDescription.setText(message.getDescription());
            textViewAddedDate.setText(message.getAddedDate());
            admintextViewAddedDate.setText(message.getReplyMessage());
            replyDate.setText(message.getReplyDate());

            if (message.getReplyImage() != null && !message.getReplyImage().isEmpty()) {
                String imageUrl1 = "https://crm.hmgreencity.com" + message.getReplyImage();

                Picasso.get()
                        .load(imageUrl1)
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.hm_green_new_logo)
                        .into(ReplyimageView);
                ReplyimageView.setVisibility(View.VISIBLE);
            } else {
                ReplyimageView.setVisibility(View.GONE);
            }

            if (message.getReplyMessage() != null && !message.getReplyMessage().isEmpty()) {
                adminreply.setVisibility(View.GONE);
            } else {
                adminreply.setVisibility(View.GONE);
            }

            if (message.getImages() != null && !message.getImages().isEmpty()) {
                String imageUrl = "https://crm.hmgreencity.com" + message.getImages();

                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.hm_green_new_logo)
                        .into(imageView);

                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
            }
        }

        private void showEnlargedImageDialog(Context context, Drawable imageDrawable) {
            View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_enlarged_image, null);
            ImageView imageViewEnlarged = dialogView.findViewById(R.id.imageViewEnlarged);
            ImageView closeButton = dialogView.findViewById(R.id.btnClose);
            imageViewEnlarged.setImageDrawable(imageDrawable);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(dialogView);
            AlertDialog dialog = builder.create();
            dialog.show();


            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        private void showAdminReplyDialog(Context context, QueryItem message) {
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.item_admin_reply);
            TextView replyDateTextView = dialog.findViewById(R.id.replyDate);
            TextView adminReplyDateTextView = dialog.findViewById(R.id.admintextViewAddedDate);
            ImageView replyImageView = dialog.findViewById(R.id.ReplyimageView);
            TextView noDataTextView = dialog.findViewById(R.id.noDataTextView); // TextView for showing no data message

            replyDateTextView.setText(message.getReplyDate());
            adminReplyDateTextView.setText(message.getReplyMessage());

            // Set click listener for the image view inside the dialog
            replyImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Show enlarged image dialog if image exists
                    if (replyImageView.getDrawable() != null) {
                        showEnlargedImageDialog(replyImageView.getContext(), replyImageView.getDrawable());
                    } else {
                        // Handle case where image is not available
                        Toast.makeText(context, "No image available", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            if (message.getReplyImage() != null && !message.getReplyImage().isEmpty()) {
                String imageUrl = "https://crm.hmgreencity.com" + message.getReplyImage();

                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.logoh) // Placeholder image
                        .error(R.drawable.logo) // Error image if loading fails
                        .into(replyImageView);

                replyImageView.setVisibility(View.VISIBLE); // Ensure image view is visible if there's an image to show
                noDataTextView.setVisibility(View.GONE); // Hide no data message if image exists
            } else {
                replyImageView.setVisibility(View.GONE); // Hide image view if no image
                noDataTextView.setVisibility(View.VISIBLE); // Show no data message
            }

            if (dialog.getWindow() != null) {
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }

            // Show the dialog
            dialog.show();
        }



    }
}
