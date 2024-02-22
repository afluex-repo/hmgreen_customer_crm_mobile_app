package com.hm.greencity.customermanagement.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.constants.FileUtils;
import com.hm.greencity.customermanagement.login.LoginActivity;
import com.hm.greencity.customermanagement.models.CustomerMyProfile;
import com.hm.greencity.customermanagement.models.HomeActivityDashBoard;
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

    @BindView(R.id.profile_img)
    ImageView profileImg;

    @BindView(R.id.support)
    ImageButton support;

    @BindView(R.id.tv_username)
    TextView tvUsername;

    @BindView(R.id.tv_activestatus)
    TextView associateDetails;

    @BindView(R.id.tv_loginId)
    TextView tvLoginId;
    @BindView(R.id.user_profile)
    CardView userProfile;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.tv_total_direct)
    TextView tvTotalDirect;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.cv_plotBooking)
    CardView cvMyBooking;
    @BindView(R.id.cv_customerDetails)
    CardView cvMyLedger;
    @BindView(R.id.cv_mysummary)
    CardView cvDueInstallment;
    //    @BindView(R.id.pie_chart)
//    PieChart pieChart;
    @BindView(R.id.constraintLayout2)
    ConstraintLayout constraintLayout2;
//    83, 156, 255

    //    238 164 127
//    0 83 156
    public static final int[] JOYFUL_COLORS1 = new int[]{Color.rgb(238, 164, 127), Color.rgb(0, 83, 156)};
    @BindView(R.id.cv_change_password)
    CardView cvChangePassword;
    @BindView(R.id.cv_aboutUs)
    CardView cvAboutUs;
    @BindView(R.id.cv_logout)
    CardView cvLogout;
    private EditText E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, custSearch;
    PieChart pieChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard_test);
        ButterKnife.bind(this);

        tvUsername.setText(PreferencesManager.getInstance(context).getFull_Name());
        tvLoginId.setText(PreferencesManager.getInstance(context).getLoginId());
        getDashboard();
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
                PreferencesManager.getInstance(context).clear();
                //===============================================| Remove SharedPreferences |===========================================
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.clear(); //Remove from login.xml file
//            editor.commit();

                //======================================================================================================================

