package com.hm.greencity.customermanagement.Activity;
import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
    private static final int REQUEST_CALL_PERMISSION = 1;
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
    @BindView(R.id.textViewdueInstallment1)
     TextView    textViewdueInstallment1;
    @BindView(R.id.img_side_menu)
    ImageView img_side_menu;
    @BindView(R.id.associateSearchView)
    SearchView associateSearchView;

    @BindView((R.id.call))
    TextView call;

    @BindView((R.id.mail))
    TextView mail;

    @BindView((R.id.webSite))
    TextView webSite;

    private CardView cvplotBooking, cvcustomerDetails, cvmysummary,cvnewCard1,cvnewCard2,cvnewCard3,cvchange_password,cvlogout,newcardview2;

    AssociateContaner associateContaner;
    @SuppressLint({"ClickableViewAccessibility", "MissingInflatedId"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.associate_dashboard_new, container, false);
        unbinder = ButterKnife.bind(this, view);


        associateContaner = new AssociateContaner();
        tv_username.setText(PreferencesManager.getInstance(context).getFull_Name());
        tv_loginId.setText(PreferencesManager.getInstance(context).getLoginId());


        TextView textView3 = view.findViewById(R.id.tv_mybooking1);
        TextView textView4 = view.findViewById(R.id.textViewmyLedger);
        TextView textView5 = view.findViewById(R.id.textViewdueInstallment);
        TextView textView6 = view.findViewById(R.id.textViewinsta);
        TextView textView7 = view.findViewById(R.id.textViewfb);
        TextView textView8 = view.findViewById(R.id.textViewyoutube);

        TextView textView31 = view.findViewById(R.id.tv_plot_ledger);
        TextView textView51 = view.findViewById(R.id.textViewmyLedger1);
        TextView chattext = view.findViewById(R.id.textViewdueInstallment1);

        TextView textView9 = view.findViewById(R.id.textView31);
        TextView textView10 = view.findViewById(R.id.textView51);



       CardView cvplotBooking = view.findViewById(R.id.cv_plotBooking);
        CardView cvcustomerDetails = view.findViewById(R.id.cv_customerDetails);
        CardView cvmysummary = view.findViewById(R.id.cv_mysummary);
        CardView cvnewCard1 = view.findViewById(R.id.cv_newCard1);
        CardView cvnewCard2 = view.findViewById(R.id.cv_newCard2);
        CardView cvnewCard3 = view.findViewById(R.id.cv_newCard3);

        CardView cvnewCard4 = view.findViewById(R.id.cv_plot_ledger);
        CardView cvnewCard5 = view.findViewById(R.id.cv_plot_avaibility);
        CardView cvnewCard6 = view.findViewById(R.id.cv_contactus);

        CardView cvchange_password = view.findViewById(R.id.cv_change_password);
        CardView cvlogout = view.findViewById(R.id.cv_logout);
        CardView newcardview2 = view.findViewById(R.id.new_cardview2);






        Glide.with(context).load("http://crm.hmgreencity.com/" + PreferencesManager.getInstance(context).getProfilePic()).
                apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.user_icon)
                .error(R.drawable.user_icon)
                .into(profile_img);

        if (NetworkUtils.getConnectivityStatus(context) != 0) {

            AssociateDashBoardTotals();

        } else showMessage(R.string.alert_internet);


        textViewdueInstallment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }
        });

        img_side_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFullScreenDialog(R.drawable.roundlogo);
            }
        });

        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(AssociateProfile.class, null);

            }
        });

        associateSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String query = newText != null ? newText : "";
                filterTextView(textView3, cvplotBooking, query);
                filterTextView(textView4, cvcustomerDetails, query);
                filterTextView(textView5, cvmysummary, query);
                filterTextView(textView6, cvnewCard1, query);
                filterTextView(textView7, cvnewCard2, query);
                filterTextView(textView8, cvnewCard3, query);
                filterTextView(textView31, cvnewCard4, query);
                filterTextView(textView51, cvnewCard5, query);
                filterTextView(textView9, cvchange_password, query);
                filterTextView(textView10, cvlogout, query);
                filterTextView(chattext, cvnewCard6, query);


                return true;
            }
        });

      call.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              makePhoneCall();
          }
      });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        webSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openWebsite();
            }
        });


//        imagelogo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showFullScreenDialog(R.drawable.roundlogo);
//
//            }
//        });

        return view;
    }


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
        String url = "https://youtube.com/@hmgroupofcompanies-s3j?si=aQNXn4h0oceo00RW";
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
        androidx.appcompat.app.AlertDialog.Builder mBuilder = new androidx.appcompat.app.AlertDialog.Builder(requireContext());
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setLocale("en");
                } else if (i == 1) {
                    setLocale("hi");
                }
                dialogInterface.dismiss();

                // Recreate activity after setting locale
                if (getActivity() != null) {
                    getActivity().recreate();
                }
            }
        });

        androidx.appcompat.app.AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);

        // Apply the configuration to the context
        Context context = getActivity();
        if (context != null) {
            context = context.createConfigurationContext(config);

            // Save language preference
            SharedPreferences.Editor editor = context.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
            editor.putString("My_Lang", lang);
            editor.apply();

            // Notify user that locale is set
            Toast.makeText(context, "Language changed to " + (lang.equals("en") ? "English" : "Hindi"), Toast.LENGTH_SHORT).show();
        }
    }





//    private void setLocale(String lang) {
//        Locale locale = new Locale(lang);
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
//
//        // SAVE data to SharedPreferences
//        SharedPreferences.Editor editor = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
//        editor.putString("My_Lang", lang);
//        editor.apply();
//    }


    public void loadLocale() {
        SharedPreferences prefs = getActivity().getSharedPreferences("Settings", MODE_PRIVATE);
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


    private void showFullScreenDialog(int imageResId) {
        Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_fullscreen_image);

        ImageView dialogImageView = dialog.findViewById(R.id.dialog_imageview);
        dialogImageView.setImageResource(imageResId);

        dialogImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    private void filterTextView(TextView textView, CardView cardView, String query) {
        if (textView == null || cardView == null) {
            Log.e("FilterTextView", "TextView or CardView is null.");
            return;
        }
        if (query == null) {
            query = "";
        }
        String textViewText = textView.getText() != null ? textView.getText().toString().toLowerCase() : "";
        query = query.toLowerCase();
        Log.d("FilterTextView", "TextView text: " + textViewText);
        Log.d("FilterTextView", "Query: " + query);
        if (textViewText.contains(query)) {
            cardView.setVisibility(View.VISIBLE);
        } else {
            cardView.setVisibility(View.GONE);
        }
    }


    private void sendEmail() {
        String emailAddress = "hmcity7374@gmail.com";
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", emailAddress, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body of the email here");

        if (emailIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }

    private void makePhoneCall() {
        String phoneNumber = "+91-9651997374";
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            startCallIntent(phoneNumber);
        }
    }

    private void startCallIntent(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        if (callIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(callIntent);
        }
    }

    private void openWebsite() {
        String url = "https://hmgroupcompanies.com";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (browserIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(browserIntent);
        }
    }




}
