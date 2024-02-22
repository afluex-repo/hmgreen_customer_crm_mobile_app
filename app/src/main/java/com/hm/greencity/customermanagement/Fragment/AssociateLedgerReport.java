package com.hm.greencity.customermanagement.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.adapters.AdapterLedgerAssociate;
import com.hm.greencity.customermanagement.adapters.AdapterLedgerCustomer;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.common.Utils;
import com.hm.greencity.customermanagement.constants.BaseFragment;
import com.hm.greencity.customermanagement.models.AssociateLedgerList;
import com.hm.greencity.customermanagement.models.LedgerReport.ResponseLedgerReport;
import com.hm.greencity.customermanagement.models.ResponseList.LstBlock;
import com.hm.greencity.customermanagement.models.ResponseList.LstPhase;
import com.hm.greencity.customermanagement.models.ResponseList.LstSite;
import com.hm.greencity.customermanagement.models.ResponseList.ResponseSite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssociateLedgerReport extends BaseFragment {

    Unbinder unbinder;




    @BindView(R.id.tv_plot_rate)
    TextView tvPlotRate;
    @BindView(R.id.tv_plot_area)
    TextView tvPlotArea;
    @BindView(R.id.tv_actual_plot_amount)
    TextView tvActualPlotAmount;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;
    @BindView(R.id.tv_booking_amount)
    TextView tvBookingAmount;
    @BindView(R.id.tv_booking_date)
    TextView tvBookingDate;
    @BindView(R.id.tv_payment_plan)
    TextView tvPaymentPlan;
    @BindView(R.id.tv_total_paid_amount)
    TextView tvTotalPaidAmount;
    @BindView(R.id.tv_allotment_date)
    TextView tvAllotmentDate;
    @BindView(R.id.tv_no_of_installments)
    TextView tvNoOfInstallments;
    @BindView(R.id.tv_installment_amount)
    TextView tvInstallmentAmount;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;

    BottomSheetDialog bottomSheetDialog;
    TextView tvSelectSite;
    TextView tvSector;
    TextView selectBlock;
    EditText etBookingNumber;
    EditText etPlotNumber;

    @BindView(R.id.img_side_menu)
    ImageView btnsearchbutton;

    private List<LstSite> lstsites;
    private List<LstPhase> lstSectors, sublstSectors;
    private List<LstBlock> lstBlocks, sublstBlocks;
    private ArrayList<String> SelectSite, SelectSector, SelectBlock;
    private String PK_SectorID, PK_SiteID, PK_BlockID;
    private PopupMenu sitemenu, sectorMenu, blockMenu;

    LinearLayout llcontainer;
    CardView cvcontainer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.associate_ledger_report_page, container, false);
        unbinder = ButterKnife.bind(this, view);

        llcontainer = view.findViewById(R.id.container1);
        cvcontainer = view.findViewById(R.id.container);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerview1.setLayoutManager(layoutManager);


   /*     Bundle bundle = this.getArguments();
        if (bundle != null) {
            int myInt = bundle.getInt(key, defaultValue);
        }

        if (getIntent().hasExtra("booking_id")) {
            getIntent().getStringExtra("booking_id");
            getData("", "", "", getIntent().getStringExtra("booking_id"), "");
        } else {
            searchhDialog();
        }
*/


        searchhDialog();
        recyclerview1.setHasFixedSize(true);

        btnsearchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchhDialog();
            }
        });



        return view;
    }



    public void getData(String site, String sector, String block, String bookingnumber, String plotnumber) {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginID", PreferencesManager.getInstance(context).getLoginId());
        object.addProperty("BookingNo", bookingnumber);
        object.addProperty("SiteID", site);
        object.addProperty("PhaseID", sector);
        object.addProperty("BlockID", block);
        object.addProperty("PlotNumber", plotnumber);


        Call<AssociateLedgerList> associateLedgerListCall=apiServices.AssociateLedgerReportList(object);
        associateLedgerListCall.enqueue(new Callback<AssociateLedgerList>() {
            @Override
            public void onResponse(Call<AssociateLedgerList> call, Response<AssociateLedgerList> response) {
                hideLoading();
                // LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    llcontainer.setVisibility(View.VISIBLE);
                    cvcontainer.setVisibility(View.VISIBLE);

                    AdapterLedgerAssociate adapterLedgerAssociate=new AdapterLedgerAssociate(response.body().getLstLedger(),context);
                    recyclerview1.setAdapter(adapterLedgerAssociate);

                    tvPlotRate.setText(response.body().getPlotRate());
                    tvPlotArea.setText(response.body().getPlotArea());
                    tvActualPlotAmount.setText(response.body().getActualPlotRate());
//                    tvDiscount.setText(response.body().getDiscount().toString());
                    tvBookingAmount.setText(response.body().getBookingamount());
                    tvBookingDate.setText(response.body().getBookingDate());
                    tvPaymentPlan.setText(response.body().getTotalInstallment());
                    tvTotalPaidAmount.setText(response.body().getPaidAmount());
                    tvAllotmentDate.setText(response.body().getPaymentDate());
                    tvNoOfInstallments.setText(response.body().getTotalInstallment());
                    tvInstallmentAmount.setText(response.body().getInstallmentAmount());
                    tvBalance.setText(response.body().getBalanceAllotmentAmount());

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<AssociateLedgerList> call, Throwable t) {

            }
        });

    }


    private void searchhDialog() {
        bottomSheetDialog = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dilog_ledger, null);
        bottomSheetDialog.setContentView(sheetView);
        tvSelectSite = sheetView.findViewById(R.id.tv_site);
        tvSector = sheetView.findViewById(R.id.tv_sector);
        selectBlock = sheetView.findViewById(R.id.tv_block);
        etBookingNumber = sheetView.findViewById(R.id.et_booking_number);
        etPlotNumber = sheetView.findViewById(R.id.et_plot_number);
        Button btnCancel = sheetView.findViewById(R.id.btn_cancel);
        Button btnSearch1 = sheetView.findViewById(R.id.btn_search);
        sitemenu = new PopupMenu(context, tvSelectSite);
        sectorMenu = new PopupMenu(context, tvSector);
        blockMenu = new PopupMenu(context, selectBlock);
        PK_SiteID ="";
        PK_SectorID="";
        PK_BlockID="";

        getProductLst();

        //  getSector();
        //  getBlock();

        lstsites = new ArrayList<>();
        lstSectors = new ArrayList<>();
        sublstSectors = new ArrayList<>();
        lstBlocks = new ArrayList<>();
        sublstBlocks = new ArrayList<>();


        //  SelectSite = new ArrayList<String>();
        //  SelectSector = new ArrayList<String>();
        // SelectBlock = new ArrayList<String>();
        // SelectSiteType=new ArrayList<String>();
        tvSelectSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sitemenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int position = item.getOrder();

                        selectBlock.setText("");
                        tvSector.setText("");
                        blockMenu.getMenu().clear();
                        sectorMenu.getMenu().clear();
                        tvSelectSite.setText(lstsites.get(position).getSiteName());

                        PK_SiteID = lstsites.get(position).getSiteID();
                        setSector(lstsites.get(position).getSiteID());


                        //  getSector();
                        //  packageMenu = lstPackages.get(position).getProductName();
                        return true;
                    }
                });
                sitemenu.show();
            }
        });
        tvSector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sectorMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int position = item.getOrder();
                        selectBlock.setText("");

                        blockMenu.getMenu().clear();
                        tvSector.setText(sublstSectors.get(position).getPhaseName());
                        PK_SectorID = sublstSectors.get(position).getPhaseID();
                        setBlock(sublstSectors.get(position).getPhaseID());


                        //  packageMenu = lstPackages.get(position).getProductName();
                        return true;
                    }
                });
                sectorMenu.show();

            }


        });

        selectBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int position = item.getOrder();
                        selectBlock.setText(sublstBlocks.get(position).getBlockName());
                        PK_BlockID = sublstBlocks.get(position).getBlockID();

                        //  packageMenu = lstPackages.get(position).getProductName();
                        return true;
                    }
                });
                blockMenu.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });


        btnSearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etBookingNumber.getText().toString().isEmpty() || (!PK_SiteID.isEmpty() && !PK_SectorID.isEmpty() && !PK_BlockID.isEmpty() && !etPlotNumber.getText().toString().trim().isEmpty())) {

                    getData(PK_SiteID, PK_SectorID, PK_BlockID
                            , etBookingNumber.getText().toString().trim(), etPlotNumber.getText().toString().trim());
                    bottomSheetDialog.dismiss();

                } else {

                    showMessage("Enter all Fields");
                }
            }
        });


        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.show();
    }

    private void datePicker(final TextView et) {
        Calendar cal = Calendar.getInstance();
        int mYear, mMonth, mDay;

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                et.setText(Utils.changeDateFormat(dayOfMonth, monthOfYear, year));
            }
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

