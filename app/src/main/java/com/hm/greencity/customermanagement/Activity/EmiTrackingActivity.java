package com.hm.greencity.customermanagement.Activity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.hm.greencity.customermanagement.Network.ChatService;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.adapters.EmiAdapter;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.databinding.ActivityEmiTrackingBinding;
import com.hm.greencity.customermanagement.models.EmiTracking.LstEMIReport;
import com.hm.greencity.customermanagement.models.EmiTracking.ReqEmi;
import com.hm.greencity.customermanagement.models.EmiTracking.ResEmi;
import com.hm.greencity.customermanagement.models.Notes.GetNote.LstNotepad;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class EmiTrackingActivity extends BaseActivity {
ActivityEmiTrackingBinding binding;
    private ChatService chatService;
    private List<LstEMIReport> messageList = new ArrayList<>();
    private EmiAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityEmiTrackingBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initview();
        onclicklistener();
    }

    @Override
    public void onNoteDelete(LstNotepad note) {

    }

    private void initview() {
        chatService = RetrofitClient.getClient().create(ChatService.class);
        messageAdapter = new EmiAdapter(messageList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        binding.emitrackingrecyclerview.setLayoutManager(layoutManager);
        binding.emitrackingrecyclerview.setAdapter(messageAdapter);
        emiList();
    }

    private void onclicklistener() {
        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EmiTrackingActivity.this,HomeTestActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }


    private void emiList() {
        String savedBookingNumber = PreferencesManager.getInstance(context).getBookingNumber();
        if (savedBookingNumber == null || savedBookingNumber.isEmpty()) {
            Toast.makeText(this, "Booking No is invalid", Toast.LENGTH_SHORT).show();
            Log.d("BookingNumber", "Booking Number is invalid or empty.");
            return;
        }
        Log.d("BookingNumber", "Requesting EMI for Booking Number: " + savedBookingNumber);
        ReqEmi request = new ReqEmi(savedBookingNumber);
        chatService.emitracking(request).enqueue(new Callback<ResEmi>() {
            @Override
            public void onResponse(Call<ResEmi> call, Response<ResEmi> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResEmi messagesResponse = response.body();
                    String statusCode = messagesResponse.getStatus();
                    String message = messagesResponse.getMessage();
                    if ("200".equals(statusCode)) {
                        List<LstEMIReport> queries = messagesResponse.getLstEMIReports();
                        messageList.clear();
                        messageList.addAll(queries);
                        messageAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(EmiTrackingActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EmiTrackingActivity.this, "Failed to fetch EMI data. Response was unsuccessful.", Toast.LENGTH_SHORT).show();
                    Log.d("EMI_Response", "Failed: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<ResEmi> call, Throwable t) {
                Log.e("EMI_Error", "Error occurred while making the EMI request: " + t.getMessage(), t);
                Toast.makeText(EmiTrackingActivity.this, "Something went wrong: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EmiTrackingActivity.this, HomeTestActivity.class);
        startActivity(intent);
        finish();
    }

}