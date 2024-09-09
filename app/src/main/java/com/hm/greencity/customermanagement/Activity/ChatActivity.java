package com.hm.greencity.customermanagement.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.hm.greencity.customermanagement.Network.ChatService;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.adapters.ReceiveMessageAdapter;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.databinding.ActivityChatBinding;
import com.hm.greencity.customermanagement.models.chatModel.FetchMessageRequest;
import com.hm.greencity.customermanagement.models.chatModel.QueryItem;
import com.hm.greencity.customermanagement.models.chatModel.ResponseChat;
import com.hm.greencity.customermanagement.models.chatModel.getResponseChat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    private static final int REQUEST_CAMERA_CAPTURE = 1;
    private static final int REQUEST_GALLERY_PICK = 2;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private RecyclerView recyclerView;
    private EditText editTextMessage;
    private Button buttonSend;
    private ImageView imageView;
    private Uri selectedImageUri;
    private Uri capturedImageUri;
    private ChatService chatService;
    private List<Object> messageList = new ArrayList<>();
    private ReceiveMessageAdapter messageAdapter;
    private Handler handler = new Handler();
    private Runnable runnable;
    private PreferencesManager preferencesManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initview();
        onclicklistener();
        fetchMessages();
        startPeriodicFetching();


    }

    private void onclicklistener() {
        binding.createquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ChatActivity.this,SendQueryActivity.class);
                startActivity(intent);
            }
        });

        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ChatActivity.this,HomeTestActivity.class);
                startActivity(intent);
            }
        });
        binding.buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        binding.attachbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissions()) {
                    showImageSourceOptions();
                } else {
                    requestPermissions();
                }
            }
        });

