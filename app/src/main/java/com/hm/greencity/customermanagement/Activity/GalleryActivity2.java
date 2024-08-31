package com.hm.greencity.customermanagement.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.hm.greencity.customermanagement.Fragment.DocumentFragment;
import com.hm.greencity.customermanagement.Fragment.PhototFragment;
import com.hm.greencity.customermanagement.Fragment.VideoFragment;
import com.hm.greencity.customermanagement.adapters.ViewPagerAdapter;
import com.hm.greencity.customermanagement.databinding.ActivityGallery2Binding;


public class GalleryActivity2 extends AppCompatActivity {

    private ActivityGallery2Binding binding;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGallery2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        setupViewPager();
    }

    private void initView() {

    }

    private void setupViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.add(new PhototFragment(), "Photos");
        viewPagerAdapter.add(new VideoFragment(), "Videos");
        viewPagerAdapter.add(new DocumentFragment(), "Documents");

        binding.pageViewer.setAdapter(viewPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.pageViewer);
    }
}
