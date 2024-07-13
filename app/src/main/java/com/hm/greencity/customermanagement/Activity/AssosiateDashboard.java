package com.hm.greencity.customermanagement.Activity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.Fragment.AssociateBookingList;
import com.hm.greencity.customermanagement.Fragment.AssociateBusinessReport;
import com.hm.greencity.customermanagement.Fragment.AssociateLedgerReport;
import com.hm.greencity.customermanagement.Fragment.AssociatePlotAvalivility;
import com.hm.greencity.customermanagement.Fragment.CustomerListFragment;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.NetworkUtils;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.constants.BaseFragment;
import com.hm.greencity.customermanagement.login.LoginActivity;
import com.hm.greencity.customermanagement.models.AssociateDashboard.ResponseAssociateDashboard;
import com.hm.greencity.customermanagement.models.UpdatePassword;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssosiateDashboard extends BaseFragment {
    Unbinder unbinder;
    EditText E1, E2, E3;

    @BindView(R.id.imageView2)
    ImageView imageView2;

    @BindView(R.id.support)
    ImageButton support;

    @BindView(R.id.profile_img)
    ImageView profile_img;

    @BindView(R.id.tv_total_downline)
    TextView tvTotalDownline;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.cv_total_dwln)
    CardView cvTotalDwln;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.tv_total_direct)
    TextView tvTotalDirect;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.cv_total_direct)
    CardView cvTotalDirect;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.tv_total_active)
    TextView tvTotalActive;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.cv_my_ledger)
    CardView cvTotalActive;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.tv_total_inactive)
    TextView tvTotalInactive;

    @BindView(R.id.tv_total_payout)
    TextView tvTotalPayout;

    @BindView(R.id.tv_total_advance)
    TextView tvTotalAdvance;

    @BindView(R.id.tv_total_deduct)
    TextView tvTotalDeduct;

    @BindView(R.id.tv_total_balance)
    TextView tvTotalBalance;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @BindView(R.id.tvlegType)
    TextView tvlegType;

    @BindView(R.id.showAll)
    TextView showAll;

    @BindView(R.id.tvlegActive)
    TextView tvlegActive;
    @BindView(R.id.tvLegInactive)
    TextView tvLegInactive;
    @BindView(R.id.totalmembers)
    TextView totalmembers;
    @BindView(R.id.totalbusiness)
    TextView totalbusiness;
    @BindView(R.id.constrOne)
    ConstraintLayout constrOne;
    @BindView(R.id.tvLeft)
    TextView tvLeft;
    @BindView(R.id.total_left)
    TextView totalLeft;
    @BindView(R.id.paid_left)
    TextView paidLeft;
    @BindView(R.id.cf_left)
    TextView cfLeft;
    @BindView(R.id.totalbuis_left)
    TextView totalbuisLeft;
    @BindView(R.id.constrTwo)
    ConstraintLayout constrTwo;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.total_right)
    TextView totalRight;
    @BindView(R.id.paid_right)
    TextView paidRight;
    @BindView(R.id.cf_right)
    TextView cfRight;
    @BindView(R.id.totalbuis_right)
    TextView totalbuisRight;
    @BindView(R.id.constrThree)
    ConstraintLayout constrThree;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.active_total)
    TextView activeTotal;
    @BindView(R.id.inactive_total)
    TextView inactiveTotal;
    @BindView(R.id.totalmembers_total)
    TextView totalmembersTotal;
    @BindView(R.id.totalbuis_total)
    TextView totalbuisTotal;
    @BindView(R.id.text_dueInstallment)
    TextView text_dueInstallment;

    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_loginId)
    TextView tv_loginId;
    @BindView(R.id.cv_plotBooking)
    CardView cvPlotBooking;
    @BindView(R.id.cv_customerDetails)
    CardView cvCustomerDetails;
    @BindView(R.id.cv_mysummary)
    CardView cvMysummary;
    @BindView(R.id.cv_plot_ledger)
    CardView cvPlotLedger;
    @BindView(R.id.cv_plot_avaibility)
    CardView cvPlotAvaibility;
    @BindView(R.id.cv_contactus)
    CardView cvContactus;
    @BindView(R.id.cv_change_password)
    CardView cvChangePassword;
    @BindView(R.id.cv_aboutUs)
    CardView cvAboutUs;
    @BindView(R.id.cv_logout)
    CardView cvLogout;
    @BindView(R.id.add_fab)
    FloatingActionButton  add_fab;
