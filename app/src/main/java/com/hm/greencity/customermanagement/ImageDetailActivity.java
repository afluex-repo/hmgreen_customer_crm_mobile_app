package com.hm.greencity.customermanagement;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;



public class ImageDetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private float mScaleFactor = 1.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);


        ImageView imageView = findViewById(R.id.idIVImage);
     //   TextView titleTextView = findViewById(R.id.titleTextViewDetail);

        Intent intent = getIntent();
        int imageResource = intent.getIntExtra("imageResource", -1);
      //  String itemTitle = intent.getStringExtra("itemTitle");
       // imageView.setImageResource(imageResource);


        if (imageResource != -1) {
            imageView.setImageResource(imageResource);
        }
       //titleTextView.setText(itemTitle);





    }






    }




