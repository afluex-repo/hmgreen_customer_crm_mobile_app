package com.hm.greencity.customermanagement.Fragment;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
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
import com.hm.greencity.customermanagement.adapters.AdapterAssociateBookingLst;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.common.Utils;
import com.hm.greencity.customermanagement.constants.BaseFragment;
import com.hm.greencity.customermanagement.models.AssociateBookingList.ResponseAssociateBookingList;
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


public class AssociateBookingList  extends BaseFragment {
    Unbinder unbinder;
    @BindView(com.hm.greencity.customermanagement.R.id.recyclerview)
    RecyclerView recyclerview;
    BottomSheetDialog searchDialog;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.textContainer1)
    LinearLayout textContainer1;
    @BindView(R.id.total_number_of_plot)
    TextView total_number_of_plot;
    @BindView(R.id.rl_clickmenu)
    RelativeLayout rl_clickmenu;
    @BindView(R.id.backarrow)
    ImageView tvbackarrow;


    private TextView tv_select_site,tv_sector,select_block;
    private List<LstSite> lstsites;
    private List<LstPhase> lstSectors,sublstSectors;
    private List<LstBlock> lstBlocks,sublstBlocks;
    private String PK_SectorID, PK_SiteID, PK_BlockID;
    private PopupMenu sitemenu, sectorMenu, blockMenu;

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
               goToActivityWithFinish(AssociateContaner.class,null);

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
        /*  object.addProperty("CustomerID", PreferencesManager.getInstance(context).getCustomerID());*/
        object.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());
        Call<ResponseAssociateBookingList> call = apiServices.AssociateBookingList(object);
        call.enqueue(new Callback<ResponseAssociateBookingList>() {
            @Override
            public void onResponse(Call<ResponseAssociateBookingList> call, Response<ResponseAssociateBookingList> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        hideLoading();
                        textContainer1.setVisibility(View.VISIBLE);
                        total_number_of_plot.setText("Total number of bookings "+response.body().getLstBooking().size());
                        AdapterAssociateBookingLst adapter = new AdapterAssociateBookingLst(response.body().getLstBooking(), getContext());
                        recyclerview.setAdapter(adapter);

                    } else showMessage("Record Not Found!");
                    hideLoading();
                } else showMessage(response.body().getMessage());
                hideLoading();

            }

            @Override
            public void onFailure(Call<ResponseAssociateBookingList> call, Throwable t) {
                hideLoading();
            }

        });
    }
