package com.hm.greencity.customermanagement.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hm.greencity.customermanagement.adapters.VideoAdapter;
import com.hm.greencity.customermanagement.databinding.FragmentVideoBinding;
import com.hm.greencity.customermanagement.models.Video.Video;
import java.util.ArrayList;
import java.util.List;


public class VideoFragment extends Fragment {

    private FragmentVideoBinding binding;
    private VideoAdapter adapter;
    private List<Video> videoList;

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
        videoList.add(new Video("Title 1", "https://www.youtube.com/watch?v=VIDEO_ID1", "https://img.youtube.com/vi/VIDEO_ID1/hqdefault.jpg"));
        videoList.add(new Video("Title 2", "https://www.youtube.com/watch?v=VIDEO_ID2", "https://img.youtube.com/vi/VIDEO_ID2/hqdefault.jpg"));
        videoList.add(new Video("Title 1", "https://www.youtube.com/watch?v=VIDEO_ID1", "https://img.youtube.com/vi/VIDEO_ID1/hqdefault.jpg"));
        videoList.add(new Video("Title 2", "https://www.youtube.com/watch?v=VIDEO_ID2", "https://img.youtube.com/vi/VIDEO_ID2/hqdefault.jpg"));

        int numberOfColumns = 1;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), numberOfColumns);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new VideoAdapter(getContext(), videoList);
        recyclerView.setAdapter(adapter);
    }
}
