package com.hm.greencity.customermanagement.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.adapters.AdapterAssociateBookingLst;
import com.hm.greencity.customermanagement.adapters.AdapterAssociateBusinessReport;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.common.Utils;
import com.hm.greencity.customermanagement.constants.BaseFragment;
import com.hm.greencity.customermanagement.models.AssociateBookingList.ResponseAssociateBookingList;
import com.hm.greencity.customermanagement.models.AssociateBusinessReport.ResponseAssociateBusinessReport;
import com.hm.greencity.customermanagement.models.ResponseList.LstBlock;
import com.hm.greencity.customermanagement.models.ResponseList.LstPhase;
import com.hm.greencity.customermanagement.models.ResponseList.LstSite;
import com.hm.greencity.customermanagement.models.ResponseList.ResponseSite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssociateBusinessReport extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    BottomSheetDialog searchDialog;
    @BindView(R.id.tvSearch)
    TextView tvSearch;

    private List<LstSite> lstsites;
    private List<LstPhase> lstSectors,sublstSectors;
    private List<LstBlock> lstBlocks,sublstBlocks;
    private String PK_SectorID, PK_SiteID, PK_BlockID;
    private PopupMenu sitemenu, sectorMenu, blockMenu;

//    chkisdownline
    CheckBox chkisdownline;

    boolean chk=false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);
        unbinder = ButterKnife.bind(this, view);


//        getData();
//        tvSearch.setVisibility(View.GONE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerview.setLayoutManager(layoutManager);
        getDataSearch("","","","","","","","","");
        return view;
    }



    private void searchhDialog() {
        searchDialog = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.search_section_adapter_business_report, null);
        searchDialog.setContentView(sheetView);
        TextView tv_start_date = sheetView.findViewById(R.id.tv_start_date);
        TextView tv_end_date = sheetView.findViewById(R.id.tv_end_date);

//        EditText et_booking_number = sheetView.findViewById(R.id.et_booking_number);
//        EditText et_associateId = sheetView.findViewById(R.id.et_associateId);
//        EditText et_customerID = sheetView.findViewById(R.id.et_customerID);
//        EditText et_plot_number = sheetView.findViewById(R.id.et_plot_number);

        Button btn_cancel = sheetView.findViewById(R.id.btn_cancel);
        Button btn_search = sheetView.findViewById(R.id.btn_search);
        chkisdownline=sheetView.findViewById(R.id.chkisdownline);

        if (chk)
        {
            chkisdownline.setChecked(true);
        }
        else
            chkisdownline.setChecked(false);

        chkisdownline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                chk=b;
            }
        });

//        getProductLst();
         //  getSector();
        //  getBlock();
        lstsites = new ArrayList<>();
        lstSectors = new ArrayList<>();
        sublstSectors = new ArrayList<>();
        lstBlocks = new ArrayList<>();
        sublstBlocks = new ArrayList<>();




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
                    tv_end_date.getText().toString().trim(),PK_SiteID,PK_SectorID,PK_BlockID,"","","","");
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

    public void getProductLst() {
        JsonObject jsonObject = new JsonObject();

        Call<ResponseSite> call = apiServices.sitelist(jsonObject);
        call.enqueue(new Callback<ResponseSite>() {
            @Override
            public void onResponse(Call<ResponseSite> call, Response<ResponseSite> response) {

                hideLoading();
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    lstsites = response.body().getLstSite();
                    lstSectors = response.body().getLstPhase();
                    lstBlocks = response.body().getLstBlock();
                    for (int i = 0; i < lstsites.size(); i++) {
                        sitemenu.getMenu().add(0, 0, i, lstsites.get(i).getSiteName());

                        //  PK_SiteID = response.body().getLstSite().get(i).getSiteID();


                    }



                } else
                    showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseSite> call, Throwable t) {
                hideLoading();
                // Toast.makeText(ReInvestment.this,t+ "", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void setSector(String siteID) {

        sublstSectors.clear();
        sectorMenu.getMenu().clear();

        for (int i = 0; i < lstSectors.size(); i++)
        {
            Log.e("TAGsadfasf", "setSector: "+lstSectors.get(i).toString() );
            if (lstSectors.get(i).getPhaseSiteID().equalsIgnoreCase(siteID))
            {

                sublstSectors.add(lstSectors.get(i));
            }
        }


        for (int i = 0; i < sublstSectors.size(); i++) {

            sectorMenu.getMenu().add(0, 0, i, sublstSectors.get(i).getPhaseName());

             // PK_SectorID = sublstSectors.get(i).getPhaseID();

            // Toast.makeText(context,selectSiteTypeid+ "", Toast.LENGTH_SHORT).show();
            //  getPackage();

        }


    }


    private void setBlock(String blockID) {

        sublstBlocks.clear();
        blockMenu.getMenu().clear();
        for (int i = 0; i < lstBlocks.size(); i++)
        {
            if (lstBlocks.get(i).getBlockSiteID().equalsIgnoreCase(blockID))
            {
                sublstBlocks.add(lstBlocks.get(i));
            }
        }

        for (int i = 0; i < sublstBlocks.size(); i++) {

            blockMenu.getMenu().add(0, 0, i, sublstBlocks.get(i).getBlockName());

             // PK_BlockID = sublstBlocks.get(i).getBlockID();

            // Toast.makeText(context,selectSiteTypeid+ "", Toast.LENGTH_SHORT).show();
            //  getPackage();

        }


    }

    @OnClick(R.id.tvSearch)
    public void onClick() {
        searchhDialog();
    }

    private void getDataSearch(String startdate,String enddate,String siteid,String sectorid,String blockid,String customerid,String bookingno,String associateid,String plotnumber)
    {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());

        object.addProperty("Leg","");
        object.addProperty("FromDate",startdate);
        object.addProperty("ToDate",enddate);
        if (chk)
        {
            object.addProperty("IsDownline","1");
        }
        else
        {
            object.addProperty("IsDownline",0);
        }



        Call<ResponseAssociateBusinessReport> associateBusinessReportCall = apiServices.AssociateBusinessReport(object);
        associateBusinessReportCall.enqueue(new Callback<ResponseAssociateBusinessReport>() {
            @Override
            public void onResponse(Call<ResponseAssociateBusinessReport> call, Response<ResponseAssociateBusinessReport> response) {
                hideLoading();
                if (response.body().getStatusCode().equalsIgnoreCase("200"))
                {
                   if (response.body().getBussinessLst().size()==0)
                   {
                       showMessage(response.body().getMessage());
                       recyclerview.setVisibility(View.GONE);
                   }
                    else {
                        recyclerview.setVisibility(View.VISIBLE);
                       AdapterAssociateBusinessReport adapterAssociateBusinessReport = new AdapterAssociateBusinessReport(response.body().getBussinessLst(), context);
                       recyclerview.setAdapter(adapterAssociateBusinessReport);
                   }

                }
            }

            @Override
            public void onFailure(Call<ResponseAssociateBusinessReport> call, Throwable t) {
                hideLoading();
            }
        });

    }
}
