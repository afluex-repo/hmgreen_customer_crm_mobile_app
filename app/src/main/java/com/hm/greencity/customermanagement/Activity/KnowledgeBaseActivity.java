package com.hm.greencity.customermanagement.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.hm.greencity.customermanagement.Fragment.CompanyProfileFragment;
import com.hm.greencity.customermanagement.adapters.ViewPagerAdapter;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.databinding.ActivityKnowledgeBaseBinding;



public class KnowledgeBaseActivity extends AppCompatActivity {
 ActivityKnowledgeBaseBinding binding;
    private ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityKnowledgeBaseBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initView();
        onclicklistener();
        setupViewPager();

    }

    private void onclicklistener() {
        binding.backarrow.setOnClickListener(v -> {
            if (!PreferencesManager.getInstance(getApplicationContext()).getUserType().equalsIgnoreCase("")) {
                Intent I;
                if (PreferencesManager.getInstance(getApplicationContext()).getUserType().equalsIgnoreCase("Trad Associate")) {
                    I = new Intent(KnowledgeBaseActivity.this, AssociateContaner.class);
                } else {
                    I = new Intent(KnowledgeBaseActivity.this, HomeTestActivity.class);
                }
                startActivity(I);
                finish();
            }
        });
    }

    private void initView() {

    }

    private void setupViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.add(new CompanyProfileFragment(), "About Company");
      //  viewPagerAdapter.add(new VideoFragment(), "Videos");
       // viewPagerAdapter.add(new KeyTrabsportFragment(), "Key Transport");
        binding.pageViewer.setAdapter(viewPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.pageViewer);


    }

}