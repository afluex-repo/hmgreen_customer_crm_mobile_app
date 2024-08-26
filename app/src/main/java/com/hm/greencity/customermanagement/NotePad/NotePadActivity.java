package com.hm.greencity.customermanagement.NotePad;
import static android.content.ContentValues.TAG;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.Activity.AssociateContaner;
import com.hm.greencity.customermanagement.Activity.HomeTestActivity;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.common.PreferencesManager;
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
    private NoteAdapter adapter;
    private List<LstNotepad> lstNotepad;
    private List<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityNotePadBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        apiServices = RetrofitClient.getClient().create(ApiServices.class);

        initview();
        onclicklistener();


    }

    @Override
    public void onNoteDelete(LstNotepad note) {

    }

    private void onclicklistener() {
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotePadActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_NOTE);
            }
        });

        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!PreferencesManager.getInstance(getApplicationContext()).getUserType().equalsIgnoreCase("")) {

                    if (PreferencesManager.getInstance(getApplicationContext()).getUserType().equalsIgnoreCase("Trad Associate")) {
                        Intent I = new Intent(NotePadActivity.this, AssociateContaner.class);
                        startActivity(I);
                        finish();
                    } else if (PreferencesManager.getInstance(getApplicationContext()).getUserType().equalsIgnoreCase("Customer")) {
                        Intent I = new Intent(NotePadActivity.this, HomeTestActivity.class);
                        startActivity(I);
                        finish();

                    }

                }
            }
        });

    }

    private void initview() {
        lstNotepad = new ArrayList<>();
        adapter = new NoteAdapter(lstNotepad, this, apiServices);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        binding.listView.setLayoutManager(layoutManager);
        binding.listView.setAdapter(adapter);

        getData();
    }



    public void getData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserID", PreferencesManager.getInstance(context).getUserId());

        Call<ResGetNote> call = apiServices.getNotes(object);
        call.enqueue(new Callback<ResGetNote>() {
            @Override
            public void onResponse(Call<ResGetNote> call, Response<ResGetNote> response) {
                hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    hideLoading();
                    ResGetNote resVisitorList = response.body();
                    Log.d(TAG, "Data response: " + response.body());
                    lstNotepad.clear();
                    if (resVisitorList.getLstNotepad() != null) {
                        List<LstNotepad> reversedList = new ArrayList<>(resVisitorList.getLstNotepad());
                        Collections.reverse(reversedList);
                        lstNotepad.addAll(reversedList);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(NotePadActivity.this, "Response unsuccessful: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("Response Error", response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResGetNote> call, Throwable t) {
                hideLoading();
                Toast.makeText(NotePadActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("CreateNote Error", t.toString());
            }
        });
    }





}