//        binding.call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String phone_number = "9651997374";
//                Intent phone_intent = new Intent(Intent.ACTION_DIAL);
//                phone_intent.setData(Uri.parse("tel:" + phone_number));
//                startActivity(phone_intent);
//            }
//        });

    }

    private void initview() {
        recyclerView = findViewById(R.id.recyclerView);
        editTextMessage = findViewById(R.id.messaget);
        imageView = findViewById(R.id.imageView);
        preferencesManager = PreferencesManager.getInstance(this);
        chatService = RetrofitClient.getClient().create(ChatService.class);
        messageAdapter = new ReceiveMessageAdapter(messageList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(messageAdapter);

    }

    private void showImageSourceOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Image Source");
        builder.setItems(new CharSequence[]{"Camera", "Gallery"},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // Camera
                                openCamera();
                                break;
                            case 1: // Gallery
                                openGallery();
                                break;
                        }
                    }
                });
        builder.show();

    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CAMERA_CAPTURE);
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_GALLERY_PICK);
    }

    private boolean checkPermissions() {
        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        return cameraPermission == PackageManager.PERMISSION_GRANTED &&
                writePermission == PackageManager.PERMISSION_GRANTED &&
                readPermission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                showImageSourceOptions();
            } else {
                Toast.makeText(this, "Camera and Storage permissions are required.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA_CAPTURE) {
                if (data != null && data.getData() != null) {
                    selectedImageUri = data.getData();
                } else {
                    selectedImageUri = capturedImageUri;
                }
            } else if (requestCode == REQUEST_GALLERY_PICK) {
                if (data != null && data.getData() != null) {
                    selectedImageUri = data.getData();
                }
            }

            if (selectedImageUri != null) {
                imageView.setImageURI(selectedImageUri);
                imageView.setVisibility(View.VISIBLE);
            }
        }
    }

    private void sendMessage() {
        String messageContent = editTextMessage.getText().toString();
        if (!messageContent.isEmpty() || selectedImageUri != null) {
            PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
            String userId = preferencesManager.getUserId();
            if (userId == null || userId.isEmpty()) {
                Toast.makeText(this, "User ID is invalid", Toast.LENGTH_SHORT).show();
                return;
            }
            RequestBody fkUserId = RequestBody.create(MediaType.parse("text/plain"), userId); // Example user ID
            RequestBody title = RequestBody.create(MediaType.parse("text/plain"), "App"); // Example title
            RequestBody description = RequestBody.create(MediaType.parse("text/plain"), messageContent);
            MultipartBody.Part imagePart = null;
            if (selectedImageUri != null) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                    byte[] bytes = getBytes(inputStream);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), bytes);
                    imagePart = MultipartBody.Part.createFormData("QueryImage", "image.jpg", requestFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            chatService.sendMessage(fkUserId, title, description, imagePart).enqueue(new Callback<ResponseChat>() {
                @Override
                public void onResponse(Call<ResponseChat> call, Response<ResponseChat> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String statusCode = response.body().getStatusCode();
                        String message = response.body().getMessage();

                        if ("200".equals(statusCode)) {
                            Toast.makeText(ChatActivity.this, message, Toast.LENGTH_SHORT).show();
                            editTextMessage.setText(""); // Clear the message box
                            selectedImageUri = null; // Clear the selected image URI
                            binding.imageView.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(ChatActivity.this, "Failed to send message", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ChatActivity.this, "Failed message", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseChat> call, Throwable t) {
                    Toast.makeText(ChatActivity.this, "Something went wrong: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Message cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

//private void sendMessage() {
//    String messageContent = editTextMessage.getText().toString();
//    if (!messageContent.isEmpty() || selectedImageUri != null) {
//        // Retrieve user ID from PreferencesManager
//        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
//        String userId = preferencesManager.getUserId();
//
//        // Check if userId is valid
//        if (userId == null || userId.isEmpty()) {
//            Toast.makeText(this, "User ID is invalid", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        RequestBody fkUserId = RequestBody.create(MediaType.parse("text/plain"), userId); // Example user ID
//        RequestBody title = RequestBody.create(MediaType.parse("text/plain"), "test"); // Example title
//        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), messageContent);
//
//        MultipartBody.Part imagePart = null;
//        if (selectedImageUri != null) {
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
//                byte[] bytes = getBytes(inputStream);
//                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), bytes);
//                imagePart = MultipartBody.Part.createFormData("QueryImage", "image.jpg", requestFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Make Retrofit call
//        chatService.sendMessage(fkUserId, title, description, imagePart).enqueue(new Callback<ResponseChat>() {
//            @Override
//            public void onResponse(Call<ResponseChat> call, Response<ResponseChat> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    String statusCode = response.body().getStatusCode();
//                    String message = response.body().getMessage();
//
//                    if ("200".equals(statusCode)) {
//                        Toast.makeText(ChatActivity.this, message, Toast.LENGTH_SHORT).show();
//                        editTextMessage.setText(""); // Clear the message box
//                        selectedImageUri = null; // Clear the selected image URI
//                        binding.imageView.setVisibility(View.GONE);
//                    } else {
//                        Toast.makeText(ChatActivity.this, "Failed to send message: " + message, Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    // Log error details
//                    try {
//                        String errorBody = response.errorBody().string();
//                        Log.e("SendMessageError", "Error body: " + errorBody);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Toast.makeText(ChatActivity.this, "Failed to send message", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseChat> call, Throwable t) {
//                Toast.makeText(ChatActivity.this, "Something went wrong: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.e("SendMessageFailure", "Error: " + t.getMessage(), t);
//            }
//        });
//    } else {
//        Toast.makeText(this, "Message cannot be empty", Toast.LENGTH_SHORT).show();
//    }
//}

    // Helper method to convert InputStream to byte array

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }


    private void fetchMessages() {
        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
        String userId = preferencesManager.getUserId();

        if (userId == null || userId.isEmpty()) {
            Toast.makeText(this, "User ID is invalid", Toast.LENGTH_SHORT).show();
            return;
        }
        FetchMessageRequest request = new FetchMessageRequest(userId);
        chatService.fetchMessages(request).enqueue(new Callback<getResponseChat>() {
            @Override
            public void onResponse(Call<getResponseChat> call, Response<getResponseChat> response) {
                if (response.isSuccessful() && response.body() != null) {
                    getResponseChat messagesResponse = response.body();
                    String statusCode = messagesResponse.getStatusCode();
                    String message = messagesResponse.getMessage();

                    if ("200".equals(statusCode)) {
                        List<QueryItem> queries = messagesResponse.getQueryList();
                        messageList.clear();
                        messageList.addAll(queries);
                        messageAdapter.notifyDataSetChanged();
                        recyclerView.scrollToPosition(messageList.size() - 1);
                    } else {
                        Toast.makeText(ChatActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChatActivity.this, "Failed to fetch messages", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<getResponseChat> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Something went wrong: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void startPeriodicFetching() {
        runnable = new Runnable() {
            @Override
            public void run() {
//                fetchMessages(); // Fetch messages every 3 seconds
                handler.postDelayed(this, 3000);
            }
        };
        handler.post(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor == null) {
            return null;
        }
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }



}