//    @Nullable
//    @BindView(R.id.chatImage)
//    ImageView chatImage;

//    @BindView(R.id.textViewdueInstallment1)
//     ImageView textViewdueInstallment1;

//    @BindView(R.id.rl_clickmenu)
//    RelativeLayout rl_clickmenu;
//
//    @BindView(R.id.img_side_menu)
//    ImageButton img_side_menu;

    AssociateContaner associateContaner;


    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.associate_dashboard_new, container, false);
        unbinder = ButterKnife.bind(this, view);

        associateContaner = new AssociateContaner();
        tv_username.setText(PreferencesManager.getInstance(context).getFull_Name());
        tv_loginId.setText(PreferencesManager.getInstance(context).getLoginId());


//        rl_clickmenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                img_side_menu.setBackground(context.getDrawable(R.drawable.hm_logo_new_final));
//            }
//        });

        Glide.with(context).load("http://crm.hmgreencity.com/" + PreferencesManager.getInstance(context).getProfilePic()).
                apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.user_icon)
                .error(R.drawable.user_icon)
                .into(profile_img);

        if (NetworkUtils.getConnectivityStatus(context) != 0) {

            AssociateDashBoardTotals();

        } else showMessage(R.string.alert_internet);

        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                goToActivity(context, AssociateProfile.class, null);
                goToActivity(AssociateProfile.class, null);

            }
        });


       //floating ACTION BUTTON


//        textViewdueInstallment1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              showChangeLanguageDialog();
//            }
//
//
//        });


        return view;
    }


//    @Override
//    public void onDestroy() {
//        unbinder.unbind();
//        super.onDestroy();
//    }

    private void AssociateDashBoardTotals() {
        // showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("CustomerID", PreferencesManager.getInstance(context).getCustomerID());
//        object.addProperty("CustomerID","24");


        Call<ResponseAssociateDashboard> call = apiServices.AssociateDashBoard(object);
        call.enqueue(new Callback<ResponseAssociateDashboard>() {
            @Override
            public void onResponse(Call<ResponseAssociateDashboard> call, Response<ResponseAssociateDashboard> response) {
                hideLoading();
                try {

                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        tvTotalDownline.setText(response.body().getTotalDownline());
                        tvTotalDirect.setText(response.body().getTotalDirects());
                        tvTotalActive.setText(response.body().getTotalActive());
                        tvTotalInactive.setText(response.body().getTotalInActive());
                        tvTotalPayout.setText(response.body().getTotalPayout());
                        tvTotalAdvance.setText(response.body().getTotalPaidPayout());
                        tvTotalDeduct.setText(response.body().getTotalDeduction());
                        tvTotalBalance.setText(response.body().getPayoutWalletBalance());
                        totalLeft.setText(response.body().getTotalBusinessLeft());
                        totalRight.setText(response.body().getTotalBusinessRight());
                        paidLeft.setText(response.body().getPaidBusinessLeft());
                        paidRight.setText(response.body().getPaidBusinessRight());
                        totalbuisLeft.setText(response.body().getPaidBusinessLeft());
                        totalbuisRight.setText(response.body().getPaidBusinessRight());


                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {
                    showMessage("Server Issue");
                }
            }

            @Override
            public void onFailure(Call<ResponseAssociateDashboard> call, Throwable t) {
                hideLoading();
            }
        });
    }


    @OnClick({R.id.cv_plotBooking, R.id.cv_customerDetails, R.id.cv_mysummary, R.id.cv_plot_ledger, R.id.cv_plot_avaibility, R.id.cv_contactus, R.id.cv_change_password, R.id.cv_aboutUs, R.id.cv_logout, R.id.support,R.id.textViewdueInstallment1,R.id.imageViewinsta,R.id.imageViewfb,R.id.imageViewyoutube})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.cv_plotBooking:
//                ReplaceFragment(new AssociateBookingList(), "Booking List");
                AssociateContaner.currentFragment = new AssociateBookingList();
                FragmentTransaction fr4 = getFragmentManager().beginTransaction();
                fr4.replace(R.id.frame, new AssociateBookingList()).addToBackStack(null);
                fr4.commit();
                break;
            case R.id.cv_customerDetails:
//                ReplaceFragment(new CustomerListFragment(), "Customer Details");
                AssociateContaner.currentFragment = new CustomerListFragment();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame, new CustomerListFragment()).addToBackStack(null);
                fr.commit();

                break;
            case R.id.cv_mysummary:
