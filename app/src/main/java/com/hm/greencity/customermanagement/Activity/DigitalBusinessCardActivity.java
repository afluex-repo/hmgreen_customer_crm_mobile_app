package com.hm.greencity.customermanagement.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.hm.greencity.customermanagement.databinding.ActivityDigitalBusinessCardBinding;


public class DigitalBusinessCardActivity extends AppCompatActivity {
ActivityDigitalBusinessCardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding= ActivityDigitalBusinessCardBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initview();
        onclicklistener();

    }

    private void onclicklistener() {
 binding.submitbutton.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         startActivity(new Intent(DigitalBusinessCardActivity.this,FinalBusinessCardActivity.class));
     }
 });
    }

    private void initview() {

    }

}