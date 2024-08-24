package com.hm.greencity.customermanagement.NotePad;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.BaseActivity;


public class AddNoteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText editText = findViewById(R.id.editTextNote);
        FloatingActionButton buttonAdd = findViewById(R.id.buttonAddNote);
        ImageView backarrow = findViewById(R.id.backarrow);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newNote = editText.getText().toString().trim();
                if (!newNote.isEmpty()) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("NEW_NOTE", newNote);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
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
}