package com.hm.greencity.customermanagement.NotePad;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hm.greencity.customermanagement.R;
import java.util.ArrayList;
import java.util.List;


public class NotePadActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ADD_NOTE = 1;
    private MyAdapter adapter;
    private List<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_pad);


        ListView listView = findViewById(R.id.listView);
        FloatingActionButton addNoteButton = findViewById(R.id.add_button);
        ImageView backarrow = findViewById(R.id.backarrow);

        items = new ArrayList<>();
        adapter = new MyAdapter(this, items);
        listView.setAdapter(adapter);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotePadActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_NOTE);
            }
        });

//        backarrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(NotePadActivity.this, AssociateContaner.class);
//                startActivity(intent);
//            }
//        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK) {
            String newNote = data.getStringExtra("NEW_NOTE");
            if (newNote != null) {
                items.add(newNote);
                adapter.notifyDataSetChanged();
            }
        }
    }

}