package com.hm.greencity.customermanagement.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.Activity.AssociateContaner;
import com.hm.greencity.customermanagement.Activity.HomeActivity;
import com.hm.greencity.customermanagement.Activity.HomeTestActivity;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.models.ResponseLogin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    public static final String TAG = "LoginActivity";
    private Button button;
    private EditText userName, passWord;
    private ImageView imageView;
    private TextView textView;

    private ImageView userPhoto;
    private EditText fullName, designation, email, phoneNumber, address, username, password, confirmPassword, search;

    SharedPreferences preferences;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        this.textView = (TextView) findViewById(R.id.for_forgot_password);
        this.imageView = (ImageView) this.findViewById(R.id.login_photo);
        this.userName = (EditText) this.findViewById(R.id.login_username);
        this.passWord = (EditText) this.findViewById(R.id.login_password);
        this.button = (Button) this.findViewById(R.id.login_button);

        builder = new AlertDialog.Builder(LoginActivity.this);

        boolean isLoggedIn = PreferencesManager.getInstance(context).getIsFirstIntro();
        if (isLoggedIn) {

            goToHome();
            //===================================================================================================================================

        } else {


            //Do not get true value login.xml file then login using database
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!userName.getText().toString().isEmpty() && !passWord.getText().toString().isEmpty())
                    {
                        showLoading();
                        hideKeyboard();
                        JsonObject loginObj = new JsonObject();
                        loginObj.addProperty("LoginId", userName.getText().toString().trim());
                        loginObj.addProperty("Password", passWord.getText().toString().trim());
//                    Log.e("valueToSend",loginObj+"");
                        Call<ResponseLogin> responseLoginCall = apiServices.getLogin(loginObj);

                        responseLoginCall.enqueue(new Callback<ResponseLogin>() {
                            @Override
                            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                                hideLoading();
                                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                                    PreferencesManager.getInstance(context).setLoginId(response.body().getLoginId());
                                    PreferencesManager.getInstance(context).setUserId(response.body().getCustomerID());
                                    PreferencesManager.getInstance(context).setFull_Name(response.body().getFullName());
                                    PreferencesManager.getInstance(context).setUserType(response.body().getUserType());
                                    PreferencesManager.getInstance(context).setCustomerID(response.body().getCustomerID());
                                    PreferencesManager.getInstance(context).setProfilePic(response.body().getProfilePic());
                                    PreferencesManager.getInstance(context).setIsFirstIntro(true);
                                    if (response.body().getUserType().equalsIgnoreCase("Customer")) {
                                        showMessage(response.body().getMessage());

                                        goToActivity(context, HomeTestActivity.class, null);
//                                        goToActivity(context, HomeActivity.class, null);
                                    } else if (response.body().getUserType().equalsIgnoreCase("Trad Associate")) {
                                        showMessage(response.body().getMessage());

                                        goToActivity(context, AssociateContaner.class, null);
                                    }else showMessage("You Are Not Authorised to Login!");
                                }
                                else {
                                    showMessage(response.body().getMessage());
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLogin> call, Throwable throwable) {
                                hideLoading();
                                showMessage(throwable.getMessage().toString());
                            }
                        });

                    }
                    else
                    {
                        showMessage("Enter details");

                    }

//                                PreferencesManager.getInstance(context).setUserId();
//
//                                //===============================================| Writes SharedPreferences |===========================================
//                                SharedPreferences.Editor editor = preferences.edit(); //Write
//                                editor.putBoolean("isLoggedIn", true); //key = isLoggedIn and value="true"
//                                editor.putString("userName", userName.getText().toString());
//                                editor.putString("userPass", passWord.getText().toString());
//                                editor.apply();
//                                editor.commit();
//                                //======================================================================================================================
//
//                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                                startActivity(intent);
//                                Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                }
            });


        }

        //====================================================| To Registration/User |====================================================
        //Need to registration in text-view then display progress
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ProgressDialog progressBar = ProgressDialog.show(LoginActivity.this, "Registration", "Please wait to create a new account");
//                progressBar.setCancelable(true);
//                Intent i = new Intent(getApplicationContext(), UsersActivity.class);
//                startActivity(i);

                customAlertDialog();

            }
        });

