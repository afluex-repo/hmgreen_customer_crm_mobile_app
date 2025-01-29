package com.hm.greencity.customermanagement.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.adapters.BroucherAdapter;
import com.hm.greencity.customermanagement.adapters.layoutAdapter;
import com.hm.greencity.customermanagement.databinding.ActivityBrouchureBinding;
import com.hm.greencity.customermanagement.models.BroucherModel.Brochurelst;
import com.hm.greencity.customermanagement.models.BroucherModel.ResBroucherModel;
import com.hm.greencity.customermanagement.models.LayoutModel.LstSiteLayout;
import com.hm.greencity.customermanagement.models.LayoutModel.ResLayoutModel;
import com.hm.greencity.customermanagement.retrofit.ApiServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BrouchureActivity extends AppCompatActivity {
ActivityBrouchureBinding binding;
    private RecyclerView layoutrecyclerview;
    private BroucherAdapter broucherAdapter;
    private List<Brochurelst> brochurelsts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding= ActivityBrouchureBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initview();
        onclicklistener();

    }

    private void initview() {
        layoutrecyclerview = binding.layoutrecyclerview;
        layoutrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        brochurelsts = new ArrayList<>();
        broucherAdapter = new BroucherAdapter(brochurelsts, this);
        layoutrecyclerview.setAdapter(broucherAdapter);


        broucherlist();
    }

    private void onclicklistener() {

        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BrouchureActivity.this,AssociateContaner.class);
                startActivity(intent);
                finish();

            }
        });

    }



    private void broucherlist() {
        ApiServices apiService = RetrofitClient.getClient().create(ApiServices.class);
        Call<ResBroucherModel> call = apiService.getBroucher();
        call.enqueue(new Callback<ResBroucherModel>() {
            @Override
            public void onResponse(Call<ResBroucherModel> call, Response<ResBroucherModel> response) {
                if (response.isSuccessful()) {
                    ResBroucherModel resLayoutModel = response.body();
                    if (resLayoutModel != null && resLayoutModel.getBrochurelst() != null) {
                        List<Brochurelst> filteredList = new ArrayList<>();
                        for (Brochurelst item : resLayoutModel.getBrochurelst()) {
                            if (item != null) {
                                filteredList.add(item);
                            }
                        }
                        brochurelsts.clear();
                        brochurelsts.addAll(filteredList);
                        broucherAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(BrouchureActivity.this, "Failed to fetch gallery data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResBroucherModel> call, Throwable t) {
                Toast.makeText(BrouchureActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}