//                ReplaceFragment(new AssociateBusinessReport(), "Business Summary");
//                getActivity().getFragmentManager().beginTransaction().replace(R.id.frame,new AssociateBusinessReport()).commit();
                AssociateContaner.currentFragment = new AssociateBusinessReport();
                FragmentTransaction fr1 = getFragmentManager().beginTransaction();

                fr1.replace(R.id.frame, new AssociateBusinessReport()).addToBackStack(null);
                fr1.commit();
                break;
            case R.id.cv_plot_ledger:
//                ReplaceFragment(new AssociateLedgerReport(), "Ledger Summary");
//                getActivity().getFragmentManager().beginTransaction().replace(R.id.frame, new AssociateLedgerReport()).commit();
                AssociateContaner.currentFragment = new AssociateLedgerReport();
                FragmentTransaction fr2 = getFragmentManager().beginTransaction();
                fr2.replace(R.id.frame, new AssociateLedgerReport()).addToBackStack(null);
                fr2.commit();
                break;
            case R.id.cv_plot_avaibility:
//                ReplaceFragment(new AssociatePlotAvalivility(), "Plot Availability");
//                getActivity().getFragmentManager().beginTransaction().replace(R.id.frame, new AssociatePlotAvalivility()).commit();
                AssociateContaner.currentFragment = new AssociatePlotAvalivility();
                FragmentTransaction fr3 = getFragmentManager().beginTransaction();
                fr3.replace(R.id.frame, new AssociatePlotAvalivility()).addToBackStack(null);
                fr3.commit();
                break;
            case R.id.cv_contactus:

                break;
            case R.id.cv_change_password:
                changePassword();
                break;
            case R.id.textViewdueInstallment1:
                showChangeLanguageDialog();
                break;
            case R.id.cv_aboutUs:
            case R.id.support:
                aboutMe();
                break;
            case R.id.cv_logout:
                logout();
                break;
            case R.id.imageViewinsta:
                openinsta();
                break;
            case R.id.imageViewfb:
                openfb();
                break;
            case R.id.imageViewyoutube:
                openyoutube();
                break;

        }
    }

    private void openinsta() {
        String url = "https://www.instagram.com/hm_green_city_2k?igsh=YXNnemQ5aWt5ZW5o";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
    private void openfb() {
        String url = "https://www.facebook.com/profile.php?id=61558813520747&mibextid=ZbWKwL";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
    private void openyoutube() {
        String url = "https://youtube.com/@Hm.city7374?si=PYfmNKUSZ66UNA17";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }


    private void changePassword() {


        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
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
//    public void aboutMe() {
//        new androidx.appcompat.app.AlertDialog.Builder(getActivity())
//                .setTitle("Contact Details")
//                .setMessage("Associate Management 2022  \nVersion 1.0 \n" + getActivity().getString(R.string.developed_by) + "\n" +
//                        "Contact us 9721497374 \nmail us hmcity7374@gmail.com")
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
////                        Toast.makeText(getApplicationContext(), "Thank You", Toast.LENGTH_SHORT);
//                    }
//                }).show();
//    }



    public void aboutMe() {
        String message = "Associate Management - 2022\n Version - 1.0\n" +
                getString(R.string.developed_by) + "\n"  +
                "Contact No.-  9651997374\n Mail - hmcity7374@gmail.com\n"+
               "Website:  http://crm.hmgreencity.com/home";

        // Create a SpannableString
        SpannableString spannableString = new SpannableString(message);

        // Find the index of the URL
        int startIndexWebsite = message.indexOf("http://crm.hmgreencity.com/home");
        int endIndexWebsite = startIndexWebsite + "http://crm.hmgreencity.com/home".length();

        // Apply clickable span for the website
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                // Handle click action for the website
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://crm.hmgreencity.com/home"));
                startActivity(intent);
            }
        }, startIndexWebsite, endIndexWebsite, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Create AlertDialog with custom message
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Contact Details")
                .setMessage(spannableString)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Positive button clicked
                    }
                })
                .show();

        // Make links clickable
        TextView messageTextView = alertDialog.findViewById(android.R.id.message);
        if (messageTextView != null) {
            messageTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }



    private void logout() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setTitle("Logout");
        builder1.setMessage("Do you really want to logout?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                (dialog, id) -> {
                    if (NetworkUtils.getConnectivityStatus(context) != 0) {
                        PreferencesManager.getInstance(context).clear();
                        PreferencesManager.getInstance(context).setIsFirstTimeLaunch(false);
                        goToActivityWithFinish(LoginActivity.class, null);
                    } else
                        showMessage(R.string.alert_internet);
                    dialog.cancel();
                });

        builder1.setNegativeButton("No", (dialog, id) -> dialog.cancel());

        AlertDialog alert11 = builder1.create();
        alert11.setOnShowListener(arg0 -> {
            alert11.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.red_color));
            alert11.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        });
        alert11.show();

    }



    private void showChangeLanguageDialog() {
        final String[] listItems = {"English", "हिंदी"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setLocale("en");
                    getActivity().recreate();
                } else if (i == 1) {
                    setLocale("hi");
                    getActivity().recreate();
                }
                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());

        // SAVE data to SharedPreferences
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences prefs = getActivity().getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }


