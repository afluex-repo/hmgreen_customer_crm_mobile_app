package com.hm.greencity.customermanagement.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.hm.greencity.customermanagement.Network.ChatService;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.adapters.SiteVisitAdapter;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.databinding.ActivitySiteVisitorListBinding;
import com.hm.greencity.customermanagement.models.GetSite.LstVisitor;
import com.hm.greencity.customermanagement.models.GetSite.ReqGetSite;
import com.hm.greencity.customermanagement.models.GetSite.ResGetSite;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SiteVisitorListActivity extends AppCompatActivity {
    ActivitySiteVisitorListBinding binding;
    private ChatService chatService;
    private List<LstVisitor> messageList = new ArrayList<>();
    private SiteVisitAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySiteVisitorListBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initview();
        onclicklistener();
        fetchvisitor();

    }

    private void onclicklistener() {
        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SiteVisitorListActivity.this,SiteVisitActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void initview() {
        chatService = RetrofitClient.getClient().create(ChatService.class);
        messageAdapter = new SiteVisitAdapter(messageList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        binding.visitorlistrecyclerview.setLayoutManager(layoutManager);
        binding.visitorlistrecyclerview.setAdapter(messageAdapter);
    }

    private void fetchvisitor() {
        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
        String userId = preferencesManager.getUserId();
        if (userId == null || userId.isEmpty()) {
            Toast.makeText(this, "User ID is invalid", Toast.LENGTH_SHORT).show();
            return;
        }
        ReqGetSite request = new ReqGetSite(userId);
        chatService.getvisitorlist(request).enqueue(new Callback<ResGetSite>() {
            @Override
            public void onResponse(Call<ResGetSite> call, Response<ResGetSite> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResGetSite messagesResponse = response.body();
                    String statusCode = messagesResponse.getStatus();
                    String message = messagesResponse.getMessage();
                    if ("200".equals(statusCode)) {
                        List<LstVisitor> queries = messagesResponse.getLstVisitor();
                        messageList.clear();
                        messageList.addAll(queries);
                        messageAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(SiteVisitorListActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SiteVisitorListActivity.this, "Failed to fetch messages", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResGetSite> call, Throwable t) {
                Toast.makeText(SiteVisitorListActivity.this, "Something went wrong: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
