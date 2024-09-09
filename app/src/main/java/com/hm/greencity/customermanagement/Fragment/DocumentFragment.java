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
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.adapters.PdfAdapter;
import com.hm.greencity.customermanagement.models.Gallery.Lstgallery;
import com.hm.greencity.customermanagement.models.Gallery.ResGallery;
import com.hm.greencity.customermanagement.retrofit.ApiServices;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentFragment extends Fragment {

    private RecyclerView recyclerView;
    private PdfAdapter pdfAdapter;
    private List<Lstgallery> pdfList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_document, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        int numberOfColumns = 3;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), numberOfColumns);
        recyclerView.setLayoutManager(gridLayoutManager);

        pdfList = new ArrayList<>();
        pdfAdapter = new PdfAdapter(pdfList, getContext());
        recyclerView.setAdapter(pdfAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
