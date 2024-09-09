package com.hm.greencity.customermanagement.Fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.adapters.GalleryAdapter;
import com.hm.greencity.customermanagement.models.Gallery.Lstgallery;
import com.hm.greencity.customermanagement.models.Gallery.ResGallery;
import com.hm.greencity.customermanagement.databinding.FragmentPhototBinding;
import com.hm.greencity.customermanagement.retrofit.ApiServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class PhototFragment extends Fragment {
    private FragmentPhototBinding binding;
    private List<Lstgallery> galleryList;
    private GalleryAdapter galleryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhototBinding.inflate(inflater, container, false);
        initView();
        fetchGalleryData();
        return binding.getRoot();

    }

    private void initView() {
        galleryList = new ArrayList<>();
        galleryAdapter = new GalleryAdapter(galleryList, requireContext());

        int numberOfColumns = 3;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), numberOfColumns);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.recyclerView.setAdapter(galleryAdapter);
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
                            if ("Image".equals(item.getMediaType())) {
                                filteredList.add(item);
                            }
                        }

                        // Use a final copy of the list to update the adapter
                        List<Lstgallery> finalFilteredList = new ArrayList<>(filteredList);
                        galleryList.clear();
                        galleryList.addAll(finalFilteredList);
                        galleryAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getContext(), "Failed to fetch gallery data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResGallery> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
