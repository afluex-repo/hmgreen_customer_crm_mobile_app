package com.hm.greencity.customermanagement.Activity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.NotePad.NotePadActivity;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.common.NetworkUtils;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.constants.FileUtils;
import com.hm.greencity.customermanagement.login.LoginActivity;
import com.hm.greencity.customermanagement.models.CustomerMyProfile;
import com.hm.greencity.customermanagement.models.HomeActivityDashBoard;
import com.hm.greencity.customermanagement.models.Notes.GetNote.LstNotepad;
import com.hm.greencity.customermanagement.models.ResponseStatusMessage;
import com.hm.greencity.customermanagement.models.UpdatePassword;
import com.ortiz.touchview.TouchImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;
import java.io.File;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeTestActivity extends BaseActivity implements IPickCancel, IPickResult {
    private static final int REQUEST_CALL_PERMISSION = 1;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_img)
    ImageView profileImg;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.imagelogo)
    ImageView imagelogo;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.support)
    ImageButton support;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_activestatus)
    TextView associateDetails;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_loginId)
    TextView tvLoginId;
//    @SuppressLint("NonConstantResourceId")
////    @BindView(R.id.user_profile)
////    CardView userProfile;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.imageView3)
//    ImageView imageView3;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.tv_total_direct)
//    TextView tvTotalDirect;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.textView3)
//    TextView textView3;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_plotBooking)
    CardView cvMyBooking;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_customerDetails)
    CardView cvMyLedger;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_mysummary)
    CardView cvDueInstallment;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.constraintLayout2)