//               goToActivity(LoginActivity.class,);
                Intent intent = new Intent(HomeTestActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
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
            public void onResponse(Call<HomeActivityDashBoard> call, Response<HomeActivityDashBoard> response) {
                hideLoading();
                if (response.body().getStatusCode().equalsIgnoreCase("200") && response.body().getDashBoardData().size() > 0) {

//                    totalPaymentAmount.setText("\u20B9" + response.body().getDashBoardData().get(0).getTotalPlotAmount());
//                    totalBookingNumber.setText(response.body().getDashBoardData().get(0).getTotalBooking());
//                    totalPlotAmount.setText("\u20B9" + response.body().getDashBoardData().get(0).getTotalPlotAmount());
//                    totalPaidAmount.setText("\u20B9" + response.body().getDashBoardData().get(0).getTotalPaidAmount());
//                    totalPendingAmount.setText("\u20B9" + response.body().getDashBoardData().get(0).getTotalPending());
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
                    pieChart.setDrawHoleEnabled(false); //false that shows filled up pie
                    pieChart.setHoleColor(Color.WHITE);
                    pieChart.setTransparentCircleRadius(61f); //31 or 91


                    ArrayList<PieEntry> entries = new ArrayList<>();
                    entries.add(new PieEntry(perPaid, "Paid"));
                    entries.add(new PieEntry(perRemaining, "Remaining"));


                    Description d = new Description();
                    d.setText("");
                    d.setTextSize(15);
//                    d.setPosition(0,0);
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
            public void onResponse(Call<CustomerMyProfile> call, Response<CustomerMyProfile> response) {
                hideLoading();
                if (response.body().getStatusCode().equalsIgnoreCase("200"))
                    customAlertDialog(response.body().getFirstName(), response.body().getAccountNo(), response.body().getAddress(), response.body().getBankName(), response.body().getBankBranch(), response.body().getBankHolderName(),
                            response.body().getCity(), response.body().getCustomberId(), response.body().getDob(), response.body().getEmail(), response.body().getGaurdianName(), response.body().getGaurdianRelation(), response.body().getGender(), response.body().getIfsc(), response.body().getJoiningDate(), response.body().getLastName(), response.body().getMobile(), response.body().getPanNumber(), response.body().getPinCode(), response.body().getSponsorId(), response.body().getFirstName(), response.body().getSponsorName(), response.body().getState(), response.body().getProfilePic());
                else
                    showMessage(response.body().getMessage());
            }


            @Override
            public void onFailure(Call<CustomerMyProfile> call, Throwable throwable) {
                hideLoading();
            }
        });

    }

    protected void customAlertDialog(final String firstName, String accountNo, String address, String bankName, String bankBranch, String bankHolderName, String city, final String customberId, String dob, String email, Object gaurdianName, Object gaurdianRelation, Object gender, String ifsc, Object joiningDate, final String lastName, String mobile, String panNumber, String pinCode, String sponsorId, String name, String sponsorName, String state, String profile) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeTestActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("My Profile");
        final View inflateForm = getLayoutInflater().inflate(R.layout.customer_alert_dialog, null); // Get custom login form view.
        builder.setView(inflateForm); // Set above view in alert dialog.
        builder.setCancelable(true);
        builder.create();

        final AlertDialog dialog = builder.show(); // Because only AlertDialog has cancel method.
        ImageView imageView = inflateForm.findViewById(R.id.profile_img);
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
//        PreferencesManager.getInstance(context).setProfilePic(profile);
        Glide.with(context).load("http://crm.hmgreencity.com/" + PreferencesManager.getInstance(context).getProfilePic()).
                apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.user_icon)
                .error(R.drawable.user_icon)
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showDialog();
                viewProfle();
            }
        });

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

    private void changePassword() {


        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(HomeTestActivity.this);
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


    private void uploadFeedback() {


        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(HomeTestActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Send Feedback");
        final View inflateForm = getLayoutInflater().inflate(R.layout.feedback_form, null); // Get custom login form view.
        builder.setView(inflateForm); // Set above view in alert dialog.
        builder.setCancelable(true);
        builder.create();

        final android.app.AlertDialog dialog = builder.show();


        this.E1 = (EditText) inflateForm.findViewById(R.id.feedback_title);
        this.E2 = (EditText) inflateForm.findViewById(R.id.edt_feedback);
//        this.E3 = (EditText) inflateForm.findViewById(R.id.customer_email);


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
//                String compPassword = E3.getText().toString().trim();
//                if (!oldPassword.isEmpty())

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
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Contact Details")
                .setMessage("Customer Management 2022  \nVersion 1.0 \n" + HomeTestActivity.this.getString(R.string.developed_by) + "\n" +
                        "Contact us 9721497374 \nmail us hmcity7374@gmail.com")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(getApplicationContext(), "Thank You", Toast.LENGTH_SHORT);
                    }
                }).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Logger.logItem("Reach onActivity");
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                profileFile = FileUtils.getFile(context, result.getUri());

//                Glide.with(context).load(result.getUri()).
//                        apply(RequestOptions.circleCropTransform())
//                        .placeholder(R.drawable.user_icon)
//                        .error(R.drawable.user_icon)
//                        .into(profileImg);
                uploadFile(profileFile);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
//                LoggerUtil.logItem(error.getMessage());
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

