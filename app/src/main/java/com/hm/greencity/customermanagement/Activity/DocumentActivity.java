package com.hm.greencity.customermanagement.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.adapters.PdfAdapter;
import com.hm.greencity.customermanagement.databinding.ActivityDocumentBinding;
import com.hm.greencity.customermanagement.models.Gallery.Lstgallery;
import com.hm.greencity.customermanagement.models.Gallery.ResGallery;
import com.hm.greencity.customermanagement.retrofit.ApiServices;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentActivity extends AppCompatActivity {
   ActivityDocumentBinding binding;
    private RecyclerView recyclerview;
    private PdfAdapter pdfAdapter;
    private List<Lstgallery> pdfList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityDocumentBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        recyclerview = binding.recyclerview;

        initview();
        onclicklistener();
    }

    private void onclicklistener() {
        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeTestActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initview() {
        int numberOfColumns = 3;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), numberOfColumns);
        binding.recyclerview.setLayoutManager(gridLayoutManager);

        pdfList = new ArrayList<>();
        pdfAdapter = new PdfAdapter(pdfList, getApplicationContext());
        binding.recyclerview.setAdapter(pdfAdapter);
        fetchGalleryData();
    }

    private void fetchGalleryData() {
        ApiServices apiService = RetrofitClient.getClient().create(ApiServices.class);
        Call<ResGallery> call = apiService.getGallery();
        call.enqueue(new Callback<ResGallery>() {
            @Override
            public void onResponse(Call<ResGallery> call, Response<ResGallery> response) {
                if (response.isSuccessful()) {
                    ResGallery resGallery = response.body();
                    if (resGallery != null && resGallery.getLstgallery() != null) {
                        List<Lstgallery> filteredList = new ArrayList<>();
                        for (Lstgallery item : resGallery.getLstgallery()) {
                            if ("Document".equals(item.getMediaType())) {
                                filteredList.add(item);
                            }
                        }
                        pdfList.clear();
                        pdfList.addAll(filteredList);
                        pdfAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(DocumentActivity.this, "Failed to fetch gallery data", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResGallery> call, Throwable t) {
                Toast.makeText(DocumentActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
