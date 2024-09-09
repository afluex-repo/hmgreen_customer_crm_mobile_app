package com.hm.greencity.customermanagement.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.hm.greencity.customermanagement.databinding.ActivityFinalBusinessCardBinding;


public class FinalBusinessCardActivity extends AppCompatActivity {
ActivityFinalBusinessCardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityFinalBusinessCardBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initview();
        onclicklistener();


    }

    private void onclicklistener() {
        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(FinalBusinessCardActivity.this,DigitalBusinessCardActivity.class));
                Intent intent = new Intent(FinalBusinessCardActivity.this,DigitalBusinessCardActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initview() {

    }

}