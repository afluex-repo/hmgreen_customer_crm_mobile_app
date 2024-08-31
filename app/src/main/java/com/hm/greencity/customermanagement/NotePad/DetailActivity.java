package com.hm.greencity.customermanagement.NotePad;
import static com.hm.greencity.customermanagement.constants.FileUtils.LST_NOTEPAD;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.constants.FileUtils;
import com.hm.greencity.customermanagement.databinding.ActivityDetailBinding;
import com.hm.greencity.customermanagement.models.Notes.GetNote.LstNotepad;
import com.hm.greencity.customermanagement.models.Notes.GetNote.ResGetNote;
import com.hm.greencity.customermanagement.models.Notes.UpdateNote.ResUpdateNote;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends BaseActivity {
ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding= ActivityDetailBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        initview();
        onclicklistener();

        TextView detailTextView = findViewById(R.id.detailTextView);
        TextView dataNotesTextView = findViewById(R.id.DataNotes);
        ImageView backarrow = findViewById(R.id.backarrow);


        String item = getIntent().getStringExtra("NOTE_DETAILS");

        dataNotesTextView.setText(item);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailActivity.this, NotePadActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onNoteDelete(LstNotepad note) {

    }

    private void initview() {
    }

    private void onclicklistener() {
   binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           UpdateNote();
       }
   });

    }

    private void UpdateNote() {
        String pkID = getIntent().getStringExtra("PK_ID");
        showLoading();
        hideKeyboard();
        JsonObject requestLogin = new JsonObject();
        requestLogin.addProperty("Fk_UserID", PreferencesManager.getInstance(this).getUserId());
        requestLogin.addProperty("Pk_NoteId", pkID);
        requestLogin.addProperty("Notes", binding.DataNotes.getText().toString().trim());
        Log.d("data: ", "Data Request: " +requestLogin);

        Call<ResUpdateNote> call = apiServices.updateNote(requestLogin);
        call.enqueue(new Callback<ResUpdateNote>() {
            @Override
            public void onResponse(Call<ResUpdateNote> call, Response<ResUpdateNote> response) {
                hideLoading();
                if (response.isSuccessful() && response.body() != null) {

                    Toast.makeText(DetailActivity.this, "Note Updated Successfully", Toast.LENGTH_LONG).show();
                    getData();
                } else {
                    Toast.makeText(DetailActivity.this, "Response unsuccessful: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("Response Error", response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResUpdateNote> call, Throwable t) {
                hideLoading();
                Toast.makeText(DetailActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("CreateNote Error", t.toString());
            }
        });
    }



    public void getData() {
//        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserID", PreferencesManager.getInstance(this).getUserId());


        Call<ResGetNote> call = apiServices.getNotes(object);
        call.enqueue(new Callback<ResGetNote>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<ResGetNote> call, Response<ResGetNote> response) {
//                hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    ResGetNote resGetNote = response.body();
                    Log.d(TAG, "Data response: " + response.body());
                    LST_NOTEPAD.clear();
                    if (resGetNote.getLstNotepad() != null) {
                        List<LstNotepad> reversedList = new ArrayList<>(resGetNote.getLstNotepad());
                        Collections.reverse(reversedList);
                        LST_NOTEPAD.addAll(reversedList);
                    }
                    FileUtils.adapter.notifyDataSetChanged();
                    Toast.makeText(DetailActivity.this, "Note Date Get Successfully", Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(DetailActivity.this, NotePadActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(DetailActivity.this, "Response unsuccessful: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Response Error: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResGetNote> call, Throwable t) {
//                hideLoading();
                Toast.makeText(DetailActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, "Error: " + t.toString());
            }
        });
    }



}