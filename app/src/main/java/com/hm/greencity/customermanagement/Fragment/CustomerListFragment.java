package com.hm.greencity.customermanagement.Fragment;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.Activity.AssociateContaner;
import com.hm.greencity.customermanagement.Activity.AssosiateDashboard;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.adapters.AdapterCustomerList;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.common.Utils;
import com.hm.greencity.customermanagement.constants.BaseFragment;
import com.hm.greencity.customermanagement.models.CustomerList.ResponseCustomerList;
import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CustomerListFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    BottomSheetDialog searchDialog;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.rl_clickmenu)
    RelativeLayout rl_clickmenu;

    @BindView(R.id.backarrow)
    ImageView tvbackarrow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerview.setLayoutManager(layoutManager);

        rl_clickmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToActivityWithFinish(AssociateContaner.class,null);
                ((ViewGroup)view.getParent()).removeView(view);
            }
        });

        tvbackarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssociateContaner.currentFragment = new AssosiateDashboard();
                FragmentTransaction fr4 = getFragmentManager().beginTransaction();
                fr4.replace(R.id.frame, new AssosiateDashboard()).addToBackStack(null);
                fr4.commit();
            }
        });


        return view;
    }

    public void getData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("CustomerID", PreferencesManager.getInstance(context).getCustomerID());
        object.addProperty("AssociateID", PreferencesManager.getInstance(context).getCustomerID());
        Call<ResponseCustomerList> call = apiServices.AssociateCustomerList(object);
        call.enqueue(new Callback<ResponseCustomerList>() {
            @Override
            public void onResponse(Call<ResponseCustomerList> call, Response<ResponseCustomerList> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        hideLoading();
                        AdapterCustomerList adapter = new AdapterCustomerList(response.body().getCustomerLst(), getContext());
                        recyclerview.setAdapter(adapter);


                    } else showMessage("Record Not Found!");
                    hideLoading();
                } else showMessage(response.body().getMessage());
                hideLoading();

            }

            @Override
            public void onFailure(Call<ResponseCustomerList> call, Throwable t) {
                hideLoading();
            }

        });
    }

    public void getDataSearch(String startDate, String endDate, String CustomerLoginId, String CustomerName) {
        showLoading();
        JsonObject object = new JsonObject();
          object.addProperty("AssociateID", PreferencesManager.getInstance(context).getCustomerID());
//        object.addProperty("AssociateID", "24");
        object.addProperty("FromDate", startDate);
        object.addProperty("ToDate", endDate);
        object.addProperty("CustomerLoginID", CustomerLoginId);
        object.addProperty("CustomerName", CustomerName);
        Call<ResponseCustomerList> call = apiServices.AssociateCustomerList(object);
        call.enqueue(new Callback<ResponseCustomerList>() {
            @Override
            public void onResponse(Call<ResponseCustomerList> call, Response<ResponseCustomerList> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        hideLoading();
                        AdapterCustomerList adapter = new AdapterCustomerList(response.body().getCustomerLst(), getContext());
                        recyclerview.setAdapter(adapter);


                    } else showMessage("Record Not Found!");
                    hideLoading();
                } else showMessage(response.body().getMessage());
                hideLoading();

            }

            @Override
            public void onFailure(Call<ResponseCustomerList> call, Throwable t) {
                hideLoading();
            }

        });
    }

    private void searchhDialog() {
        searchDialog = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_customerlist, null);
        searchDialog.setContentView(sheetView);
        TextView tv_start_date = sheetView.findViewById(R.id.tv_start_date);
        TextView tv_end_date = sheetView.findViewById(R.id.tv_end_date);
        EditText CustomerLoginID = sheetView.findViewById(R.id.tvCustomerLoginId);
        EditText CustomerName = sheetView.findViewById(R.id.tv_customer_name);
        Button btn_cancel = sheetView.findViewById(R.id.btn_cancel);
        Button btn_search = sheetView.findViewById(R.id.btn_search);
        btn_cancel.setOnClickListener(v -> {
            searchDialog.dismiss();
        });

        tv_start_date.setOnClickListener(v -> {
            datePicker(tv_start_date);
            tv_end_date.setText("");
        });

        tv_end_date.setOnClickListener(v -> {
            if (tv_start_date.getText().toString().equalsIgnoreCase(""))
                showMessage("Select Start Date");
            else
                datePicker(tv_end_date);
        });


        btn_search.setOnClickListener(v -> {
            searchDialog.dismiss();
            getDataSearch(tv_start_date.getText().toString().trim(),
                    tv_end_date.getText().toString().trim(), CustomerLoginID.getText().toString().trim(), CustomerName.getText().toString().trim());
        });

        searchDialog.setCancelable(false);
        searchDialog.setCanceledOnTouchOutside(false);
        searchDialog.show();
    }

    private void datePicker(final TextView et) {
        Calendar cal = Calendar.getInstance();
        int mYear, mMonth, mDay;

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year, monthOfYear, dayOfMonth) -> {
            et.setText(Utils.changeDateFormat(dayOfMonth, monthOfYear, year));
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        datePickerDialog.show();
    }

    @OnClick(R.id.tvSearch)
    public void onClick() {
        searchhDialog();
    }
}
