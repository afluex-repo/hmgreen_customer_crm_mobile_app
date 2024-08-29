package com.hm.greencity.customermanagement.Fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.adapters.GalleryAdapter;
import com.hm.greencity.customermanagement.models.Gallery.PhototsModel;
import com.hm.greencity.customermanagement.databinding.FragmentPhototBinding;
import java.util.ArrayList;
import java.util.List;


public class PhototFragment extends Fragment {
    private FragmentPhototBinding binding;
    private List<PhototsModel> phototsModelList;
    private GalleryAdapter galleryAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhototBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {

        phototsModelList = new ArrayList<>();
        phototsModelList.add(new PhototsModel(R.drawable.logoh));
        phototsModelList.add(new PhototsModel(R.drawable.logoh));
        phototsModelList.add(new PhototsModel(R.drawable.logoh));
        phototsModelList.add(new PhototsModel(R.drawable.logoh));
        phototsModelList.add(new PhototsModel(R.drawable.logoh));
        phototsModelList.add(new PhototsModel(R.drawable.logoh));
        phototsModelList.add(new PhototsModel(R.drawable.logoh));
        phototsModelList.add(new PhototsModel(R.drawable.logoh));
        phototsModelList.add(new PhototsModel(R.drawable.logoh));
        phototsModelList.add(new PhototsModel(R.drawable.logoh));
        phototsModelList.add(new PhototsModel(R.drawable.logoh));
        phototsModelList.add(new PhototsModel(R.drawable.logoh));
        phototsModelList.add(new PhototsModel(R.drawable.logoh));
        phototsModelList.add(new PhototsModel(R.drawable.logoh));
        phototsModelList.add(new PhototsModel(R.drawable.logoh));


        galleryAdapter = new GalleryAdapter(phototsModelList, requireContext());


        int numberOfColumns = 3;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), numberOfColumns);
        binding.recyclerView.setLayoutManager(gridLayoutManager);

        binding.recyclerView.setAdapter(galleryAdapter);


    }
}
