package com.hm.greencity.customermanagement.Activity;
import static com.hm.greencity.customermanagement.Activity.FinalBusinessCardActivity.BASE_URL;
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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.Network.RetrofitClient1;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.databinding.ActivityDigitalBusinessCardBinding;
import com.hm.greencity.customermanagement.models.BusinessCard.CreateBusinessCard.ResCreateBusinessCard;
import com.hm.greencity.customermanagement.models.BusinessCard.GetBusinessCard.ResGetBusinessCard;
import com.hm.greencity.customermanagement.models.BusinessCard.UpdateBusinessCard.ResUpdateBusinessCard;
import com.hm.greencity.customermanagement.retrofit.ApiServices;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    private ApiServices apiServices;
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
                // Check if each required field is empty
                if (TextUtils.isEmpty(binding.companyName.getText().toString())) {
                    Toast.makeText(DigitalBusinessCardActivity.this, "Enter Company Name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.businessnaem.getText().toString())) {
                    Toast.makeText(DigitalBusinessCardActivity.this, "Enter Business Name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.jobtitle.getText().toString())) {
                    Toast.makeText(DigitalBusinessCardActivity.this, "Enter Job Title", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.designation.getText().toString())) {
                    Toast.makeText(DigitalBusinessCardActivity.this, "Enter Designation", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.phone.getText().toString())) {
                    Toast.makeText(DigitalBusinessCardActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.email.getText().toString())) {
                    Toast.makeText(DigitalBusinessCardActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.address.getText().toString())) {
                    Toast.makeText(DigitalBusinessCardActivity.this, "Enter Address", Toast.LENGTH_SHORT).show();
                } else {
                    // If all fields are filled, proceed to create the card
                    createCard();
                }
            }
        });
        binding.updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCard();
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
        getData();
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

    private void createCard() {
        // Create RequestBody for text data
        RequestBody userId = RequestBody.create(MultipartBody.FORM, PreferencesManager.getInstance(this).getUserId());
        RequestBody companyName = RequestBody.create(MultipartBody.FORM, binding.companyName.getText().toString());
        RequestBody businessName = RequestBody.create(MultipartBody.FORM, binding.businessnaem.getText().toString());
        RequestBody jobTitle = RequestBody.create(MultipartBody.FORM, binding.jobtitle.getText().toString());
        RequestBody description = RequestBody.create(MultipartBody.FORM, binding.designation.getText().toString());
        RequestBody mobileNo = RequestBody.create(MultipartBody.FORM, binding.phone.getText().toString());
        RequestBody emailId = RequestBody.create(MultipartBody.FORM, binding.email.getText().toString());
        RequestBody address = RequestBody.create(MultipartBody.FORM, binding.address.getText().toString());
        RequestBody whatsapp = RequestBody.create(MultipartBody.FORM, binding.Whatsapp.getText().toString());
        RequestBody instagram = RequestBody.create(MultipartBody.FORM, binding.insta.getText().toString());
        RequestBody facebook = RequestBody.create(MultipartBody.FORM, binding.fb.getText().toString());
        RequestBody linkdin = RequestBody.create(MultipartBody.FORM, binding.linkedIn.getText().toString());
        RequestBody telegram = RequestBody.create(MultipartBody.FORM, binding.telegram.getText().toString());

        // Convert ImageView images to File
        File profileImageFile = getFileFromImageView(binding.profileImage);
        File coverImageFile = getFileFromImageView(binding.bgimage);

        // Create MultipartBody.Part for images
        MultipartBody.Part profileImagePart = profileImageFile != null ?
                MultipartBody.Part.createFormData("Profile", profileImageFile.getName(),
                        RequestBody.create(MediaType.parse("image/jpeg"), profileImageFile)) : null;

        MultipartBody.Part coverImagePart = coverImageFile != null ?
                MultipartBody.Part.createFormData("CoverImage", coverImageFile.getName(),
                        RequestBody.create(MediaType.parse("image/jpeg"), coverImageFile)) : null;

        // Call your API with multipart data
        ApiServices apiServices = RetrofitClient1.getClient("https://crm.hmgreencity.com/").create(ApiServices.class);

        Call<ResCreateBusinessCard> call = apiServices.businesscard(
                userId,
                companyName,
                businessName,
                jobTitle,
                description,
                mobileNo,
                emailId,
                address,
                whatsapp,
                instagram,
                facebook,
                linkdin,
                telegram,
                coverImagePart,   // Sending the cover image as multipart
                profileImagePart  // Sending the profile image as multipart
        );

        // Enqueue the call
        call.enqueue(new Callback<ResCreateBusinessCard>() {
            @Override
            public void onResponse(Call<ResCreateBusinessCard> call, Response<ResCreateBusinessCard> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Intent intent = new Intent(DigitalBusinessCardActivity.this, FinalBusinessCardActivity.class);
                    startActivity(intent);
                    Toast.makeText(DigitalBusinessCardActivity.this, "Document Uploaded Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(DigitalBusinessCardActivity.this, "Response unsuccessful: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("Response Error", response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResCreateBusinessCard> call, Throwable t) {
                Toast.makeText(DigitalBusinessCardActivity.this, "Upload Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Upload Error", t.toString());
            }
        });
    }
    private File getFileFromImageView(ImageView imageView) {
        imageView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
        imageView.setDrawingCacheEnabled(false);

        File file = new File(getCacheDir(), "image.jpg");
        try (FileOutputStream outStream = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.exists() ? file : null;
    }
    private void updateCard() {
        // Create RequestBody for text data
        RequestBody businessId = RequestBody.create(MultipartBody.FORM, PreferencesManager.getInstance(this).getPkBussinessCardId());
        RequestBody userId = RequestBody.create(MultipartBody.FORM, PreferencesManager.getInstance(this).getUserId());
        RequestBody companyName = RequestBody.create(MultipartBody.FORM, binding.companyName.getText().toString());
        RequestBody businessName = RequestBody.create(MultipartBody.FORM, binding.businessnaem.getText().toString());
        RequestBody jobTitle = RequestBody.create(MultipartBody.FORM, binding.jobtitle.getText().toString());
        RequestBody description = RequestBody.create(MultipartBody.FORM, binding.designation.getText().toString());
        RequestBody mobileNo = RequestBody.create(MultipartBody.FORM, binding.phone.getText().toString());
        RequestBody emailId = RequestBody.create(MultipartBody.FORM, binding.email.getText().toString());
        RequestBody address = RequestBody.create(MultipartBody.FORM, binding.address.getText().toString());
        RequestBody whatsapp = RequestBody.create(MultipartBody.FORM, binding.Whatsapp.getText().toString());
        RequestBody instagram = RequestBody.create(MultipartBody.FORM, binding.insta.getText().toString());
        RequestBody facebook = RequestBody.create(MultipartBody.FORM, binding.fb.getText().toString());
        RequestBody linkdin = RequestBody.create(MultipartBody.FORM, binding.linkedIn.getText().toString());
        RequestBody telegram = RequestBody.create(MultipartBody.FORM, binding.telegram.getText().toString());
        // Convert ImageView images to File
        File profileImageFile = getFileFromImageView(binding.profileImage);
        File coverImageFile = getFileFromImageView(binding.bgimage);

        // Create MultipartBody.Part for images
        MultipartBody.Part profileImagePart = profileImageFile != null ?
                MultipartBody.Part.createFormData("Profile", profileImageFile.getName(),
                        RequestBody.create(MediaType.parse("image/jpeg"), profileImageFile)) : null;

        MultipartBody.Part coverImagePart = coverImageFile != null ?
                MultipartBody.Part.createFormData("CoverImage", coverImageFile.getName(),
                        RequestBody.create(MediaType.parse("image/jpeg"), coverImageFile)) : null;
        // Call your API with multipart data
        ApiServices apiServices = RetrofitClient1.getClient("https://crm.hmgreencity.com/").create(ApiServices.class);
        Call<ResUpdateBusinessCard> call = apiServices.updatecard(
                businessId,
                userId,
                companyName,
                businessName,
                jobTitle,
                description,
                mobileNo,
                emailId,
                address,
                whatsapp,
                instagram,
                facebook,
                linkdin,
                telegram,
                coverImagePart,
                profileImagePart
        );

        // Enqueue the call
        call.enqueue(new Callback<ResUpdateBusinessCard>() {
            @Override
            public void onResponse(Call<ResUpdateBusinessCard> call, Response<ResUpdateBusinessCard> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Intent intent = new Intent(DigitalBusinessCardActivity.this, FinalBusinessCardActivity.class);
                    startActivity(intent);
                    Toast.makeText(DigitalBusinessCardActivity.this, "Document Uploaded Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(DigitalBusinessCardActivity.this, "Response unsuccessful: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("Response Error", response.toString());
                }
            }
            @Override
            public void onFailure(Call<ResUpdateBusinessCard> call, Throwable t) {
                Toast.makeText(DigitalBusinessCardActivity.this, "Upload Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Upload Error", t.toString());
            }
        });
    }
    private void getData() {
        ApiServices apiServices = RetrofitClient.getApiServices();
        JsonObject requestLogin = new JsonObject();
        requestLogin.addProperty("Fk_UserID", PreferencesManager.getInstance(this).getUserId());
        Call<ResGetBusinessCard> call = apiServices.getcarddetails(requestLogin);
        call.enqueue(new Callback<ResGetBusinessCard>() {
            @Override
            public void onResponse(Call<ResGetBusinessCard> call, Response<ResGetBusinessCard> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResGetBusinessCard resGetNote = response.body();
                    binding.companyName.setText(resGetNote.getName());
                    binding.jobtitle.setText(resGetNote.getJobTitle());
                    binding.businessnaem.setText(resGetNote.getDescription());
                    binding.phone.setText(resGetNote.getMobileNo());
                    binding.email.setText(resGetNote.getEmailId());
                    binding.address.setText(resGetNote.getAddress());
                    binding.fb.setText(resGetNote.getFacebook());
                    binding.insta.setText(resGetNote.getInstagram());
                    binding.Whatsapp.setText(resGetNote.getWhatsapp());
                    binding.linkedIn.setText(resGetNote.getLinkdin());
                    binding.telegram.setText(resGetNote.getLinkdin());
                    loadImage(binding.profileImage, resGetNote.getImage());
                    loadImage(binding.bgimage, resGetNote.getBackgroundImage());

                    //Toast.makeText(DigitalBusinessCardActivity.this, "", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(DigitalBusinessCardActivity.this, "Response unsuccessful: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("Response Error", response.message());
                }
            }
            @Override
            public void onFailure(Call<ResGetBusinessCard> call, Throwable t) {
                Toast.makeText(DigitalBusinessCardActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("CreateNote Error", t.toString());
            }
        });
    }
    private void loadImage(ImageView imageView, String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            Picasso.get()
                    .load(BASE_URL + imagePath.substring(1))
                    .placeholder(R.drawable.box_bg)
                    .into(imageView);
        }
    }

}