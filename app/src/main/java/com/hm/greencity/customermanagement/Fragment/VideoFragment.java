package com.hm.greencity.customermanagement.Fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hm.greencity.customermanagement.databinding.FragmentVideoBinding;


public class VideoFragment extends Fragment {
  FragmentVideoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVideoBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }
}