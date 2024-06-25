package com.hm.greencity.customermanagement.Fragment;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.Build;
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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.Activity.AssociateContaner;
import com.hm.greencity.customermanagement.Activity.AssosiateDashboard;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.adapters.AdapterAssociatePlotAvalility;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.constants.BaseFragment;
import com.hm.greencity.customermanagement.models.AssociatePlotAvalibility.AssociateLstAvailability;
import com.hm.greencity.customermanagement.models.AssociatePlotAvalibility.ResponseAssociatePlotAvalibility;
import com.hm.greencity.customermanagement.models.ResponseList.LstBlock;
import com.hm.greencity.customermanagement.models.ResponseList.LstPhase;
import com.hm.greencity.customermanagement.models.ResponseList.LstSite;
import com.hm.greencity.customermanagement.models.ResponseList.ResponseSite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssociatePlotAvalivility extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @BindView(R.id.tvSearch)
    TextView tvSearch;

    @BindView(R.id.backarrow)
    ImageView tvbackarrow;



    BottomSheetDialog bottomSheetDialog;
    TextView tvSelectSite;
    TextView tvSector;
    TextView selectBlock;
    EditText etBookingNumber;
    EditText etPlotNumber;
    @BindView(R.id.tvall)
    TextView tvall;
    @BindView(R.id.tvbooked)
    TextView tvbooked;
    @BindView(R.id.tvavail)
    TextView tvavail;
    @BindView(R.id.tvalot)
    TextView tvalot;
    @BindView(R.id.tvreg)
    TextView tvreg;


    private List<LstSite> lstsites;
    private List<LstPhase> lstSectors, sublstSectors;
    private List<LstBlock> lstBlocks, sublstBlocks;
    private ArrayList<String> SelectSite, SelectSector, SelectBlock;
    private String PK_SectorID, PK_SiteID, PK_BlockID;
    private PopupMenu sitemenu, sectorMenu, blockMenu;

    boolean allowedPagination = true;
    boolean wait = false;
    int pageNumber = 1;

    LinearLayoutManager layoutManager;

    List<AssociateLstAvailability> associateLstAvailabilityList;
    AssociateLstAvailability associateLstAvailability;

    AdapterAssociatePlotAvalility adapter;


    String mpk_siteID = "", mpk_sectorID = "", mpk_blockID = "", mtrim1 = "",status="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        layoutManager = new LinearLayoutManager(context);
        recyclerview.setLayoutManager(layoutManager);
        associateLstAvailabilityList = new LinkedList<>();
        adapter = new AdapterAssociatePlotAvalility(associateLstAvailabilityList, context);
        recyclerview.setAdapter(adapter);

//        Shader shader = new RadialGradient(0,0,500, Color.parseColor("#ffd4d4"),Color.parseColor("#ffaaaa"),Shader.TileMode.REPEAT);
//        tvbooked.getPaint().setShader(shader);
//
//        Shader shader1 = new LinearGradient(0,tvalot.getPaint().measureText(tvalot.getText().toString()),0,0, Color.parseColor("#ffd900"),Color.parseColor("#91f300"),Shader.TileMode.REPEAT);
//        tvalot.getPaint().setShader(shader1);
        LinearLayout linearLayout = view.findViewById(R.id.textContainer);
        linearLayout.setVisibility(View.VISIBLE);


        tvSearch.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                searchhDialog();
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

//        getData();
        getData("", "", ""
                , "", "");