//    private void getDataSearch(String startdate,String enddate,String siteid,String sectorid,String blockid,String customerid,String bookingno,String associateid,String plotnumber)
//    {
//        showLoading();
//        JsonObject object = new JsonObject();
//        object.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());
//        object.addProperty("BookingId","");
//        object.addProperty("CustomerLoginID",customerid);
//        object.addProperty("CustomerName",associateid);
//        object.addProperty("SiteID",siteid);
//        object.addProperty("PhaseID",sectorid);
//        object.addProperty("BlockID",blockid);
//        object.addProperty("PlotNumber",plotnumber);
//        object.addProperty("BookingNumber",bookingno);
//        object.addProperty("FromDate",startdate);
//        object.addProperty("ToDate",enddate);
//        Call<ResponseAssociateBookingList> call = apiServices.AssociateBookingList(object);
//        call.enqueue(new Callback<ResponseAssociateBookingList>() {
//            @Override
//            public void onResponse(Call<ResponseAssociateBookingList> call, Response<ResponseAssociateBookingList> response) {
//                if (response.isSuccessful()) {
//                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
//                        PreferencesManager.getInstance(context).setBookingNumber(response.body().getLstBooking().get(0).getBookingNumber());
//                        hideLoading();
//                        textContainer1.setVisibility(View.VISIBLE);
//                        total_number_of_plot.setText("Total number of bookings "+response.body().getLstBooking().size());
//                        AdapterAssociateBookingLst adapter = new AdapterAssociateBookingLst(response.body().getLstBooking(), getContext());
//                        recyclerview.setAdapter(adapter);
//                    } else showMessage("Record Not Found!");
//                    hideLoading();
//                } else showMessage(response.body().getMessage());
//                hideLoading();
//            }
//            @Override
//            public void onFailure(Call<ResponseAssociateBookingList> call, Throwable t) {
//                hideLoading();
//            }
//        });
//    }

    public void getDataSearch(String startdate, String enddate, String siteid, String sectorid, String blockid, String customerid, String bookingno, String associateid, String plotnumber) {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());
        object.addProperty("BookingId", "");
        object.addProperty("CustomerLoginID", customerid);
        object.addProperty("CustomerName", associateid);
        object.addProperty("SiteID", siteid);
        object.addProperty("PhaseID", sectorid);
        object.addProperty("BlockID", blockid);
        object.addProperty("PlotNumber", plotnumber);
        object.addProperty("BookingNumber", bookingno);
        object.addProperty("FromDate", startdate);
        object.addProperty("ToDate", enddate);

        Call<ResponseAssociateBookingList> call = apiServices.AssociateBookingList(object);
        call.enqueue(new Callback<ResponseAssociateBookingList>() {
            @Override
            public void onResponse(Call<ResponseAssociateBookingList> call, Response<ResponseAssociateBookingList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        PreferencesManager.getInstance(context).setBookingNumber(response.body().getLstBooking().get(0).getBookingNumber());
//                        String bookingNumber = response.body().getLstBooking().get(0).getBookingNumber();
//                        Log.d("BookingNumber", "API Response Booking Number: " + bookingNumber);
//                        PreferencesManager.getInstance(context).setBookingNumber(bookingNumber);
                        hideLoading();
                        textContainer1.setVisibility(View.VISIBLE);
                        total_number_of_plot.setText("Total number of bookings " + response.body().getLstBooking().size());
                        AdapterAssociateBookingLst adapter = new AdapterAssociateBookingLst(response.body().getLstBooking(), getContext());
                        recyclerview.setAdapter(adapter);
                    } else {
                        showMessage("Record Not Found!");
                    }
                    hideLoading();
                } else {
                    showMessage(response.body().getMessage());
                    hideLoading();
                }
            }

            @Override
            public void onFailure(Call<ResponseAssociateBookingList> call, Throwable t) {
                hideLoading();
                Log.e("Error", "Failed to fetch booking data: " + t.getMessage());
            }
        });
    }


    private void searchhDialog() {
    searchDialog = new BottomSheetDialog(context);
    View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_bookinglist, null);
        searchDialog.setContentView(sheetView);
    TextView tv_start_date = sheetView.findViewById(R.id.tv_start_date);
    TextView tv_end_date = sheetView.findViewById(R.id.tv_end_date);
    tv_sector=sheetView.findViewById(R.id.tv_sector);
    tv_select_site=sheetView.findViewById(R.id.tv_select_site);
    select_block=sheetView.findViewById(R.id.select_block);
    EditText et_booking_number = sheetView.findViewById(R.id.et_booking_number);
    EditText et_associateId = sheetView.findViewById(R.id.et_associateId);
        EditText et_customerID = sheetView.findViewById(R.id.et_customerID);
        EditText et_plot_number = sheetView.findViewById(R.id.et_plot_number);

        Button btn_cancel = sheetView.findViewById(R.id.btn_cancel);
    Button btn_search = sheetView.findViewById(R.id.btn_search);
        sitemenu = new PopupMenu(context, tv_select_site);
        sectorMenu = new PopupMenu(context, tv_sector);
        blockMenu = new PopupMenu(context, select_block);
        PK_SiteID ="";
        PK_SectorID="";
        PK_BlockID="";
        getProductLst();
        lstsites = new ArrayList<>();
        lstSectors = new ArrayList<>();
        sublstSectors = new ArrayList<>();
        lstBlocks = new ArrayList<>();
        sublstBlocks = new ArrayList<>();
        tv_select_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sitemenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int position = item.getOrder();
                        blockMenu.getMenu().clear();
                        sectorMenu.getMenu().clear();
                        tv_select_site.setText(lstsites.get(position).getSiteName());
                        PK_SiteID = lstsites.get(position).getSiteID();
                        setSector(lstsites.get(position).getSiteID());
                        return true;
                    }
                });
                sitemenu.show();
            }
        });
        tv_sector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sectorMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int position = item.getOrder();
                        blockMenu.getMenu().clear();
                        tv_sector.setText(sublstSectors.get(position).getPhaseName());
                        PK_SectorID = sublstSectors.get(position).getPhaseID();
                        setBlock(sublstSectors.get(position).getPhaseID());
                        return true;
                    }
                });
                sectorMenu.show();
            }
        });

        select_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int position = item.getOrder();
                        select_block.setText(sublstBlocks.get(position).getBlockName());
                        PK_BlockID = sublstBlocks.get(position).getBlockID();
                        return true;
                    }
                });
                blockMenu.show();
            }
        });
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
                tv_end_date.getText().toString().trim(),PK_SiteID,PK_SectorID,PK_BlockID,et_customerID.getText().toString().trim(),et_booking_number.getText().toString().trim(),et_associateId.getText().toString().trim(),et_plot_number.getText().toString().trim());
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
                    }
                } else
                    showMessage(response.body().getMessage());
            }
            @Override
            public void onFailure(Call<ResponseSite> call, Throwable t) {
                hideLoading();
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
        }


    }


    @OnClick(R.id.tvSearch)
    public void onClick() {
        searchhDialog();
    }
}


