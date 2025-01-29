package com.hm.greencity.customermanagement;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;



public class ImageDetailActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        imageView = findViewById(R.id.idIVImage);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl");
        if (!imageUrl.startsWith("http")) {
            imageUrl = "https://crm.hmgreencity.com/" + imageUrl;
        }
        Log.d("ImageDetailActivity", "Received image URL: " + imageUrl);
        if (imageUrl == null || imageUrl.isEmpty()) {
            Log.d("ImageDetailActivity", "Image URL is null or empty");
            imageView.setImageResource(R.drawable.news_icon);
        } else {
            Log.d("ImageDetailActivity", "Loading image from URL: " + imageUrl);
            Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.logo)
                    .error(com.vansuita.pickimage.R.drawable.camera)
                    .fallback(R.drawable.news_icon)
                    .into(imageView);

        }
    }
}