//        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                if (dy > 0) //check for scroll down
//                {
//                    int totalItemCount = layoutManager.getItemCount();
//                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
//                    if (!allowedPagination && lastVisibleItemPosition == totalItemCount - 1) {
//
//                        getData("", "", ""
//                                , "", "");
//
////                        allowedPagination = true;
////                        pageNumber = pageNumber + 1;
////                        if (!dynamicPages.equalsIgnoreCase("")) {
////                            loadMoreData(currentPage);
////                        }
//                    }
//                }
//            }
//        });


        final int[] pastVisiblesItems = new int[1];
        final int[] visibleItemCount = new int[1];
        final int[] totalItemCount = new int[1];
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount[0] = layoutManager.getChildCount();
                    totalItemCount[0] = layoutManager.getItemCount();
                    pastVisiblesItems[0] = layoutManager.findFirstVisibleItemPosition();

                    if (allowedPagination) {
                        if ((visibleItemCount[0] + pastVisiblesItems[0]) >= totalItemCount[0]) {
//                            allowedPagination = false;

                            Log.v("...", "Last Item Wow !");
                            // Do pagination.. i.e. fetch new data

//                            allowedPagination = true;

                            getData("", "", ""
                                    , "", "");
                        }
                    }
                }
            }
        });

        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void searchhDialog() {
        bottomSheetDialog = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dilog_ledger, null);
        bottomSheetDialog.setContentView(sheetView);
        tvSelectSite = sheetView.findViewById(R.id.tv_site);
        tvSector = sheetView.findViewById(R.id.tv_sector);
        selectBlock = sheetView.findViewById(R.id.tv_block);
        etBookingNumber = sheetView.findViewById(R.id.et_booking_number);
        etBookingNumber.setVisibility(View.GONE);
        etPlotNumber = sheetView.findViewById(R.id.et_plot_number);
        Button btnCancel = sheetView.findViewById(R.id.btn_cancel);
        Button btnSearch1 = sheetView.findViewById(R.id.btn_search);
        sitemenu = new PopupMenu(context, tvSelectSite);
        sectorMenu = new PopupMenu(context, tvSector);
        blockMenu = new PopupMenu(context, selectBlock);
        mpk_siteID = "";
        mpk_sectorID = "";
        mpk_blockID = "";
        mtrim1 = "";

        PK_SiteID = "";
        PK_SectorID = "";
        PK_BlockID = "";
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
                        mpk_siteID = lstsites.get(position).getSiteID();


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

                        mpk_sectorID = sublstSectors.get(position).getPhaseID();

