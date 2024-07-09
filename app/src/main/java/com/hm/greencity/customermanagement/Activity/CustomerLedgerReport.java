package com.hm.greencity.customermanagement.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.adapters.AdapterLedgerCustomer;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.common.Utils;
import com.hm.greencity.customermanagement.models.LedgerReport.ResponseLedgerReport;
import com.hm.greencity.customermanagement.models.ResponseList.LstBlock;
import com.hm.greencity.customermanagement.models.ResponseList.LstPhase;
import com.hm.greencity.customermanagement.models.ResponseList.LstSite;
import com.hm.greencity.customermanagement.models.ResponseList.ResponseSite;
import com.hm.greencity.customermanagement.models.UpdatePassword;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerLedgerReport extends BaseActivity /*implements NavigationView.OnNavigationItemSelectedListener */{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
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
    @BindView(R.id.btnsearchbutton)
    ImageView btnsearchbutton;
    private List<LstSite> lstsites;
    private List<LstPhase> lstSectors, sublstSectors;
    private List<LstBlock> lstBlocks, sublstBlocks;
    private ArrayList<String> SelectSite, SelectSector, SelectBlock;
    private String PK_SectorID, PK_SiteID, PK_BlockID;
    private PopupMenu sitemenu, sectorMenu, blockMenu;
    LinearLayout llcontainer;
    CardView cvcontainer;


    private EditText E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, custSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladger_report);
        ButterKnife.bind(this);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_id);
        toggle = new ActionBarDrawerToggle(CustomerLedgerReport.this, drawerLayout, R.string.nav_close, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backarrow);

        navigationView = (NavigationView) findViewById(R.id.navigation_id);
//        navigationView.setNavigationItemSelectedListener(CustomerLedgerReport.this);
        navigationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivityWithFinish(context,HomeTestActivity.class,null);
//               goToActivityWithFinish(context,HomeTestActivity.class,null);
            }
        });

//        View hView = navigationView.getHeaderView(0);
//        TextView nav_user = (TextView) hView.findViewById(R.id.nav_header);
//
//        TextView nav_id = (TextView) hView.findViewById(R.id.nav_header_id);
//        nav_id.setText(PreferencesManager.getInstance(context).getLoginId());

