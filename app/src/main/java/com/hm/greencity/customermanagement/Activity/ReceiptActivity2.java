package com.hm.greencity.customermanagement.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.annotation.SuppressLint;
import android.os.Bundle;
import com.hm.greencity.customermanagement.adapters.ReceiptAdapter;
import com.hm.greencity.customermanagement.databinding.ActivityReceipt2Binding;
import com.hm.greencity.customermanagement.models.ReceiptModel;
import java.util.ArrayList;


public class ReceiptActivity2 extends AppCompatActivity {
    ActivityReceipt2Binding binding;
    private ReceiptAdapter adapter;
    private ArrayList<ReceiptModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityReceipt2Binding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        initView();
        onClickListener();
    }

    @SuppressLint("SuspiciousIndentation")
    private void initView() {
        binding.receiptRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        data = new ArrayList<>();
        data.add(new ReceiptModel("HMCRM115011782", "HMCRM115011782", "DHM000029", "03/10/2024", "10000.00", "HMBK69542349", "Kohinoor City, Onex home, D, 22", "UPI"));
        data.add(new ReceiptModel("HMCRM115011782", "HMCRM115011782", "DHM000029", "03/10/2024", "10000.00", "HMBK69542349", "Kohinoor City, Onex home, D, 22", "UPI"));
        data.add(new ReceiptModel("HMCRM115011782", "HMCRM115011782", "DHM000029", "03/10/2024", "10000.00", "HMBK69542349", "Kohinoor City, Onex home, D, 22", "UPI"));
        adapter = new ReceiptAdapter(data, this);
        binding.receiptRecyclerview.setAdapter(adapter);
    }

    private void onClickListener() {

    }
}
