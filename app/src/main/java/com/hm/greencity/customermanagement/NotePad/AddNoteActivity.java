package com.hm.greencity.customermanagement.NotePad;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.models.Notes.CreateNote.ResCreateNote;
import com.hm.greencity.customermanagement.models.Notes.GetNote.LstNotepad;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddNoteActivity extends BaseActivity {
    private EditText editTextNote;
    private PreferencesManager preferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextNote = findViewById(R.id.editTextNote);
        FloatingActionButton buttonAdd = findViewById(R.id.buttonAddNote);
        ImageView backarrow = findViewById(R.id.backarrow);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNote();
            }
        });

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AddNoteActivity.this, NotePadActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onNoteDelete(LstNotepad note) {

    }


    private void CreateNote() {
        showLoading();
        hideKeyboard();
        JsonObject requestLogin = new JsonObject();
        requestLogin.addProperty("Fk_UserID", PreferencesManager.getInstance(this).getUserId());
        requestLogin.addProperty("Notes", editTextNote.getText().toString().trim());

        Call<ResCreateNote> call = apiServices.createNote(requestLogin);
        call.enqueue(new Callback<ResCreateNote>() {
            @Override
            public void onResponse(Call<ResCreateNote> call, Response<ResCreateNote> response) {
                hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    Intent intent= new Intent(AddNoteActivity.this, NotePadActivity.class);
                    startActivity(intent);
                    Toast.makeText(AddNoteActivity.this, "Note Created Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AddNoteActivity.this, "Response unsuccessful: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("Response Error", response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResCreateNote> call, Throwable t) {
                hideLoading();
                Toast.makeText(AddNoteActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("CreateNote Error", t.toString());
            }
        });
    }
}