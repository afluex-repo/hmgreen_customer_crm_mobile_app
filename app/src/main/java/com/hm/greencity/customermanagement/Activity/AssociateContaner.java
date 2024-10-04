package com.hm.greencity.customermanagement.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.JsonObject;

import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.constants.BaseActivity;
import com.hm.greencity.customermanagement.models.UpdatePassword;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AssociateContaner extends BaseActivity /*implements NavigationView.OnNavigationItemSelectedListener*/ {

    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    public static Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        ButterKnife.bind(this);
//        Toolbar containertoolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(containertoolbar);
//
//        ReplaceFragment(new AssosiateDashboard(), "HM Group OF Company");

        // Find and set the toolbar
        Toolbar containertoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(containertoolbar);

// Enable the Up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        ReplaceFragment(new AssosiateDashboard(), "HM Group OF Company");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    //    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return false;
//    }

//    @OnClick({R.id.img_side_menu, R.id.rl_clickmenu})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.img_side_menu:
//            case R.id.rl_clickmenu:
//                if (!(currentFragment instanceof AssosiateDashboard))
//                    ReplaceFragment(new AssosiateDashboard(), "HM Green City");
////                    goToActivity(context, AssociateContaner.class, null);
////                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
////                    drawerLayout.closeDrawer(GravityCompat.START);
////                } else {
////                    drawerLayout.openDrawer(GravityCompat.START);
////                }
//                break;
//        }
//    }

    public void ReplaceFragment(Fragment setFragment, String title) {
        new Handler().postDelayed(() -> {
            currentFragment = setFragment;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame, setFragment);
//            tvTitle.setText(title);
            transaction.commitAllowingStateLoss();
        }, 200);
    }

 /*   class DrawerMenuItems {

        DrawerMenuItems(View itemView) {
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.img_profile, R.id.tv_dashboard, R.id.tv_customer_details, R.id.tv_plot_booking, R.id.tv_plot_availability,
                R.id.tv_profile, R.id.tv_change_password, R.id.tv_logout, R.id.tv_business_summary, R.id.tv_ledger})

        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.img_profile:
                    Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show();
//                    goToActivity(ContainerActivity.this, Profile.class, null);
                    break;
                case R.id.tv_dashboard:
                    if (!(currentFragment instanceof AssosiateDashboard))
                        ReplaceFragment(new AssosiateDashboard(), "HM Green City");
                    break;
                case R.id.tv_customer_details:
                    if (!(currentFragment instanceof CustomerListFragment))
                        ReplaceFragment(new CustomerListFragment(), "Customer Details");
                    break;
                case R.id.tv_plot_booking:
                    if (!(currentFragment instanceof AssociateBookingList))
                        ReplaceFragment(new AssociateBookingList(), "Booking List");
                    break;
                case R.id.tv_plot_availability:
                    if (!(currentFragment instanceof AssociatePlotAvalivility))
                        ReplaceFragment(new AssociatePlotAvalivility(), "Plot Availability");
                    break;

                case R.id.tv_business_summary:
                    if (!(currentFragment instanceof AssociateBusinessReport))
                        ReplaceFragment(new AssociateBusinessReport(), "Business Summary");
                    break;

                    case R.id.tv_ledger:
                    if (!(currentFragment instanceof AssociateLedgerReport))
                        ReplaceFragment(new AssociateLedgerReport(), "Ledger Summary");
                    break;

                case R.id.tv_profile:
                    goToActivity(context, AssociateProfile.class, null);

                    break;


                case R.id.tv_change_password:
                    changePassword();
                    break;

                case R.id.tv_logout:
                    logoutDialog(context, LoginActivity.class);
                    break;
            }
            drawerLayout.closeDrawers();
        }
    }*/

    private void changePassword() {


        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Change Password");
        final View inflateForm = getLayoutInflater().inflate(R.layout.custom_alert_change_password, null); // Get custom login form view.
        builder.setView(inflateForm); // Set above view in alert dialog.
        builder.setCancelable(true);
        builder.create();

        final android.app.AlertDialog dialog = builder.show();


        EditText E1 = (EditText) inflateForm.findViewById(R.id.customer_name);
        EditText E2 = (EditText) inflateForm.findViewById(R.id.customer_phone_number);
        EditText E3 = (EditText) inflateForm.findViewById(R.id.customer_email);


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

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() < 1) {
            if (currentFragment instanceof AssosiateDashboard) {
                finishAffinity();
            } else
                {
                ReplaceFragment(new AssosiateDashboard(), "HM Green City");
//                bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
            }
        } else {
            fm.popBackStack();
        }
    }

}