//     case R.id.img_profile:
//            Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show();
//    //goToActivity(ContainerActivity.this, Profile.class, null);
//                    break;
//                case R.id.tv_dashboard:
//            if (!(currentFragment instanceof AssosiateDashboard))
//    ReplaceFragment(new AssosiateDashboard(), "HM Green City");
//                    break;
//                case R.id.tv_customer_details:
//            if (!(currentFragment instanceof CustomerListFragment))
//    ReplaceFragment(new CustomerListFragment(), "Customer Details");
//                    break;
//                case R.id.tv_plot_booking:
//            if (!(currentFragment instanceof AssociateBookingList))
//    ReplaceFragment(new AssociateBookingList(), "Booking List");
//                    break;
//                case R.id.tv_plot_availability:
//            if (!(currentFragment instanceof AssociatePlotAvalivility))
//    ReplaceFragment(new AssociatePlotAvalivility(), "Plot Availability");
//                    break;
//
//                case R.id.tv_business_summary:
//            if (!(currentFragment instanceof AssociateBusinessReport))
//    ReplaceFragment(new AssociateBusinessReport(), "Business Summary");
//                    break;
//
//                    case R.id.tv_ledger:
//            if (!(currentFragment instanceof AssociateLedgerReport))
//    ReplaceFragment(new AssociateLedgerReport(), "Ledger Summary");
//                    break;
//
//                case R.id.tv_profile:
//    goToActivity(context, AssociateProfile.class, null);
//
//                    break;
//
//
//                case R.id.tv_change_password:
//    changePassword();
//                    break;
//
//                case R.id.tv_logout:
//    logoutDialog(context, LoginActivity .class);
//                    break;


    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }





}
