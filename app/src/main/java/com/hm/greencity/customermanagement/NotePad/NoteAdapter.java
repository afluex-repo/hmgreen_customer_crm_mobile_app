package com.hm.greencity.customermanagement.NotePad;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.models.Notes.DeleteNote.ResDeleteNote;
import com.hm.greencity.customermanagement.models.Notes.GetNote.LstNotepad;
import com.hm.greencity.customermanagement.models.Notes.GetNote.ResGetNote;
import com.hm.greencity.customermanagement.retrofit.ApiServices;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private List<LstNotepad> mList;
    private List<ResGetNote> list;
    private Context context;

    private  ApiServices apiServices;

    public NoteAdapter(List<LstNotepad> mList, Context context,ApiServices apiServices) {
        this.mList = mList;
        this.list = list;
        this.context = context;
        this.apiServices = apiServices;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Collections.reverse(mList);
        LstNotepad note = mList.get(position);
        holder.textView.setText(note.getNotes());
        holder.date.setText(note.getDate());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("NOTE_DETAILS", note.getNotes());
                intent.putExtra("PK_ID", note.getPk_NoteId());
                context.startActivity(intent);
            }
        });

        holder.deletebutton.setOnClickListener(v -> {
            deleteNoteFromList(note.getPk_NoteId());
        });

    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView,date;
        public ImageView deletebutton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            date = itemView.findViewById(R.id.date);
            deletebutton = itemView.findViewById(R.id.deletebutton);
        }
    }


    private void performOtherAction(int noteId, String userId) {
        Toast.makeText(context, "Note deleted: " + noteId, Toast.LENGTH_SHORT).show();


    }


    private void deleteNoteFromList(String noteId) {
        JsonObject requestObject = new JsonObject();
        requestObject.addProperty("Pk_NoteId", noteId);
        requestObject.addProperty("Fk_UserID", PreferencesManager.getInstance(context).getUserId());

        Call<ResDeleteNote> call = apiServices.deleteNote(requestObject);
        call.enqueue(new Callback<ResDeleteNote>() {
            @Override
            public void onResponse(Call<ResDeleteNote> call, Response<ResDeleteNote> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResDeleteNote resDeleteNote = response.body();
                    if ("200".equals(resDeleteNote.getStatus())) {
                        for (int i = 0; i < mList.size(); i++) {
                            if (mList.get(i).getPk_NoteId().equals(noteId)) {
                                mList.remove(i);
                                notifyItemRemoved(i);
                                notifyItemRangeChanged(i, mList.size());
                                break;
                            }
                        }
                        Toast.makeText(context, "Note deleted successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Deletion failed: " + resDeleteNote.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Response unsuccessful: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResDeleteNote> call, Throwable t) {
                Toast.makeText(context, "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