//    private void uploadFile(File file, String type) {
//        try {
//            showProgressDialog();
//            //creating request body for Profile file
//            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
//            RequestBody id = RequestBody.create(MediaType.parse("text/plain"), PreferencesManager.getInstance(context).getUserId());
//            RequestBody typeBody = RequestBody.create(MediaType.parse("text/plain"), type);
//            MultipartBody.Part body = MultipartBody.Part.createFormData("", file.getName(), requestBody);
//            //creating our api
//            ApiServices apiServices = ServiceGenerator.createServiceFile(ApiServices.class);
//            //creating a call and calling the upload image method
//            Call<JsonObject> call = apiServices.uploadImage(id, typeBody, body);
//            //finally performing the call
//            call.enqueue(new Callback<JsonObject>() {
//                @Override
//                public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
//                    LoggerUtil.logItem(response.body());
//                    Log.e("***********", call.request().url().toString());
//                    pd.dismiss();
//                    PreferencesManager.getInstance(context).setImage(response.body().get("ImageUrl").getAsString());
////                    {"ImageUrl":"http://demo3.afluex.com//ProfilePicture/86_DWPE_1916200916302_cropped8846116371962730712.jpg",
////                            "Pk_UserId":"2","Status":"0","DocumentType":"Profile"}
//                    Gson gson = new GsonBuilder().create();
//                    JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
//                    if (jsonObject.get("Status").getAsString().equalsIgnoreCase("0")) {
//                        PreferencesManager.getInstance(context).setProfilePic(jsonObject.get("ImageUrl").getAsString());
//                    }
//
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
//                    Log.e("***********", call.request().url().toString());
//                    Log.e("***********", "= " + t.getMessage());
//                    //Log.e("***********", "= " + t.getLocalizedMessage());
//                    showMessage(t.getMessage());
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void uploadFile(File homeWorkFile) {

//        LoggerUtil.logItem(compressedImageFile.length());
//        LoggerUtil.logItem(homeWorkFile.length());


        showLoading();
        RequestBody requestBody;
        MultipartBody.Part body = null;
        requestBody = RequestBody.create(MediaType.parse("image/*"), homeWorkFile);
        body = MultipartBody.Part.createFormData("ProfilePic", homeWorkFile.getName(), requestBody);
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), PreferencesManager.getInstance(context).getUserId());
        //RequestBody adharnumber = RequestBody.create(MediaType.parse("text/plain"), tvAdharNumber.getText().toString().trim());
        Call<ResponseStatusMessage> call = apiServices.uploadProfilePic(userId, body);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
//                LoggerUtil.logItem(response.body());
                try {
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        // hideLoading();
                        showMessage(response.body().getMessage());
                        PreferencesManager.getInstance(context).setProfilePic(response.body().getProfilePic());
//                        Glide.with(context).load("http://crm.hmgreencity.com/"+response.body().getProfilePic()).
//                                apply(RequestOptions.circleCropTransform())
//                                .placeholder(R.drawable.user_icon)
//                                .error(R.drawable.user_icon)
//                                .into(profileImg);


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
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
//                LoggerUtil.logItem(t.getMessage());
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
        pickSetup.setGalleryIcon(R.mipmap.gallery_colored);
        pickSetup.setCameraIcon(R.mipmap.camera_colored);
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
//        builder.setIcon(R.mipmap.ic_launcher);
//        builder.setTitle("My profile");
        final View inflateForm = getLayoutInflater().inflate(R.layout.profile_picture_layout, null); // Get custom login form view.
        builder.setView(inflateForm); // Set above view in alert dialog.
        builder.setCancelable(true);
        builder.create();

        final android.app.AlertDialog dialog = builder.show();


//        this.E1 = (EditText) inflateForm.findViewById(R.id.customer_name);
//        this.E2 = (EditText) inflateForm.findViewById(R.id.customer_phone_number);
//        this.E3 = (EditText) inflateForm.findViewById(R.id.customer_email);


        TouchImageView imageView = inflateForm.findViewById(R.id.iv_image_activity);
        Glide.with(context).load("http://crm.hmgreencity.com/" + PreferencesManager.getInstance(context).getProfilePic())
//                .apply(RequestOptions.circleCropTransform())
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        finish();
    }
}