//                        setBlock(sublstSectors.get(position).getPhaseID());


                        //  packageMenu = lstPackages.get(position).getProductName();
                        return true;
                    }
                });
                sectorMenu.show();

            }


        });


       /* recyclerview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {

                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    page++;
                    loadingPB.setVisibility(View.VISIBLE);
                    getDataFromAPI(page, limit);
                }
            }
        });*/


        selectBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int position = item.getOrder();
                        selectBlock.setText(sublstBlocks.get(position).getBlockName());
                        PK_BlockID = sublstBlocks.get(position).getBlockID();
                        mpk_blockID = sublstBlocks.get(position).getBlockID();
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
                mpk_siteID = "";
                mpk_sectorID = "";
                mpk_blockID = "";
                mtrim1 = "";

                bottomSheetDialog.dismiss();
            }
        });


        btnSearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etBookingNumber.getText().toString().isEmpty() || (!PK_SiteID.isEmpty() && !PK_SectorID.isEmpty() && !PK_BlockID.isEmpty()/* && !etPlotNumber.getText().toString().trim().isEmpty()*/)) {
                    pageNumber = 1;
//                    allowedPagination=true;
                    status="";
                    allowedPagination = true;
                    wait = false;

                    associateLstAvailabilityList.clear();


                    adapter.notifyDataSetChanged();

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

//    private void getData(String pk_siteID, String pk_sectorID, String pk_blockID, String trim, String trim1) {
//    }

    private void getData(String pk_siteID, String pk_sectorID, String pk_blockID, String trim, String trim1) {

        if (allowedPagination && !wait) {
//            showLoading();
            wait = true;
            JsonObject object = new JsonObject();
//
//        'SiteID': '',
//                'SectorID': '',
//                'BlockID': '',
//                'status': '',
//                'PlotNumber': '',
//                'page': '1'

//            object.addProperty("CustomerID", PreferencesManager.getInstance(context).getCustomerID());
//            object.addProperty("SiteID", pk_siteID);
//            object.addProperty("SectorID", pk_sectorID);
//            object.addProperty("BlockID", pk_blockID);
//            object.addProperty("status", "");
//            object.addProperty("PlotNumber", trim1);
//            object.addProperty("page", pageNumber + "");

            object.addProperty("CustomerID", PreferencesManager.getInstance(context).getCustomerID());
            object.addProperty("SiteID", mpk_siteID);
            object.addProperty("SectorID", mpk_sectorID);
            object.addProperty("BlockID", mpk_blockID);
            object.addProperty("status", status);
            object.addProperty("PlotNumber", trim1);
            object.addProperty("page", pageNumber + "");


            Call<ResponseAssociatePlotAvalibility> call = apiServices.AssociatePlotAvailability(object);
            call.enqueue(new Callback<ResponseAssociatePlotAvalibility>() {
                @Override
                public void onResponse(Call<ResponseAssociatePlotAvalibility> call, Response<ResponseAssociatePlotAvalibility> response) {
                    hideLoading();
                    /*  if (response.isSuccessful()) {*/
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        hideLoading();
                        wait = false;

                        if (response.body().getLstPlotAvailability().size() < 9) {
                            allowedPagination = false;
                        }
                        pageNumber++;
//                        associateLstAvailabilityList.add(response.body().getLstPlotAvailability());
                        associateLstAvailabilityList.addAll(response.body().getLstPlotAvailability());

                        adapter.notifyDataSetChanged();
//                        showMessage(response.body().getMessage());


                    } else {
                        showMessage(response.body().getMessage());
                    }
           /* }else showMessage("Record Not Found!");
            hideLoading();
                hideLoading();*/

                }

                @Override
                public void onFailure(Call<ResponseAssociatePlotAvalibility> call, Throwable t) {
                    hideLoading();
                }


            });
        }
    }


    private void getDatastatus(String plotStatus) {

        if (allowedPagination && !wait) {
//            showLoading();
            wait = true;
            JsonObject object = new JsonObject();
//
//        'SiteID': '',
//                'SectorID': '',
//                'BlockID': '',
//                'status': '',
//                'PlotNumber': '',
//                'page': '1'

//            object.addProperty("CustomerID", PreferencesManager.getInstance(context).getCustomerID());
//            object.addProperty("SiteID", pk_siteID);
//            object.addProperty("SectorID", pk_sectorID);
//            object.addProperty("BlockID", pk_blockID);
//            object.addProperty("status", "");
//            object.addProperty("PlotNumber", trim1);
//            object.addProperty("page", pageNumber + "");

            object.addProperty("CustomerID", PreferencesManager.getInstance(context).getCustomerID());
            object.addProperty("SiteID", mpk_siteID);
            object.addProperty("SectorID", mpk_sectorID);
            object.addProperty("BlockID", mpk_blockID);
            object.addProperty("status", plotStatus);
            object.addProperty("PlotNumber", "");
            object.addProperty("page", pageNumber + "");


            Call<ResponseAssociatePlotAvalibility> call = apiServices.AssociatePlotAvailability(object);
            call.enqueue(new Callback<ResponseAssociatePlotAvalibility>() {
                @Override
                public void onResponse(Call<ResponseAssociatePlotAvalibility> call, Response<ResponseAssociatePlotAvalibility> response) {
                    hideLoading();
                    /*  if (response.isSuccessful()) {*/
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        hideLoading();
                        wait = false;

                        if (response.body().getLstPlotAvailability().size() < 9) {
                            allowedPagination = false;
                        }
                        pageNumber++;
//                        associateLstAvailabilityList.add(response.body().getLstPlotAvailability());
                        associateLstAvailabilityList.addAll(response.body().getLstPlotAvailability());

                        adapter.notifyDataSetChanged();
//                        showMessage(response.body().getMessage());


                    } else {
                        showMessage(response.body().getMessage());
                    }
           /* }else showMessage("Record Not Found!");
            hideLoading();
                hideLoading();*/

                }

                @Override
                public void onFailure(Call<ResponseAssociatePlotAvalibility> call, Throwable t) {
                    hideLoading();
                }


            });
        }
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

            // PK_SectorID = sublstSectors.get(i).getPhaseID();

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

    @OnClick({R.id.tvall, R.id.tvbooked, R.id.tvavail, R.id.tvalot, R.id.tvreg})
    public void onClick(View view) {

        pageNumber = 1;
        allowedPagination = true;
        wait = false;
        associateLstAvailabilityList.clear();
        adapter.notifyDataSetChanged();

        switch (view.getId()) {
            case R.id.tvall:
                status="0";
                getDatastatus("0");
                break;
            case R.id.tvbooked:
                status="B";


                getDatastatus("B");
                break;
            case R.id.tvavail:
                status="A";
                getDatastatus("A");
                break;
            case R.id.tvalot:
                status="H";
                getDatastatus("H");
                break;
            case R.id.tvreg:
                status="R";
                getDatastatus("R");
                break;
        }
    }
}