//        saveAdminImageToInternalStorage();

    }

    //====================================================| For Activity Starting and Closing |====================================================
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    //============================================================| SharedPreferences
    private void goToHome() {
//        Intent intent = new Intent(this, HomeActivity.class);
        Intent intent = new Intent(this, HomeTestActivity.class);
        startActivity(intent);
    }
    //Editor to edit value in xml file at the logging time
    /*public void login(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_LOGGED_IN,true); //key = isLoggedIn and value="true"
        editor.apply();
        //editor. remove();//Remove value using key
        ///editor.clear(); //Remove from login.xml file
        editor.commit();

        boolean isLoggedIn = sharedPreferences.getBoolean(IS_LOGGED_IN,false);
        Toast.makeText(this, "is xml file created? "+isLoggedIn, Toast.LENGTH_SHORT).show();
        login();
    }*/


    //=================================================| Save admin image to internal storage |===========================================//
    protected String saveAdminImageToInternalStorage() {
        File directory = new File(getFilesDir() + "/UsersPhoto/");
        directory.mkdir();
        File file = new File(directory, "img_admin.png");
        try {
            OutputStream output = new FileOutputStream(file);
            Bitmap b = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.hm_logo_new_final);
            b.compress(Bitmap.CompressFormat.PNG, 100, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }


    //Add data into database using alert dialog
    public void customAlertDialog1() {

        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Add User");
        final View inflateForm = getLayoutInflater().inflate(R.layout.user_alert_dialog, null); // Get custom login form view.
        builder.setView(inflateForm); // Set above view in alert dialog.
        builder.setCancelable(true);
//        builder.create();

        final AlertDialog dialog = builder.create(); // Because only AlertDialog has cancel method.
        dialog.show();
        this.userPhoto = (ImageView) inflateForm.findViewById(R.id.reg_passport);
        this.fullName = (EditText) inflateForm.findViewById(R.id.reg_full_name);
        this.designation = (EditText) inflateForm.findViewById(R.id.reg_designation);
        this.email = (EditText) inflateForm.findViewById(R.id.reg_email);
        this.phoneNumber = (EditText) inflateForm.findViewById(R.id.reg_phone_number);
        this.address = (EditText) inflateForm.findViewById(R.id.reg_address);
        this.username = (EditText) inflateForm.findViewById(R.id.reg_username);
        this.password = (EditText) inflateForm.findViewById(R.id.reg_password);
        this.confirmPassword = (EditText) inflateForm.findViewById(R.id.reg_confirm_password);
        final Button saveButton = (Button) inflateForm.findViewById(R.id.reg_save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    addData(userPhoto, fullName, designation, email, phoneNumber, address, username, password, confirmPassword); //Adding data into database

                    //Displaying data from database and sets listView
//                    if(customAdapter==null) {
//                        customAdapter = customAdapter = new UsersAdapter(UsersActivity.this, arrayList, usersService);
//                        listView.setAdapter(customAdapter);
//                    }
//                    customAdapter.arrayList = (ArrayList) usersService.getUsers();
//                    ((BaseAdapter)listView.getAdapter()).notifyDataSetChanged(); //Refresh listView

                    dialog.cancel(); // Close Alert Dialog.
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //1. Load image from gallery
//        userPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
//            }
//        });

    }


    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();

    }

    public void customAlertDialog() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.forgot_password_layput, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);
//        deleteDialogView.findViewById(R.id.yes).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //your business logic
//                deleteDialog.dismiss();
//            }
//        });
//        deleteDialogView.findViewById(R.id.no).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteDialog.dismiss();
//            }
//        });

        deleteDialog.show();
    }


//    http://crm.hmgreencity.com/
//    DHMGREENCRM
//    123456
}