//        nav_user.setText("Welcome " + PreferencesManager.getInstance(context).getFull_Name());

        llcontainer = findViewById(R.id.container1);
        cvcontainer = findViewById(R.id.container);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerview1.setLayoutManager(layoutManager);

        if (getIntent().hasExtra("booking_id")) {
            getIntent().getStringExtra("booking_id");
            getData("", "", "", getIntent().getStringExtra("booking_id"), "");
        } else {
            searchhDialog();
        }

        recyclerview1.setHasFixedSize(true);
        btnsearchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchhDialog();
            }
        });

    }

    public void getData(String site, String sector, String block, String bookingnumber, String plotnumber) {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());
        object.addProperty("BookingNumber", bookingnumber);
        object.addProperty("SiteID", site);
        object.addProperty("PhaseID", sector);
        object.addProperty("BlockID", block);
        object.addProperty("PlotNumber", plotnumber);


        Call<ResponseLedgerReport> call = apiServices.LedgerReportList(object);
        call.enqueue(new Callback<ResponseLedgerReport>() {
            @Override
            public void onResponse(Call<ResponseLedgerReport> call, Response<ResponseLedgerReport> response) {
                hideLoading();
                // LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    llcontainer.setVisibility(View.VISIBLE);
                    cvcontainer.setVisibility(View.VISIBLE);
                    AdapterLedgerCustomer adapter = new AdapterLedgerCustomer(response.body().getPlotLadgerList(), context);
                    recyclerview1.setAdapter(adapter);
                    tvPlotRate.setText(response.body().getPloatDatailList().get(0).getPlotRate());
                    tvPlotArea.setText(response.body().getPloatDatailList().get(0).getPlotArea());
                    tvActualPlotAmount.setText(response.body().getPloatDatailList().get(0).getActualPlotAmount());
                    tvDiscount.setText(response.body().getPloatDatailList().get(0).getDiscount());
                    tvBookingAmount.setText(response.body().getPloatDatailList().get(0).getBookingAmount());
                    tvBookingDate.setText(response.body().getPloatDatailList().get(0).getBookingDate());
                    tvPaymentPlan.setText(response.body().getPloatDatailList().get(0).getPaymentPlan());
                    tvTotalPaidAmount.setText(response.body().getPloatDatailList().get(0).getTotalPaidAmount());
                    tvAllotmentDate.setText(response.body().getPloatDatailList().get(0).getAllotmentDate());
                    tvNoOfInstallments.setText(response.body().getPloatDatailList().get(0).getNoofInstallments());
                    tvInstallmentAmount.setText(response.body().getPloatDatailList().get(0).getInstallmentAmount());
                    tvBalance.setText(response.body().getPloatDatailList().get(0).getBalance());

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseLedgerReport> call, Throwable t) {
                hideLoading();
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       goToActivityWithFinish(context,HomeTestActivity.class,null);
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    protected void customAlertDialog(final String firstName, String accountNo, String address, String bankName, String bankBranch, String bankHolderName, String city, final String customberId, String dob, String email, Object gaurdianName, Object gaurdianRelation, Object gender, String ifsc, Object joiningDate, final String lastName, String mobile, String panNumber, String pinCode, String sponsorId, String name, String sponsorName, String state) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("My Profile");
        final View inflateForm = getLayoutInflater().inflate(R.layout.customer_alert_dialog, null); // Get custom login form view.
        builder.setView(inflateForm); // Set above view in alert dialog.
        builder.setCancelable(true);
        builder.create();

        final android.app.AlertDialog dialog = builder.show(); // Because only AlertDialog has cancel method.

        this.E1 = (EditText) inflateForm.findViewById(R.id.customer_name);
        this.E2 = (EditText) inflateForm.findViewById(R.id.customer_phone_number);
        this.E3 = (EditText) inflateForm.findViewById(R.id.customer_email);
        this.E4 = (EditText) inflateForm.findViewById(R.id.customer_contact_person);
        this.E5 = (EditText) inflateForm.findViewById(R.id.customer_dob);
        this.E6 = (EditText) inflateForm.findViewById(R.id.customer_address);
        this.E7 = (EditText) inflateForm.findViewById(R.id.customer_account_no);
        this.E8 = (EditText) inflateForm.findViewById(R.id.customer_ifsc);
        this.E9 = (EditText) inflateForm.findViewById(R.id.customer_bankBranch);
        this.E10 = (EditText) inflateForm.findViewById(R.id.customer_pan_no);
        this.E11 = (EditText) inflateForm.findViewById(R.id.customer_bankname);
//        this.E10
//        this.E8=


        E1.setText(firstName + " " + lastName);
        E2.setText(mobile);
        E3.setText(email);
        E4.setText(bankHolderName);
        E5.setText(dob);
        E6.setText(address);
        E7.setText(accountNo);
        E8.setText(ifsc);
        E9.setText(bankBranch);
        E10.setText(panNumber);
        E11.setText(bankName);

        Button supplierSaveButton = (Button) inflateForm.findViewById(R.id.customer_save_button);
        supplierSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

    }


    //====================================================| Navigation |====================================================
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        drawerLayout.closeDrawer(Gravity.LEFT);
//       goToActivityWithFinish(context,HomeTestActivity.class,null);
////        if (menuItem.getItemId() == R.id.due_installment_list) {
////            goToActivity(context, DueInstallment.class, null);
////
////        }
////        if (menuItem.getItemId() == R.id.all_ledger) {
////            goToActivity(context, CustomerLedgerReport.class, null);
////
////        }
////        if (menuItem.getItemId() == R.id.my_booking_list) {
////
////            goToActivity(context, PlotBooking.class, null);
////        }
////        if (menuItem.getItemId() == R.id.my_profile) {
////            showLoading();
////            JsonObject jsonObject = new JsonObject();
////            jsonObject.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());
////            Call<CustomerMyProfile> customerMyProfileCall = apiServices.getProfile(jsonObject);
////            customerMyProfileCall.enqueue(new Callback<CustomerMyProfile>() {
////                @Override
////                public void onResponse(Call<CustomerMyProfile> call, Response<CustomerMyProfile> response) {
////                    hideLoading();
////                    if (response.body().getStatusCode().equalsIgnoreCase("200"))
////                        customAlertDialog(response.body().getFirstName(), response.body().getAccountNo(), response.body().getAddress(), response.body().getBankName(), response.body().getBankBranch(), response.body().getBankHolderName(),
////                                response.body().getCity(), response.body().getCustomberId(), response.body().getDob(), response.body().getEmail(), response.body().getGaurdianName(), response.body().getGaurdianRelation(), response.body().getGender(), response.body().getIfsc(), response.body().getJoiningDate(), response.body().getLastName(), response.body().getMobile(), response.body().getPanNumber(), response.body().getPinCode(), response.body().getSponsorId(), response.body().getFirstName(), response.body().getSponsorName(), response.body().getState());
////                    else
////                        showMessage(response.body().getMessage());
////                }
////
////
////                @Override
////                public void onFailure(Call<CustomerMyProfile> call, Throwable throwable) {
////
////                }
////            });
////
////
//////            Intent intent = new Intent(HomeActivity.this, ProductsActivity.class);
//////            startActivity(intent);
////        }
////        if (menuItem.getItemId() == R.id.dashboard) {
////           goToActivityWithFinish(context,HomeTestActivity.class,null);
//////            Intent intent = new Intent(HomeActivity.this, SuppliersActivity.class);
//////            startActivity(intent);
////        }
////        if (menuItem.getItemId() == R.id.profiles_id) {
//////            Intent intent = new Intent(HomeActivity.this, ProfilesActivity.class);
//////            startActivity(intent);
////        }
////        if (menuItem.getItemId() == R.id.change_password) {
////
////            changePassword();
//////            Intent intent = new Intent(HomeActivity.this, UsersActivity.class);
//////            startActivity(intent);
////        }
////        if (menuItem.getItemId() == R.id.about_developer) {
////            aboutMe();
////        }
////        if (menuItem.getItemId() == R.id.log_out) {
////            PreferencesManager.getInstance(context).clear();
////            //===============================================| Remove SharedPreferences |===========================================
//////            SharedPreferences.Editor editor = preferences.edit();
//////            editor.clear(); //Remove from login.xml file
//////            editor.commit();
////
////            //======================================================================================================================
////
//////               goToActivity(LoginActivity.class,);
////            goToActivityWithFinish(context, LoginActivity.class, null);
////
////        }
//        return false;
//    }

    private void changePassword() {


        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Change Password");
        final View inflateForm = getLayoutInflater().inflate(R.layout.custom_alert_change_password, null); // Get custom login form view.
        builder.setView(inflateForm); // Set above view in alert dialog.
        builder.setCancelable(true);
        builder.create();

        final android.app.AlertDialog dialog = builder.show();


        this.E1 = (EditText) inflateForm.findViewById(R.id.customer_name);
        this.E2 = (EditText) inflateForm.findViewById(R.id.customer_phone_number);
        this.E3 = (EditText) inflateForm.findViewById(R.id.customer_email);


        Button supplierSaveButton = (Button) inflateForm.findViewById(R.id.customer_save_button);
        Button cancelButton = (Button) inflateForm.findViewById(R.id.customer_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


        supplierSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPassword = E1.getText().toString().trim();
                String newPassword = E2.getText().toString().trim();
                String compPassword = E3.getText().toString().trim();
                if (!oldPassword.isEmpty()) {
                    if (newPassword.equals(compPassword)) {

                        //    request POST 'http://demo1.afluex.com/webapi/ChnagePassword' \
//            --form 'OldPassword="123456"' \
//            --form 'NewPassword="12345"' \
//            --form 'CustomerID="96"'
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("OldPassword", oldPassword);
                        jsonObject.addProperty("NewPassword", newPassword);
                        jsonObject.addProperty("CustomerID", PreferencesManager.getInstance(context).getUserId());
                        Call<UpdatePassword> updatePasswordCall = apiServices.updatePassword(jsonObject);

                        updatePasswordCall.enqueue(new Callback<UpdatePassword>() {
                            @Override
                            public void onResponse(Call<UpdatePassword> call, Response<UpdatePassword> response) {
                                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                                    dialog.cancel();
                                    showMessage(response.body().getMessage());
                                } else {
                                    showMessage(response.body().getMessage());
                                }
                            }

                            @Override
                            public void onFailure(Call<UpdatePassword> call, Throwable throwable) {

                            }
                        });
                    } else {
                        showMessage("Old and New Password are not same");
                    }

                } else {
                    showMessage("Enter old password");
                }

            }
        });


    }

    //====================================================| About |====================================================
    public void aboutMe() {
        new AlertDialog.Builder(context)
                .setTitle("About")
                .setMessage("Customer Management 2020  \nVersion 1.0 \n" + context.getString(R.string.developed_by))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Thank You", Toast.LENGTH_SHORT);
                    }
                }).show();
    }


}