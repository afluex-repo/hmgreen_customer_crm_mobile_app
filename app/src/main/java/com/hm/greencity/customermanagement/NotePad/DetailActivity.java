package com.hm.greencity.customermanagement.NotePad;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.hm.greencity.customermanagement.R;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView detailTextView = findViewById(R.id.detailTextView);
        TextView dataNotesTextView = findViewById(R.id.Data_Notes);
        ImageView backarrow = findViewById(R.id.backarrow);


        String item = getIntent().getStringExtra("ITEM");
        dataNotesTextView.setText(item);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailActivity.this, NotePadActivity.class);
                startActivity(intent);
            }
        });
    }



}