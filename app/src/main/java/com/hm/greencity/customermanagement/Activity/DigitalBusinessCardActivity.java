package com.hm.greencity.customermanagement.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.hm.greencity.customermanagement.databinding.ActivityDigitalBusinessCardBinding;
import java.io.FileNotFoundException;
import java.io.InputStream;



public class DigitalBusinessCardActivity extends AppCompatActivity {
ActivityDigitalBusinessCardBinding binding;
    private static final int REQUEST_CODE_CAMERA = 1;
    private static final int REQUEST_CODE_GALLERY = 2;
    private static final int REQUEST_CODE_PERMISSIONS = 100;
    private ImageView profileImage;
    private Uri imageUri;
    private static final int TARGET_PROFILE_IMAGE = 1;
    private static final int TARGET_BG_IMAGE = 2;
    private int targetImageView = TARGET_BG_IMAGE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding= ActivityDigitalBusinessCardBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initview();
        onclicklistener();

    }

    private void onclicklistener() {
       binding.submitbutton.setOnClickListener(new View.OnClickListener() {
           @Override
             public void onClick(View v) {
             startActivity(new Intent(DigitalBusinessCardActivity.this,FinalBusinessCardActivity.class));
           }
        });


        binding.cameraIcon.setOnClickListener(v -> {
            targetImageView = TARGET_PROFILE_IMAGE;
            showImageSourceDialog();
        });

        binding.textOverlay.setOnClickListener(v -> {
            targetImageView = TARGET_BG_IMAGE;
            showImageSourceDialog();
        });


    }


    private void initview() {

    }


    private void showImageSourceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Image Source");
        builder.setItems(new CharSequence[]{"Take Photo", "Choose from Gallery"}, (dialog, which) -> {
            switch (which) {
                case 0:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSIONS);
                        } else {
                            openCamera();
                        }
                    } else {
                        openCamera();
                    }
                    break;
                case 1:
                    openGallery();
                    break;
            }
        });
        builder.show();
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CODE_CAMERA);
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_CAMERA) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    if (targetImageView == TARGET_PROFILE_IMAGE) {
                        binding.profileImage.setImageBitmap(imageBitmap);
                    } else if (targetImageView == TARGET_BG_IMAGE) {
                        binding.bgimage.setImageBitmap(imageBitmap);
                    }
                }
            } else if (requestCode == REQUEST_CODE_GALLERY) {
                Uri selectedImage = data.getData();
                try {
                    InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                    Bitmap selectedBitmap = BitmapFactory.decodeStream(imageStream);
                    if (targetImageView == TARGET_PROFILE_IMAGE) {
                        binding.profileImage.setImageBitmap(selectedBitmap);
                    } else if (targetImageView == TARGET_BG_IMAGE) {
                        binding.bgimage.setImageBitmap(selectedBitmap);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission is required to take a photo", Toast.LENGTH_LONG).show();
            }
        }
    }

}