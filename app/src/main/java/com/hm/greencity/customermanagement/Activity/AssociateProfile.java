package com.hm.greencity.customermanagement.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.constants.FileUtils;
import com.hm.greencity.customermanagement.models.Notes.GetNote.LstNotepad;
import com.hm.greencity.customermanagement.models.ResponseAssociateProfile;
import com.hm.greencity.customermanagement.models.ResponseStatusMessage;
import com.hm.greencity.customermanagement.models.ResponseUpdateProfile;
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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AssociateProfile extends BaseActivity implements IPickCancel, IPickResult {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img)
    ImageView profileImage;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_lastname)
    TextView tvLastname;
    @BindView(R.id.tv_mem_id)
    TextView tvMemId;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_email)
    EditText tvEmail;
    @BindView(R.id.male)
    RadioButton male;
    @BindView(R.id.female)
    RadioButton female;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;
    @BindView(R.id.cardview1)
    RelativeLayout cardview1;
    @BindView(R.id.tv_account_number)
    EditText tvAccountNumber;
    @BindView(R.id.tv_bank_name)
    EditText tvBankName;
    @BindView(R.id.tv_branch_name)
    EditText tvBranchName;
    @BindView(R.id.tv_ifsc)
    EditText tvIfsc;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.et_panCard)
    EditText etPanCard;
    @BindView(R.id.tv_Dob)
    TextView tvDob;
    @BindView(R.id.cardview2)
    CardView cardview2;
    @BindView(R.id.changepassword)
     TextView changepassword;
    EditText E1, E2, E3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        ButterKnife.bind(this);
        tvTitle.setText("Associate Profile");
        getData();

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showDialog();
                viewProfle();
            }
        });
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(context).load("http://crm.hmgreencity.com/"+PreferencesManager.getInstance(context).getProfilePic()).
                apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.user_icon)
                .error(R.drawable.user_icon)
                .into(profileImage);

    }

    public void getUpdateProfile() {
        showLoading();
        JsonObject object=new JsonObject();
        object.addProperty("CustomerID",PreferencesManager.getInstance(context).getCustomerID());
        object.addProperty("FirstName",tvName.getText().toString().trim());
        object.addProperty("LastName",tvLastname.getText().toString().trim());
        object.addProperty("Mobile",tvContact.getText().toString().trim());
        object.addProperty("EmailId",tvEmail.getText().toString().trim());
        object.addProperty("PanNumber",etPanCard.getText().toString().trim());
        object.addProperty("DoB",tvDob.getText().toString().trim());
        object.addProperty("","");
        object.addProperty("AccountHolderName","");
        object.addProperty("AccountNumber",tvAccountNumber.getText().toString().trim());
        object.addProperty("BankName",tvBankName.getText().toString().trim());
        object.addProperty("BankBranch",tvBranchName.getText().toString().trim());
        object.addProperty("IFSC",tvIfsc.getText().toString().trim());

        Call<ResponseUpdateProfile> call = apiServices.AssociateUpdateProfile(object);
        call.enqueue(new Callback<ResponseUpdateProfile>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
                hideLoading();
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    onBackPressed();
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseUpdateProfile> call, Throwable t) {
                hideLoading();
            }
        });
    }
        @OnClick({R.id.img_back, R.id.btn_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.btn_update:
              onBackPressed();
                break;
        }
    }

    @Override
    public void onNoteDelete(LstNotepad note) {
    }

    private void getData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginID", PreferencesManager.getInstance(context).getLoginId());
        Call<ResponseAssociateProfile> call = apiServices.AssociateGetProfile(object);
        call.enqueue(new Callback<ResponseAssociateProfile>() {
            @Override
            public void onResponse(Call<ResponseAssociateProfile> call, Response<ResponseAssociateProfile> response) {
                hideLoading();
                try {

                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        tvName.setText(response.body().getFirstName());
                        tvLastname.setText(response.body().getLastName());

                        tvContact.setText(response.body().getMobile());
                        tvEmail.setText(response.body().getEmailId());
                        tvAccountNumber.setText(response.body().getAccountNumber());
                        tvBankName.setText(response.body().getBankName());
                        tvBranchName.setText(response.body().getBankBranch());
                        tvIfsc.setText(response.body().getIfsc());
                        tvDob.setText(response.body().getDob());
                        etPanCard.setText(response.body().getPanNumber());

                        PreferencesManager.getInstance(context).setProfilePic(response.body().getProfilePic());


                        Glide.with(context).load("http://crm.hmgreencity.com/"+response.body().getProfilePic()).
                                apply(RequestOptions.circleCropTransform())
                                .placeholder(R.drawable.user_icon)
                                .error(R.drawable.user_icon)
                                .into(profileImage);


                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {
                    showMessage("Server Issue");
                }
            }

            @Override
            public void onFailure(Call<ResponseAssociateProfile> call, Throwable t) {
                hideLoading();
            }
        });
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                profileFile = FileUtils.getFile(context, result.getUri());
                uploadFile(profileFile);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
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
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                try {
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        showMessage(response.body().getMessage());
                        PreferencesManager.getInstance(context).setProfilePic(response.body().getProfilePic());
                Glide.with(context).load("http://crm.hmgreencity.com/"+response.body().getProfilePic()).
                        apply(RequestOptions.circleCropTransform())
                        .placeholder(R.drawable.user_icon)
                        .error(R.drawable.user_icon)
                        .into(profileImage);
                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }
    @Override
    public void onCancelClick() {
    }

    @Override
    public void onPickResult(PickResult pickResult) {
        if (pickResult.getError() == null) {
            CropImage.activity(pickResult.getUri()).setCropShape(CropImageView.CropShape.RECTANGLE)
                    .setAspectRatio(1, 1)
                    .setFixAspectRatio(true)
                    .start(context);
        }
    }
    private void viewProfle() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        final View inflateForm = getLayoutInflater().inflate(R.layout.profile_picture_layout, null); // Get custom login form view.
        builder.setView(inflateForm);
        builder.setCancelable(true);
        builder.create();

        final android.app.AlertDialog dialog = builder.show();
        TouchImageView imageView=inflateForm.findViewById(R.id.iv_image_activity);
        Glide.with(context).load("http://crm.hmgreencity.com/"+PreferencesManager.getInstance(context).getProfilePic())
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

    private void changePassword() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
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


}
