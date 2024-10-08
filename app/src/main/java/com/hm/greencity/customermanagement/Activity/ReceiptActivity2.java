package com.hm.greencity.customermanagement.Activity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.adapters.ReceiptAdapter;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.common.Utils;
import com.hm.greencity.customermanagement.databinding.ActivityReceipt2Binding;
import com.hm.greencity.customermanagement.models.Notes.GetNote.LstNotepad;
import com.hm.greencity.customermanagement.models.PlotAllotmentReport.LstPlotReport;
import com.hm.greencity.customermanagement.models.PlotAllotmentReport.ResponseAllotmentReport;
import com.hm.greencity.customermanagement.retrofit.ApiServices;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ReceiptActivity2 extends BaseActivity {
    ActivityReceipt2Binding binding;
    private ReceiptAdapter adapter;
    private List<LstPlotReport> lstvisitors;
    BottomSheetDialog searchDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityReceipt2Binding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        initView();
        onClickListener();
        getData();
    }

    @Override
    public void onNoteDelete(LstNotepad note) {

    }

    private void initView() {
        lstvisitors = new ArrayList<>();
        binding.receiptRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReceiptAdapter(lstvisitors, this);
        binding.receiptRecyclerview.setAdapter(adapter);
    }

    private void onClickListener() {
        binding.search.setOnClickListener(v -> searchDialog());
    }

    public void getData() {
        ApiServices apiService = RetrofitClient.getClient().create(ApiServices.class);
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("CustomerLoginId", PreferencesManager.getInstance(getApplicationContext()).getLoginId());

        Call<ResponseAllotmentReport> call = apiService.getAllotmentReport(object);
        call.enqueue(new Callback<ResponseAllotmentReport>() {
            @Override
            public void onResponse(Call<ResponseAllotmentReport> call, Response<ResponseAllotmentReport> response) {
                hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    ResponseAllotmentReport resVisitorList = response.body();
                    Log.d("data response:", "" + response.body());

                    if (resVisitorList.getLstPlotReport() != null) {
                        Log.d("ReceiptActivity2", "Response data size: " + resVisitorList.getLstPlotReport().size());
                        lstvisitors.clear();
                        lstvisitors.addAll(resVisitorList.getLstPlotReport());
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.e(TAG, "Response data is null");
                    }
                } else {
                    Log.e(TAG, "Response Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseAllotmentReport> call, Throwable t) {
                hideLoading();
                Log.e(TAG, "Network Failure: " + t.getMessage());
            }
        });
    }
    private void searchDialog() {
        // Use the activity context
        searchDialog = new BottomSheetDialog(ReceiptActivity2.this);

        // Inflate the layout
        View sheetView = this.getLayoutInflater().inflate(R.layout.filter_layout, null);
        searchDialog.setContentView(sheetView);

        // Find views
        TextView tv_start_date = sheetView.findViewById(R.id.tv_start_date);
        TextView tv_end_date = sheetView.findViewById(R.id.tv_end_date);
        EditText et_payout_number = sheetView.findViewById(R.id.et_payout_number);
        Button btn_cancel = sheetView.findViewById(R.id.btn_cancel);
        Button btn_search = sheetView.findViewById(R.id.btn_search);

        // Set click listeners
        btn_cancel.setOnClickListener(v -> searchDialog.dismiss());

        tv_start_date.setOnClickListener(v -> {
            datePicker(tv_start_date);
            tv_end_date.setText(""); // Clear end date when start date is selected
        });

        tv_end_date.setOnClickListener(v -> {
            if (tv_start_date.getText().toString().trim().isEmpty()) {
                showMessage("Select Start Date");
            } else {
                datePicker(tv_end_date);
            }
        });

        btn_search.setOnClickListener(v -> {
            searchDialog.dismiss();
            getDataSearch(tv_start_date.getText().toString().trim(),
                    tv_end_date.getText().toString().trim());
        });

        // Configure dialog behavior
        searchDialog.setCancelable(false);
        searchDialog.setCanceledOnTouchOutside(false);
        searchDialog.show();
    }

    private void datePicker(final TextView et) {
        Calendar cal = Calendar.getInstance();
        int mYear = cal.get(Calendar.YEAR);
        int mMonth = cal.get(Calendar.MONTH);
        int mDay = cal.get(Calendar.DAY_OF_MONTH);

        // Use the activity context
        DatePickerDialog datePickerDialog = new DatePickerDialog(ReceiptActivity2.this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    // Adjust monthOfYear to be 1-based for display
                    et.setText(Utils.changeDateFormat(dayOfMonth, monthOfYear + 1, year));
                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        datePickerDialog.show();
    }



    public void getDataSearch(String startDate, String endDate) {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("CustomerLoginId", PreferencesManager.getInstance(getApplicationContext()).getLoginId());
        object.addProperty("FromDate", startDate);
        object.addProperty("ToDate", endDate);

       // LoggerUtil.logItem(object);
        Log.d("req data",""+object);
        Call<ResponseAllotmentReport> call = apiServices.getAllotmentReport(object);
        call.enqueue(new Callback<ResponseAllotmentReport>() {
            @Override
            public void onResponse(Call<ResponseAllotmentReport> call, Response<ResponseAllotmentReport> response) {
                hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                  //  LoggerUtil.logItem(response.body());
                    lstvisitors.clear();
                    if (response.body().getLstPlotReport() != null) {
                        lstvisitors.addAll(response.body().getLstPlotReport());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "Response Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseAllotmentReport> call, Throwable t) {
                hideLoading();
                Log.e(TAG, "Network Failure: " + t.getMessage());
            }
        });
    }

}