//    ConstraintLayout constraintLayout2;
    public static final int[] JOYFUL_COLORS1 = new int[]{Color.rgb(238, 164, 127), Color.rgb(0, 83, 156)};
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_change_password)
    CardView cvChangePassword;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_aboutUs)
    CardView cvAboutUs;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_logout)
    CardView cvLogout;
    private EditText E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, custSearch;
    PieChart pieChart;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.imagenotepad)
    ImageView imagenotepad;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.chatImage)
    ImageView chatImage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.imageView8)
    ImageView imageView8;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.call)
    ImageView call;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.searchView)
    SearchView searchView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.mail)
    TextView mail;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.Call)
    TextView Call;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.WebSite)
    TextView WebSite;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.galleryimageView)
    ImageView galleryimageView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.dgitalcardimageView)
    ImageView dgitalcardimageView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.receiptImage)
    ImageView receiptImage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.emicard)
    CardView emicard;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.documentcard)
    CardView documentcard;
    @BindView(R.id.knowledgebase)
    CardView knowledgebase;

    @BindView(R.id.inApp)
    CardView inApp;


    private CardView cvPlotBooking, cvCustomerDetails, cvMySummary,cvnewCard1,cvnewCard2,cvnewCard3,cvchange_password,cvlogout,newcardview2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard_test);
        ButterKnife.bind(this);
        tvUsername.setText(PreferencesManager.getInstance(context).getFull_Name());
        tvLoginId.setText(PreferencesManager.getInstance(context).getLoginId());
        getDashboard();
        Onclicklistener();
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView6 = findViewById(R.id.textView6);
        TextView textView7 = findViewById(R.id.textView7);
        TextView textView8 = findViewById(R.id.textView8);
        TextView textView31 = findViewById(R.id.textView31);
        TextView textView51 = findViewById(R.id.textView51);
        TextView chattext = findViewById(R.id.chattext);
        cvPlotBooking = findViewById(R.id.cv_plotBooking);
        cvCustomerDetails = findViewById(R.id.cv_customerDetails);
        cvMySummary = findViewById(R.id.cv_mysummary);
        cvnewCard1 = findViewById(R.id.cv_newCard1);
        cvnewCard2 = findViewById(R.id.cv_newCard2);
        cvnewCard3 = findViewById(R.id.cv_newCard3);
        cvchange_password = findViewById(R.id.cv_change_password);
        cvlogout = findViewById(R.id.cv_logout);
        newcardview2 = findViewById(R.id.new_cardview2);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filterTextView(textView3,cvPlotBooking, newText);
                filterTextView(textView4,cvCustomerDetails, newText);
                filterTextView(textView5,cvMySummary, newText);
                filterTextView(textView6, cvnewCard1,newText);
                filterTextView(textView7,cvnewCard2, newText);
                filterTextView(textView8,cvnewCard3, newText);
                filterTextView(textView31,cvchange_password, newText);
                filterTextView(textView51,cvlogout, newText);
                filterTextView(chattext,newcardview2, newText);

                return true;
            }
        });

    }

    private void Onclicklistener() {
        emicard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeTestActivity.this,EmiTrackingActivity.class));
                finish();
            }
        });

        documentcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeTestActivity.this,DocumentActivity.class));
                finish();
            }
        });

        imagenotepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeTestActivity.this,NotePadActivity.class));
                finish();
            }
        });
        dgitalcardimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeTestActivity.this,HomeDigitalCardActivity.class));
            }
        });
        receiptImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeTestActivity.this,ReceiptActivity2.class));
            }
        });

        knowledgebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeTestActivity.this,KnowledgeBaseActivity.class));
            }
        });

        inApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeTestActivity.this,InAppGuidenceActivity.class));
            }
        });

        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "hmcity7374@gmail.com" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                startActivity(Intent.createChooser(intent, ""));
            }
        });
        WebSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://hmgroupcompanies.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        imagelogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFullScreenDialog(R.drawable.roundlogo);
            }
        });
        galleryimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(context, GalleryActivity2.class, null);
            }
        });
        cvMyLedger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(context, CustomerLedgerReport.class, null);
            }
        });
        cvDueInstallment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(context, DueInstallment.class, null);

            }
        });
        cvMyBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(context, PlotBooking.class, null);
            }
        });
        cvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        cvAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFeedback();
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutMe();
            }
        });
        cvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProfile();
            }
        });
        chatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeTestActivity.this,ChatActivity.class));
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openinsta();
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfb();
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openyoutube();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });

    }

    @Override
    public void onNoteDelete(LstNotepad note) {

    }

    private void filterTextView(TextView textView,CardView cardView, String query) {
        String textViewText = textView.getText().toString().toLowerCase();
        query = query.toLowerCase();

        if (textViewText.contains(query)) {
            cardView.setVisibility(View.VISIBLE);
        } else {
            cardView.setVisibility(View.GONE);
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

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(context).load("http://crm.hmgreencity.com/" + PreferencesManager.getInstance(context).getProfilePic()).
                apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.user_icon)
                .error(R.drawable.user_icon)
                .into(profileImg);
    }
    private void getDashboard() {
        showLoading();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());
        Call<HomeActivityDashBoard> dashBoardCall = apiServices.getDashboard(jsonObject);
        dashBoardCall.enqueue(new Callback<HomeActivityDashBoard>() {
            @Override
            public void onResponse(@NonNull Call<HomeActivityDashBoard> call, @NonNull Response<HomeActivityDashBoard> response) {
                hideLoading();
                assert response.body() != null;
                if (response.body().getStatusCode().equalsIgnoreCase("200") && response.body().getDashBoardData().size() > 0) {
                    associateDetails.setText(response.body().getDashBoardData().get(0).getAssociateDetails());
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
                    pieChart.setDrawHoleEnabled(false);
                    pieChart.setHoleColor(Color.WHITE);
                    pieChart.setTransparentCircleRadius(61f);
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
                    dataSet.setSliceSpace(3f);
                    dataSet.setSelectionShift(5f);
                    dataSet.setColors(JOYFUL_COLORS1);
                    PieData data = new PieData(dataSet);
                    data.setValueTextSize(15f);
                    data.setValueTextColor(Color.YELLOW);
                    pieChart.setData(data);
                }
            }
            @Override
            public void onFailure(@NonNull Call<HomeActivityDashBoard> call, @NonNull Throwable throwable) {
                hideLoading();
                showMessage(throwable.getMessage());
            }
        });
    }
    private void showProfile() {
        showLoading();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());
        Call<CustomerMyProfile> customerMyProfileCall = apiServices.getProfile(jsonObject);
        customerMyProfileCall.enqueue(new Callback<CustomerMyProfile>() {
            @Override
            public void onResponse(@NonNull Call<CustomerMyProfile> call, @NonNull Response<CustomerMyProfile> response) {
                hideLoading();
                assert response.body() != null;
                assert response.body() != null;
                if (response.body().getStatusCode().equalsIgnoreCase("200"))
                    customAlertDialog(response.body().getFirstName(), response.body().getAccountNo(), response.body().getAddress(), response.body().getBankName(), response.body().getBankBranch(), response.body().getBankHolderName(),
                            response.body().getCity(), response.body().getCustomberId(), response.body().getDob(), response.body().getEmail(), response.body().getGaurdianName(), response.body().getGaurdianRelation(), response.body().getGender(), response.body().getIfsc(), response.body().getJoiningDate(), response.body().getLastName(), response.body().getMobile(), response.body().getPanNumber(), response.body().getPinCode(), response.body().getSponsorId(), response.body().getFirstName(), response.body().getSponsorName(), response.body().getState(), response.body().getProfilePic());
                else
                    showMessage(response.body().getMessage());
            }
            @Override
            public void onFailure(@NonNull Call<CustomerMyProfile> call, @NonNull Throwable throwable) {
                hideLoading();
            }
        });
    }
    protected void customAlertDialog(final String firstName, String accountNo, String address, String bankName, String bankBranch, String bankHolderName, String city, final String customberId, String dob, String email, Object gaurdianName, Object gaurdianRelation, Object gender, String ifsc, Object joiningDate, final String lastName, String mobile, String panNumber, String pinCode, String sponsorId, String name, String sponsorName, String state, String profile) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeTestActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("My Profile");
        final View inflateForm = getLayoutInflater().inflate(R.layout.customer_alert_dialog, null);
        builder.setView(inflateForm);
        builder.setCancelable(true);
        builder.create();
        final AlertDialog dialog = builder.show();
        ImageView imageView = inflateForm.findViewById(R.id.profile_img);
        TextView textView= inflateForm.findViewById(R.id.changepassword);
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
        Glide.with(context).load("http://crm.hmgreencity.com/" + PreferencesManager.getInstance(context).getProfilePic()).
                apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.user_icon)
                .error(R.drawable.user_icon)
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewProfle();
            }
        });
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
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
        supplierSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }


    private void changePassword() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(HomeTestActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Change Password");
        final View inflateForm = getLayoutInflater().inflate(R.layout.custom_alert_change_password, null);
        builder.setView(inflateForm);
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
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("OldPassword", oldPassword);
                        jsonObject.addProperty("NewPassword", newPassword);
                        jsonObject.addProperty("CustomerID", PreferencesManager.getInstance(context).getUserId());
                        Call<UpdatePassword> updatePasswordCall = apiServices.updatePassword(jsonObject);
                        updatePasswordCall.enqueue(new Callback<UpdatePassword>() {
                            @Override
                            public void onResponse(@NonNull Call<UpdatePassword> call, @NonNull Response<UpdatePassword> response) {
                                assert response.body() != null;
                                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                                    dialog.cancel();
                                    showMessage(response.body().getMessage());
                                } else {
                                    showMessage(response.body().getMessage());
                                }
                            }
                            @Override
                            public void onFailure(@NonNull Call<UpdatePassword> call, @NonNull Throwable throwable) {
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
    private void uploadFeedback() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(HomeTestActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Send Feedback");
        final View inflateForm = getLayoutInflater().inflate(R.layout.feedback_form, null);
        builder.setView(inflateForm);
        builder.setCancelable(true);
        builder.create();
        final android.app.AlertDialog dialog = builder.show();
        this.E1 = (EditText) inflateForm.findViewById(R.id.feedback_title);
        this.E2 = (EditText) inflateForm.findViewById(R.id.edt_feedback);
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
                String title = E1.getText().toString().trim();
                String feedback = E2.getText().toString().trim();
                {
                    if (!(E1.getText().toString().trim().isEmpty())&&!(E2.getText().toString().trim().isEmpty()))
                    {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("Title", title);
                        jsonObject.addProperty("Description", feedback);
                        jsonObject.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getUserId());
                        jsonObject.addProperty("AddedBy", PreferencesManager.getInstance(context).getUserId());
                        Call<UpdatePassword> updateFeedback = apiServices.uploadFeedback(jsonObject);
                        updateFeedback.enqueue(new Callback<UpdatePassword>() {
                            @Override
                            public void onResponse(@NonNull Call<UpdatePassword> call, @NonNull Response<UpdatePassword> response) {
                                assert response.body() != null;
                                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                                    dialog.cancel();
                                    showMessage(response.body().getMessage());
                                } else {
                                    showMessage(response.body().getMessage());
                                }
                            }
                            @Override
                            public void onFailure(@NonNull Call<UpdatePassword> call, @NonNull Throwable throwable) {
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(context, "Enter title and feedback both...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    //====================================================| About |====================================================
    public void aboutMe() {
        String message = "Associate Management - 2022\n Version - 1.0\n" +
                getString(R.string.developed_by) + "\n"  +
                "Contact No.-  9651997374\n Mail - hmcity7374@gmail.com\n";
        SpannableString spannableString = new SpannableString(message);
        int startIndexWebsite = message.indexOf("https://hmgroupcompanies.com");
        int endIndexWebsite = startIndexWebsite + "https://hmgroupcompanies.com".length();
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hmgroupcompanies.com"));
                startActivity(intent);
            }
        }, startIndexWebsite, endIndexWebsite, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        AlertDialog alertDialog = new AlertDialog.Builder(HomeTestActivity.this)
                .setTitle("Contact Details")
                .setMessage(spannableString)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
        TextView messageTextView = alertDialog.findViewById(android.R.id.message);
        if (messageTextView != null) {
            messageTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                assert result != null;
                profileFile = FileUtils.getFile(context, result.getUri());
                uploadFile(profileFile);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                assert result != null;
                Exception error = result.getError();
            }
        }
    }
    private ProgressDialog pd;
    private File profileFile;
    private void showProgressDialog() {
        pd = new ProgressDialog(context);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setTitle("Upload Profile Pic...");
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.show();
    }
    private void uploadFile(File homeWorkFile) {
        showLoading();
        RequestBody requestBody;
        MultipartBody.Part body = null;
        requestBody = RequestBody.create(MediaType.parse("image/*"), homeWorkFile);
        body = MultipartBody.Part.createFormData("ProfilePic", homeWorkFile.getName(), requestBody);
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), PreferencesManager.getInstance(context).getUserId());
        Call<ResponseStatusMessage> call = apiServices.uploadProfilePic(userId, body);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<ResponseStatusMessage> call, @NonNull Response<ResponseStatusMessage> response) {
                hideLoading();
                try {
                    assert response.body() != null;
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        showMessage(response.body().getMessage());
                        PreferencesManager.getInstance(context).setProfilePic(response.body().getProfilePic());
                        Glide.with(context).load("http://crm.hmgreencity.com/" + response.body().getProfilePic()).
                                apply(RequestOptions.circleCropTransform())
                                .placeholder(R.drawable.user_icon)
                                .error(R.drawable.user_icon)
                                .into(profileImg);
                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseStatusMessage> call, @NonNull Throwable t) {
                hideLoading();
            }
        });
    }
    @Override
    public void onCancelClick() {

    }
    private PickImageDialog dialog;
    void showDialog() {
        PickSetup pickSetup = new PickSetup();
        pickSetup.setTitle("Select Profile Picture");
        pickSetup.setGalleryIcon(com.vansuita.pickimage.R.mipmap.gallery_colored);
        pickSetup.setCameraIcon(com.vansuita.pickimage.R.mipmap.camera_colored);
        pickSetup.setCancelTextColor(R.color.colorAccent);
        dialog = PickImageDialog.build(pickSetup);
        dialog.setOnPickCancel(this);
        dialog.show(this);
    }
    @Override
    public void onPickResult(PickResult pickResult) {
        if (pickResult.getError() == null) {
            CropImage.activity(pickResult.getUri()).setCropShape(CropImageView.CropShape.RECTANGLE)
                    .setAspectRatio(1, 1)
                    .setFixAspectRatio(true)
                    .start(this);
        }

    }
    private void viewProfle() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        final View inflateForm = getLayoutInflater().inflate(R.layout.profile_picture_layout, null);
        builder.setView(inflateForm);
        builder.setCancelable(true);
        builder.create();
        final android.app.AlertDialog dialog = builder.show();
        TouchImageView imageView = inflateForm.findViewById(R.id.iv_image_activity);
        Glide.with(context).load("http://crm.hmgreencity.com/" + PreferencesManager.getInstance(context).getProfilePic())
                .placeholder(R.drawable.user_icon)
                .error(R.drawable.user_icon)
                .into(imageView);
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
                showDialog();
                dialog.cancel();
            }
        });
    }
    private void showChangeLanguageDialog() {
        final String [] listItems = {"English","हिंदी"};
        androidx.appcompat.app.AlertDialog.Builder mBuilder = new androidx.appcompat.app.AlertDialog.Builder(HomeTestActivity.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i==0){
                    FileUtils.setLocale("en",getBaseContext());
                    recreate();
                }
                else if (i==1){
                    FileUtils.setLocale("hi",getBaseContext());
                    recreate();
                }
                dialogInterface.dismiss();

            }
        });

        androidx.appcompat.app.AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Setings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        finish();
    }
    private void showFullScreenDialog(int imageResId) {
        Dialog dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
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
    @SuppressLint("QueryPermissionsNeeded")
    private void sendEmail() {
        String emailAddress = "hmcity7374@gmail.com";
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", emailAddress, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body of the email here");
        if (emailIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }
    private void makePhoneCall() {
        String phoneNumber = "+91-9651997374";
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            startCallIntent(phoneNumber);
        }
    }
    @SuppressLint("QueryPermissionsNeeded")
    private void startCallIntent(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        if (callIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivity(callIntent);
        }
    }

    private void openWebsite() {
        Uri uri = Uri.parse("https://hmgroupcompanies.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    private void logout() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Logout");
        builder1.setMessage("Do you really want to logout?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                (dialog, id) -> {
                    if (NetworkUtils.getConnectivityStatus(context) != 0) {
                        PreferencesManager.getInstance(context).clear();
                        PreferencesManager.getInstance(context).setIsFirstTimeLaunch(false);
                       Intent intent = new Intent(HomeTestActivity.this,LoginActivity.class);
                       startActivity(intent);
                       finish();
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


}
