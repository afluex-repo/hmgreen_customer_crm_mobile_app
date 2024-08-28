package com.hm.greencity.customermanagement.NotePad;

import static com.hm.greencity.customermanagement.constants.FileUtils.LST_NOTEPAD;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.Activity.AssociateContaner;
import com.hm.greencity.customermanagement.Activity.HomeTestActivity;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.constants.FileUtils;
import com.hm.greencity.customermanagement.databinding.ActivityNotePadBinding;
import com.hm.greencity.customermanagement.models.Notes.GetNote.LstNotepad;
import com.hm.greencity.customermanagement.models.Notes.GetNote.ResGetNote;
import com.hm.greencity.customermanagement.retrofit.ApiServices;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotePadActivity extends BaseActivity {
    ActivityNotePadBinding binding;
    private static final int REQUEST_CODE_ADD_NOTE = 1;
    private ApiServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityNotePadBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        apiServices = RetrofitClient.getClient().create(ApiServices.class);

        initview();
        onclicklistener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK) {
            // Refresh the list of notes
            getData();
        }
    }

    private void onclicklistener() {
        binding.addButton.setOnClickListener(v -> {
            Intent intent = new Intent(NotePadActivity.this, AddNoteActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_NOTE);
        });

        binding.backarrow.setOnClickListener(v -> {
            if (!PreferencesManager.getInstance(getApplicationContext()).getUserType().equalsIgnoreCase("")) {
                Intent I;
                if (PreferencesManager.getInstance(getApplicationContext()).getUserType().equalsIgnoreCase("Trad Associate")) {
                    I = new Intent(NotePadActivity.this, AssociateContaner.class);
                } else {
                    I = new Intent(NotePadActivity.this, HomeTestActivity.class);
                }
                startActivity(I);
                finish();
            }
        });
    }

    private void initview() {
        FileUtils.initializeAdapter(FileUtils.LST_NOTEPAD, this, apiServices);

//        adapter = new NoteAdapter(LST_NOTEPAD, this, apiServices);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        binding.listView.setLayoutManager(layoutManager);
        binding.listView.setAdapter(FileUtils.adapter);

        getData();
    }

    public void getData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserID", PreferencesManager.getInstance(this).getUserId());

        Call<ResGetNote> call = apiServices.getNotes(object);
        call.enqueue(new Callback<ResGetNote>() {
            @Override
            public void onResponse(Call<ResGetNote> call, Response<ResGetNote> response) {
                hideLoading();
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
                } else {
                    Toast.makeText(NotePadActivity.this, "Response unsuccessful: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Response Error: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResGetNote> call, Throwable t) {
                hideLoading();
                Toast.makeText(NotePadActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, "Error: " + t.toString());
            }
        });
    }

    @Override
    public void onNoteDelete(LstNotepad note) {

    }
}
