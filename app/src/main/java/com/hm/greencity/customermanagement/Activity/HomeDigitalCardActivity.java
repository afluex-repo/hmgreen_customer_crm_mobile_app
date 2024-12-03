package com.hm.greencity.customermanagement.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.hm.greencity.customermanagement.databinding.ActivityHomeDigitalCardBinding;


public class HomeDigitalCardActivity extends AppCompatActivity {
ActivityHomeDigitalCardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityHomeDigitalCardBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initview();
        onclicklistener();

    }

    private void onclicklistener() {
        binding.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeDigitalCardActivity.this,DigitalBusinessCardActivity.class));
            }
        });

    }

    private void initview() {
    }


}