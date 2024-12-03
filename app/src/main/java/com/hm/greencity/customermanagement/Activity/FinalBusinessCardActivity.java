package com.hm.greencity.customermanagement.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.provider.MediaStore;
import android.util.Log;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.databinding.ActivityFinalBusinessCardBinding;
import com.hm.greencity.customermanagement.models.BusinessCard.GetBusinessCard.ResGetBusinessCard;
import com.hm.greencity.customermanagement.retrofit.ApiServices;
import com.squareup.picasso.Picasso;
import java.io.ByteArrayOutputStream;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class FinalBusinessCardActivity extends AppCompatActivity {
    ActivityFinalBusinessCardBinding binding;
    private ApiServices apiServices;
    CardView cardView;
    public static final String BASE_URL = "https://abdolphininfratech.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityFinalBusinessCardBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        initview();
        onclicklistener();
        cardView=findViewById(R.id.visitingCard);
        checkAndRequestPermissions();

    }

    private void onclicklistener() {
        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalBusinessCardActivity.this, AssociateContaner.class);
                startActivity(intent);
                finish();
            }
        });

        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(FinalBusinessCardActivity.this,DigitalBusinessCardActivity.class);
               startActivity(intent);
               finish();
            }
        });

        binding.share.setOnClickListener(view -> {
            binding.visitingCard.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Capturing Card Image", Toast.LENGTH_SHORT).show();
            Bitmap screenshot = ViewShot(cardView);
        });

    }

    private void initview() {
        getData();
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
                    binding.name.setText(resGetNote.getName());
                    binding.jobtitle.setText(resGetNote.getJobTitle());
                    binding.businesssummery.setText(resGetNote.getDescription());
                    binding.phone.setText(resGetNote.getMobileNo());
                    binding.email.setText(resGetNote.getEmailId());
                    binding.address.setText(resGetNote.getAddress());

                    binding.mobileno.setText(resGetNote.getMobileNo());
                    binding.cardemailid.setText(resGetNote.getEmailId());
                    binding.cardaddress.setText(resGetNote.getAddress());
                    binding.cardname.setText(resGetNote.getName());
                    loadImage(binding.imagelogo, resGetNote.getImage());

                    String facebookUrl = resGetNote.getFacebook();
                    String phone = resGetNote.getMobileNo();
                    String instagramUrl = resGetNote.getInstagram();
                    String telegramUrl = resGetNote.getTelegram();
                    String whatsappUrl = resGetNote.getWhatsapp();
                    String linkedinUrl = resGetNote.getLinkdin();

                    binding.fb.setOnClickListener(v -> openUrl(facebookUrl));
                    binding.insta.setOnClickListener(v -> openUrl(instagramUrl));
                    binding.telegram.setOnClickListener(v -> openUrl(telegramUrl));
                    binding.whatsapp.setOnClickListener(v -> openWhatsAppChat(whatsappUrl));
                    binding.contact.setOnClickListener(v -> openSMS(phone));
                    binding.call.setOnClickListener(v -> makeCall(phone));
                    binding.linkedIn.setOnClickListener(v -> openUrl(linkedinUrl));


                } else {
                    Toast.makeText(FinalBusinessCardActivity.this, "Response unsuccessful: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("Response Error", response.message());
                }
            }
            @Override
            public void onFailure(Call<ResGetBusinessCard> call, Throwable t) {
                Toast.makeText(FinalBusinessCardActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("CreateNote Error", t.toString());
            }
        });

    }
    private void openUrl(String resourceId) {
        if (resourceId != null && !resourceId.isEmpty()) {
            String fullUrl = resourceId;
            if (!fullUrl.startsWith("http://") && !fullUrl.startsWith("https://")) {
                fullUrl = "https://" + fullUrl;
            }
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl));
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No application found to open this URL", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid resource ID", Toast.LENGTH_SHORT).show();
        }
    }
    private void loadImage(ImageView imageView, String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            Picasso.get()
                    .load(BASE_URL + imagePath.substring(1))
                    .placeholder(R.drawable.box_bg)
                    .into(imageView);
        }
    }
    private void openWhatsAppChat(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            // Construct the WhatsApp URL
            String url = "https://wa.me/" + phoneNumber;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "WhatsApp is not installed on your device.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid WhatsApp number", Toast.LENGTH_SHORT).show();
        }
    }


    public Bitmap ViewShot(View v) {
        int height = v.getHeight();
        int width = v.getWidth();
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("image/jpeg");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "BusinessCard", null);
        Uri imageUri = Uri.parse(path);
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(intent, "Share Business Card"));

        return b;
    }
    private void checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void openSMS(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            Uri uri = Uri.parse("sms:" + phoneNumber);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.putExtra("sms_body", "Hello! I'm reaching out regarding...");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "No SMS app found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show();
        }
    }
    private void makeCall(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        } else {
            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show();
        }
    }


}