//                        //  PK_SiteID = response.body().getLstSite().get(i).getSiteID();


                    }

//                    ----------------------------


                    // Toast.makeText(ReInvestment.this, response+"", Toast.LENGTH_SHORT).show();
                   /* for (int i = 0; i < lstSectors.size(); i++) {

                        sectorMenu.getMenu().add(0, 0, i, lstSectors.get(i).getPhaseName());

                        PK_SectorID = response.body().getLstPhase().get(i).getPhaseID();

                        // Toast.makeText(context,selectSiteTypeid+ "", Toast.LENGTH_SHORT).show();
                        //  getPackage();

                    }
                    for (int i = 0; i < lstBlocks.size(); i++) {

                        blockMenu.getMenu().add(0, 0, i, lstBlocks.get(i).getBlockName());

                        PK_BlockID = response.body().getLstBlock().get(i).getBlockID();

                        // Toast.makeText(context,selectSiteTypeid+ "", Toast.LENGTH_SHORT).show();
                        //  getPackage();

                    }
*/

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
        for (int i = 0; i < lstSectors.size(); i++) {
            if (lstSectors.get(i).getPhaseSiteID().equalsIgnoreCase(siteID)) {
                sublstSectors.add(lstSectors.get(i));
            }
        }


        for (int i = 0; i < sublstSectors.size(); i++) {

            sectorMenu.getMenu().add(0, 0, i, sublstSectors.get(i).getPhaseName());

//             // PK_SectorID = sublstSectors.get(i).getPhaseID();

            // Toast.makeText(context,selectSiteTypeid+ "", Toast.LENGTH_SHORT).show();
            //  getPackage();

        }


    }


    private void setBlock(String blockID) {

        sublstBlocks.clear();
        blockMenu.getMenu().clear();
        for (int i = 0; i < lstBlocks.size(); i++) {
            if (lstBlocks.get(i).getBlockSiteID().equalsIgnoreCase(blockID)) {
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
}
