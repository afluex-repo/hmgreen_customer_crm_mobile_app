package com.hm.greencity.customermanagement.NotePad;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.databinding.ActivityShowNotePadBinding;

public class ShowNotePadActivity extends AppCompatActivity {
ActivityShowNotePadBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityShowNotePadBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }
}