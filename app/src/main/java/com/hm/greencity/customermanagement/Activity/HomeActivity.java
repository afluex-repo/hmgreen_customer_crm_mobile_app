package com.hm.greencity.customermanagement.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.adapters.DashboardAdapter;
import com.hm.greencity.customermanagement.models.CustomerMyProfile;
import com.hm.greencity.customermanagement.models.DueInstallmentDashBoard;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.login.LoginActivity;
import com.hm.greencity.customermanagement.models.HomeActivityDashBoard;
import com.hm.greencity.customermanagement.models.UpdatePassword;

import java.util.ArrayList;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    private EditText E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, custSearch;

    PieChart pieChart;
    BarChart barChart;

    //For checking SharedPreferences



    TextView totalPaymentAmount, totalPaidAmount, totalPendingAmount, DashboardclientName, totalBookingNumber, totalPlotAmount;
    RecyclerView rv_dashBoardDueInstallment;


    public static final int[] JOYFUL_COLORS1 = new int[]{Color.rgb(106, 167, 134), Color.rgb(217, 80, 138)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        totalBookingNumber = findViewById(R.id.totalBookingNumbrer);
        totalPlotAmount = findViewById(R.id.totalPayingAmount);


//        if (Build.VERSION.SDK_INT >= 21) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }

//        setContentView(R.layout.activity_home);

 /* ActionBar actionbar = getActionBar();
        actionbar.setTitle("HM Green City");
        actionbar.setSubtitle("Customer");
        getActionBar().setDisplayShowTitleEnabled(false);
        actionbar.hide();*/
//        setContentView(R.layout.activity_home);
        // Toolbar containertoolbar = findViewById(R.id.toolbar);


        //====================================================| To Display Navigation Bar Icon and Back |====================================================
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_id);
        toggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.navigation_id);
        navigationView.setNavigationItemSelectedListener(HomeActivity.this);


        View hView = navigationView.getHeaderView(0);
        TextView nav_user = (TextView) hView.findViewById(R.id.nav_header);

        TextView nav_id = (TextView) hView.findViewById(R.id.nav_header_id);
        nav_id.setText(PreferencesManager.getInstance(context).getLoginId());
        nav_user.setText("Welcome "+PreferencesManager.getInstance(context).getFull_Name());


        totalPaymentAmount = findViewById(R.id.totalPaymentAcccount);
        totalPaidAmount = findViewById(R.id.totalPaidAmount);
        totalPendingAmount = findViewById(R.id.totalPendingAmount);
        DashboardclientName = findViewById(R.id.clientName);
        rv_dashBoardDueInstallment = findViewById(R.id.rv_dashBoardDueInstallment);

        DashboardclientName.setText(PreferencesManager.getInstance(context).getFull_Name());
        getDashboard();
        getDueInstallment();


    }

    //====================================================| OptionsMenu and Back press disabled |====================================================
    //Back press disabled
    @Override
    public void onBackPressed() {

    }

    private void getDashboard() {

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());

        Call<HomeActivityDashBoard> dashBoardCall = apiServices.getDashboard(jsonObject);

        dashBoardCall.enqueue(new Callback<HomeActivityDashBoard>() {
            @Override
            public void onResponse(Call<HomeActivityDashBoard> call, Response<HomeActivityDashBoard> response) {
//                hideLoading();
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {

//                    totalPaymentAmount.setText("\u20B9" + response.body().getDashBoardData().get(0).getTotalPlotAmount());
                    totalBookingNumber.setText(response.body().getDashBoardData().get(0).getTotalBooking());
                    totalPlotAmount.setText("\u20B9" + response.body().getDashBoardData().get(0).getTotalPlotAmount());
                    totalPaidAmount.setText("\u20B9" + response.body().getDashBoardData().get(0).getTotalPaidAmount());
                    totalPendingAmount.setText("\u20B9" + response.body().getDashBoardData().get(0).getTotalPending());

                    float totalPayableAmount = Float.parseFloat(response.body().getDashBoardData().get(0).getTotalPlotAmount());
                    float totalRemainingAmount = Float.parseFloat(response.body().getDashBoardData().get(0).getTotalPending());
                    float totalPaidAmount = Float.parseFloat(response.body().getDashBoardData().get(0).getTotalPaidAmount());

                    float perPaid = (totalPaidAmount / totalPayableAmount) * 100;
                    float perRemaining = (totalRemainingAmount / totalPayableAmount) * 100;

                    pieChart = (PieChart) findViewById(R.id.pie_chart);
                    pieChart.setUsePercentValues(true);
                    pieChart.getDescription().setEnabled(true);
                    pieChart.setExtraOffsets(5, 10, 5, 5);
                    pieChart.setDragDecelerationFrictionCoef(0.99f);
                    pieChart.setDrawHoleEnabled(false); //false that shows filled up pie
                    pieChart.setHoleColor(Color.WHITE);
                    pieChart.setTransparentCircleRadius(61f); //31 or 91

                    ArrayList<PieEntry> entries = new ArrayList<>();
                    entries.add(new PieEntry(perPaid, "Paid"));
                    entries.add(new PieEntry(perRemaining, "Remaining"));


                    Description d = new Description();
                    d.setText("");
                    d.setTextSize(15);
                    pieChart.setDescription(d);
                    pieChart.animateY(1400, Easing.EasingOption.EaseInCubic);
                    PieDataSet dataSet = new PieDataSet(entries, "Amount");
                    dataSet.setValueFormatter(new PercentFormatter());
                    dataSet.setSliceSpace(3f); //pie divided space
                    dataSet.setSelectionShift(5f);
                    dataSet.setColors(JOYFUL_COLORS1);
                    PieData data = new PieData(dataSet);
                    data.setValueTextSize(15f);
                    data.setValueTextColor(Color.YELLOW);

                    pieChart.setData(data);

                }
            }

            @Override
            public void onFailure(Call<HomeActivityDashBoard> call, Throwable throwable) {
//                hideLoading();
                showMessage(throwable.getMessage());
            }
        });

    }

    private void getDueInstallment() {
        showLoading();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());

        Call<DueInstallmentDashBoard> dashBoardCall = apiServices.getDueInstallmentDashBoard(jsonObject);

        dashBoardCall.enqueue(new Callback<DueInstallmentDashBoard>() {
            @Override
            public void onResponse(Call<DueInstallmentDashBoard> call, Response<DueInstallmentDashBoard> response) {
                hideLoading();
                if (response.isSuccessful()){
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    if (!response.body().getLstduelist().isEmpty())
                    {
                        DashboardAdapter dashboardAdapter = new DashboardAdapter(response.body().getLstduelist(), context);
                        rv_dashBoardDueInstallment.setAdapter(dashboardAdapter);
                        dashboardAdapter.notifyDataSetChanged();

                    }

                } else {
                    showMessage(response.body().getMessage());
                }

            }else
                {
                    showMessage("Server Error");
                }
            }

            @Override
            public void onFailure(Call<DueInstallmentDashBoard> call, Throwable throwable) {
                showMessage(throwable.getMessage());
                hideLoading();

            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    protected void customAlertDialog(final String firstName, String accountNo, String address, String bankName, String bankBranch, String bankHolderName, String city, final String customberId, String dob, String email, Object gaurdianName, Object gaurdianRelation, Object gender, String ifsc, Object joiningDate, final String lastName, String mobile, String panNumber, String pinCode, String sponsorId, String name, String sponsorName, String state) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(HomeActivity.this);
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
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(Gravity.LEFT);
        if (menuItem.getItemId() == R.id.due_installment_list) {
            Intent intent = new Intent(HomeActivity.this, DueInstallment.class);
            startActivity(intent);
        }
        if (menuItem.getItemId() == R.id.all_ledger) {
            goToActivity(context,CustomerLedgerReport.class,null);
//            Intent intent = new Intent(HomeActivity.this, CustomersActivity.class);
//            startActivity(intent);
        }
        if (menuItem.getItemId() == R.id.my_booking_list) {
            Intent intent = new Intent(HomeActivity.this, PlotBooking.class);
            startActivity(intent);
        }
        if (menuItem.getItemId() == R.id.my_profile) {
            showLoading();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());
            Call<CustomerMyProfile> customerMyProfileCall = apiServices.getProfile(jsonObject);
            customerMyProfileCall.enqueue(new Callback<CustomerMyProfile>() {
                @Override
                public void onResponse(Call<CustomerMyProfile> call, Response<CustomerMyProfile> response) {
                    hideLoading();
                    if (response.body().getStatusCode().equalsIgnoreCase("200"))
                        customAlertDialog(response.body().getFirstName(), response.body().getAccountNo(), response.body().getAddress(), response.body().getBankName(), response.body().getBankBranch(), response.body().getBankHolderName(),
                                response.body().getCity(), response.body().getCustomberId(), response.body().getDob(), response.body().getEmail(), response.body().getGaurdianName(), response.body().getGaurdianRelation(), response.body().getGender(), response.body().getIfsc(), response.body().getJoiningDate(), response.body().getLastName(), response.body().getMobile(), response.body().getPanNumber(), response.body().getPinCode(), response.body().getSponsorId(), response.body().getFirstName(), response.body().getSponsorName(), response.body().getState());
                    else
                        showMessage(response.body().getMessage());
                }


                @Override
                public void onFailure(Call<CustomerMyProfile> call, Throwable throwable) {

                }
            });


//            Intent intent = new Intent(HomeActivity.this, ProductsActivity.class);
//            startActivity(intent);
        }
        if (menuItem.getItemId() == R.id.dashboard) {
//            Intent intent = new Intent(HomeActivity.this, SuppliersActivity.class);
//            startActivity(intent);
        }
        if (menuItem.getItemId() == R.id.profiles_id) {
//            Intent intent = new Intent(HomeActivity.this, ProfilesActivity.class);
//            startActivity(intent);
        }
        if (menuItem.getItemId() == R.id.change_password) {

            changePassword();
//            Intent intent = new Intent(HomeActivity.this, UsersActivity.class);
//            startActivity(intent);
        }
        if (menuItem.getItemId() == R.id.about_developer) {
            aboutMe();
        }
        if (menuItem.getItemId() == R.id.log_out) {
            PreferencesManager.getInstance(context).clear();
            //===============================================| Remove SharedPreferences |===========================================
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.clear(); //Remove from login.xml file
//            editor.commit();

            //======================================================================================================================

//               goToActivity(LoginActivity.class,);
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return false;
    }

    private void changePassword() {


        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(HomeActivity.this);
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
        new AlertDialog.Builder(HomeActivity.this)
                .setTitle("About")
                .setMessage("Customer Management 2020  \nVersion 1.0 \n" + HomeActivity.this.getString(R.string.developed_by))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Thank You", Toast.LENGTH_SHORT);
                    }
                }).show();
    }


}
