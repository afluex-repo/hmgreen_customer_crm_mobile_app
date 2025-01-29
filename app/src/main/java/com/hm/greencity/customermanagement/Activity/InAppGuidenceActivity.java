package com.hm.greencity.customermanagement.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.databinding.ActivityInAppGuidenceBinding;


public class InAppGuidenceActivity extends AppCompatActivity {
ActivityInAppGuidenceBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityInAppGuidenceBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        onclicklistener();
        initview();
    }

    private void onclicklistener() {
        binding.backarrow.setOnClickListener(v -> {
            if (!PreferencesManager.getInstance(getApplicationContext()).getUserType().equalsIgnoreCase("")) {
                Intent I;
                if (PreferencesManager.getInstance(getApplicationContext()).getUserType().equalsIgnoreCase("Trad Associate")) {
                    I = new Intent(InAppGuidenceActivity.this, AssociateContaner.class);
                } else {
                    I = new Intent(InAppGuidenceActivity.this, HomeTestActivity.class);
                }
                startActivity(I);
                finish();
            }
        });
    }

    private void initview() {

    }

}