package com.hm.greencity.customermanagement.Activity;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!PreferencesManager.getInstance(getApplicationContext()).getUserType().equalsIgnoreCase("")) {

                    if (PreferencesManager.getInstance(getApplicationContext()).getUserType().equalsIgnoreCase("Trad Associate")) {
                        // if ()
                        // goToActivityWithFinish(context, ContainerActivity.class, null);
                        // goToActivityWithFinish(context, ContainerActivity.class, null);
                        Intent I = new Intent(MainActivity.this, AssociateContaner.class);
                        startActivity(I);
                        finish();
                    } else if (PreferencesManager.getInstance(getApplicationContext()).getUserType().equalsIgnoreCase("Customer")) {
                        // goToActivityWithFinish(context, ContainerActivity.class, null);
                        Intent I = new Intent(MainActivity.this, HomeTestActivity.class);
                        startActivity(I);
                        finish();

                    }

                } else {

                    Intent I = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(I);
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);
    }

//
//    ***For Login API***
//
//            -request POST 'http://demo1.afluex.com/webapi/Login' \
//            --form 'LoginId="HMCRM192000440"' \
//            --form 'Password="12345"'                             Customer: 'LoginId="HMCRM192000440"' 'Password="12345"'
    //--form 'LoginId="DHM000023"' \                                Associate: 'LoginId="DHM000023"'      'Password="123456"'
    // --form 'Password="123456"'
//            ***For DueInstallment API***
//DueInstallmentList
//    request POST 'http://demo1.afluex.com/webapi/Dueinstalmentlist' \
//            --form 'LoginId="HMCRM192000440"'
//
//
//            ***For DashBoardAPI***
//
//    request POST 'http://demo1.afluex.com/webapi/DashBoard' \
//            --form 'LoginId="HMCRM192000440"'
//
//            =====================================
//    From: Mona (DESKTOP-FD5CI8D/192.168.29.52/Afluex01-<199dbb26f45db623>)
//    at Mon Jan 03 16:10:01 2022
//            -------------------------------------
//    Change Password
//
//    request POST 'http://demo1.afluex.com/webapi/ChnagePassword' \
//            --form 'OldPassword="123456"' \
//            --form 'NewPassword="12345"' \
//            --form 'CustomerID="96"'
//
//            =====================================
//    From: Mona (DESKTOP-FD5CI8D/192.168.29.52/Afluex01-<199dbb26f45db623>)
//    at Mon Jan 03 16:55:46 2022
//            -------------------------------------
//    Update Profile
//
//-request POST 'http://demo1.afluex.com/webapi/UpdateProfileDetails' \
//            --form 'CustomberId="96"' \
//            --form 'FirstName="Testing"' \
//            --form 'LastName="LastName"' \
//            --form 'Mobile="1234567890"' \
//            --form 'Email="RJ@GMAILCOM"' \
//            --form 'BankHolderName="Rj JISWA;"' \
//            --form 'AccountNo="212342343243"' \
//            --form 'IFSC="ICICIC"' \
//            --form 'BankBranch="nIRALA"' \
//            --form 'BankName="icic"' \
//            --form 'DOB="22/10/1996"' \
//            --form 'Address="gOMTI lAKO"' \
//            --form 'PanNumber="PAN123PN"'
//
//            =====================================
//    From: Mona (DESKTOP-FD5CI8D/192.168.29.52/Afluex01-<199dbb26f45db623>)
//    at Mon Jan 03 16:55:47 2022
//            -------------------------------------
//    get Profile Details
//
//
//
//    request POST 'demo1.afluex.com/webapi/ProfileDetails?Loginid=HMCRM192000440'
//
//            =====================================
//    From: Mona (DESKTOP-FD5CI8D/192.168.29.52/Afluex01-<199dbb26f45db623>)
//    at Mon Jan 03 16:56:07 2022
//            -------------------------------------
//    Ladger Details

//    08025106003

//
//    request POST 'demo1.afluex.com/webapi/LedgerReportList' \
//            --form 'BookingNumber="HMBK68624384"' \
//            --form 'LoginId="HMCRM192000440"' \
//            --form 'SiteID=""' \
//            --form 'PhaseID=""' \
//            --form 'BlockID=""' \
//            --form 'PlotNumber=""'


//    Booking List
//
//    request POST 'http://demo1.afluex.com/webapi/CustomerBookingList' \
//            --form 'LoginID="HMCRM192000440"' \
//            --form 'SiteID=""' \
//            --form 'PhaseID=""' \
//            --form 'BlockID=""' \
//            --form 'PlotNumber=""' \
//            --form 'BookingNumber="HMBK92896339"' \
//            --form 'ToDate="01/02/2022"' \
//            --form 'FromDate="01/02/2016"'


    //    http://crm.hmgreencity.com/
//    DHMGREENCRM
//    123456

//
//    HMCRM1717243179
//    984123
//    http://crm.hmgreencity.com/Home/Login


//
//
//    -request POST 'http://demo1.afluex.com/webapi/DueInstallmentList' \
//            --form 'CustomerID="96"' \
//            --form 'BookingNumber=""' \
//            --form 'FromDate=""' \
//            --form 'ToDate=""' \
//            --form 'SiteID=""' \
//            --form 'PhaseID=""' \
//            --form 'BlockID=""' \
//            --form 'PlotNumber=""'
//
//            =====================================
//    From: Mona (DESKTOP-FD5CI8D/192.168.29.52/Afluex01-<199dbb26f45db623>)
//    at Tue Jan 04 18:57:58 2022
//            -------------------------------------
//    For Site List ----------
//
//
//    request POST 'http://demo1.afluex.com/webapi/sitelist?Loginid=HMCRM192000440'
    // http://demo1.afluex.com/webapi/AssociateDashBoard' \
    //   --form 'CustomerID="24"'

    // http://demo1.afluex.com/webapi/AssociateDueInstallement' \
    //       --form 'CustomerID="24"'

    //request POST 'http://demo1.afluex.com/webapi/AssociateGetProfile' \
    //      --form 'LoginID="DHM000023"'

    //request POST 'http://demo1.afluex.com/webapi/AssociateUpdateProfile' \
    //--form 'CustomerID="24"' \
    //--form 'FirstName="Testing Vikarma"' \
    //--form 'LastName="Singh"' \
    //--form 'Mobile="8855220098"' \
    //--form 'EmailId="tesing@gmail.com"' \
    //--form 'AccountHolderName="Tefing"' \
    //--form 'AccountNumber="45345345345345"' \
    //--form 'BankName="ICICI"' \
    //--form 'BankBranch="Niralanagar"' \
    //--form 'IFSC="ICIC5345O"' \
    //--form 'ProfilePicture=""' \
    //--form 'Address="LKo"' \
    //--form 'PanNumber="ert464566"' \
    //--form 'DoB="22/1/2202"'

    //request POST 'http://demo1.afluex.com/webapi/AssociateGetProfile' \
    //    --form 'LoginID="DHM000023"'

}
