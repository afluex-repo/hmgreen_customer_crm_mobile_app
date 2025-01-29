package com.hm.greencity.customermanagement.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.adapters.layoutAdapter;
import com.hm.greencity.customermanagement.databinding.ActivityLayoutBinding;
import com.hm.greencity.customermanagement.models.LayoutModel.LstSiteLayout;
import com.hm.greencity.customermanagement.models.LayoutModel.ResLayoutModel;
import com.hm.greencity.customermanagement.retrofit.ApiServices;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class LayoutActivity extends AppCompatActivity {
    ActivityLayoutBinding binding;
    private RecyclerView layoutrecyclerview;
    private layoutAdapter layoutAdapter;
    private List<LstSiteLayout> lstSiteLayouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onclicklistener();


        layoutrecyclerview = binding.layoutrecyclerview;
        layoutrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        lstSiteLayouts = new ArrayList<>();
        layoutAdapter = new layoutAdapter(lstSiteLayouts, this);
        layoutrecyclerview.setAdapter(layoutAdapter);

        fetchGalleryData();
    }

    private void onclicklistener() {
        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LayoutActivity.this,AssociateContaner.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private void fetchGalleryData() {
        ApiServices apiService = RetrofitClient.getClient().create(ApiServices.class);
        Call<ResLayoutModel> call = apiService.getLayout();
        call.enqueue(new Callback<ResLayoutModel>() {
            @Override
            public void onResponse(Call<ResLayoutModel> call, Response<ResLayoutModel> response) {
                if (response.isSuccessful()) {
                    ResLayoutModel resLayoutModel = response.body();
                    if (resLayoutModel != null && resLayoutModel.getLstSiteLayout() != null) {
                        List<LstSiteLayout> filteredList = new ArrayList<>();
                        for (LstSiteLayout item : resLayoutModel.getLstSiteLayout()) {
                            if (item != null) {
                                filteredList.add(item);
                            }
                        }
                        lstSiteLayouts.clear();
                        lstSiteLayouts.addAll(filteredList);
                        layoutAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(LayoutActivity.this, "Failed to fetch gallery data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResLayoutModel> call, Throwable t) {
                Toast.makeText(LayoutActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
