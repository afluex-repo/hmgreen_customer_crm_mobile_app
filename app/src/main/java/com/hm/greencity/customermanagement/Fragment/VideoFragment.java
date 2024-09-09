package com.hm.greencity.customermanagement.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.adapters.VideoAdapter;
import com.hm.greencity.customermanagement.databinding.FragmentVideoBinding;
import com.hm.greencity.customermanagement.models.Gallery.Lstgallery;
import com.hm.greencity.customermanagement.models.Gallery.ResGallery;
import com.hm.greencity.customermanagement.retrofit.ApiServices;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VideoFragment extends Fragment {

    private FragmentVideoBinding binding;
    private VideoAdapter adapter;
    private List<Lstgallery> videoList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVideoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = binding.recyclerView;

        videoList = new ArrayList<>();

        int numberOfColumns = 1;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), numberOfColumns);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new VideoAdapter(videoList, getContext());
        recyclerView.setAdapter(adapter);

        // Fetch gallery data
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
                            if ("VideoLinks".equals(item.getMediaType())) {
                                filteredList.add(item);
                            }
                        }
                        videoList.clear();
                        videoList.addAll(filteredList);
                        adapter.notifyDataSetChanged();
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
