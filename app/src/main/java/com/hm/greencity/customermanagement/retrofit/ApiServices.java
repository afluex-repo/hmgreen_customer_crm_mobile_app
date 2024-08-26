package com.hm.greencity.customermanagement.retrofit;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.models.AssociateBookingList.ResponseAssociateBookingList;
import com.hm.greencity.customermanagement.models.AssociateBusinessReport.ResponseAssociateBusinessReport;
import com.hm.greencity.customermanagement.models.AssociateDashboard.ResponseAssociateDashboard;
import com.hm.greencity.customermanagement.models.AssociateDueInstallment.ResponseAssociateDueInstallment;
import com.hm.greencity.customermanagement.models.AssociateLedgerList;
import com.hm.greencity.customermanagement.models.AssociatePlotAvalibility.ResponseAssociatePlotAvalibility;
import com.hm.greencity.customermanagement.models.CustomerList.ResponseCustomerList;
import com.hm.greencity.customermanagement.models.CustomerMyProfile;
import com.hm.greencity.customermanagement.models.DueInstallment.ResponseDueInstallment;
import com.hm.greencity.customermanagement.models.DueInstallment.ResponsePloatBooking;
import com.hm.greencity.customermanagement.models.DueInstallmentDashBoard;
import com.hm.greencity.customermanagement.models.HomeActivityDashBoard;
import com.hm.greencity.customermanagement.models.LedgerReport.ResponseLedgerReport;
import com.hm.greencity.customermanagement.models.Notes.CreateNote.ResCreateNote;
import com.hm.greencity.customermanagement.models.Notes.DeleteNote.ResDeleteNote;
import com.hm.greencity.customermanagement.models.Notes.GetNote.ResGetNote;
import com.hm.greencity.customermanagement.models.ResponseAssociateProfile;
import com.hm.greencity.customermanagement.models.ResponseList.ResponseSite;
import com.hm.greencity.customermanagement.models.ResponseLogin;
import com.hm.greencity.customermanagement.models.ResponseStatusMessage;
import com.hm.greencity.customermanagement.models.ResponseUpdateProfile;
import com.hm.greencity.customermanagement.models.UpdateCustomerProfile;
import com.hm.greencity.customermanagement.models.UpdatePassword;
import com.hm.greencity.customermanagement.models.chatModel.ResponseChat;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ApiServices {

    @POST("webapi/login")
    Call<ResponseLogin> getLogin(@Body JsonObject login);

    @POST("webapi/DashBoard")
    Call<HomeActivityDashBoard> getDashboard(@Body JsonObject dashboard);

     @POST("webapi/Dueinstalmentlist")
    Call<DueInstallmentDashBoard> getDueInstallmentDashBoard(@Body JsonObject jsonObject);

    @Multipart
    @POST("api/UploadPhoto")
    Call<ResponseStatusMessage> uploadProfilePic(@Part("PK_UserId") RequestBody fk_UserId,
                                                 @Part() MultipartBody.Part file);

    @POST("webapi/ProfileDetails")
    Call<CustomerMyProfile> getProfile(@Body JsonObject jsonObject);

    @POST("webapi/UpdateProfileDetails")
    Call<UpdateCustomerProfile> updateProfile(@Body JsonObject jsonObject);

    @POST("webapi/ChangePassword")
    Call<UpdatePassword> updatePassword(@Body JsonObject jsonObject);


    @POST("webapi/FeedBack")
    Call<UpdatePassword> uploadFeedback(@Body JsonObject jsonObject);

    @POST("webapi/Dueinstalmentlist")
    Call<ResponseDueInstallment>Dueinstalmentlist (@Body JsonObject login);

    @POST("webapi/CustomerBookingList")
    Call<ResponsePloatBooking>CustomerBookingList (@Body JsonObject login);

    @POST("webapi/sitelist")
    Call<ResponseSite>sitelist (@Body JsonObject login);

    @POST("webapi/LedgerReportList")
    Call<ResponseLedgerReport>LedgerReportList(@Body JsonObject login);

    @POST("webapi/AssociateLedgerList")
    Call<AssociateLedgerList>AssociateLedgerReportList(@Body JsonObject login);

    @POST("webapi/AssociateDashBoard")
    Call<ResponseAssociateDashboard>AssociateDashBoard(@Body JsonObject login);

    @POST("webapi/AssociateDueInstallement")
    Call<ResponseAssociateDueInstallment>AssociateDueInstallement(@Body JsonObject login);

    @POST("webapi/AssociateGetProfile")
    Call<ResponseAssociateProfile>AssociateGetProfile(@Body JsonObject login);

    @POST("webapi/AssociateUpdateProfile")
    Call<ResponseUpdateProfile>AssociateUpdateProfile(@Body JsonObject login);

    @POST("webapi/AssociateCustomerList")
    Call<ResponseCustomerList>AssociateCustomerList(@Body JsonObject login);

    @POST("webapi/AssociateBookingList")
    Call<ResponseAssociateBookingList>AssociateBookingList(@Body JsonObject login);

    @POST("webapi/AssociateBusinessReport")
    Call<ResponseAssociateBusinessReport>AssociateBusinessReport(@Body JsonObject login);

    @POST("webapi/AssociatePlotAvailability")
    Call<ResponseAssociatePlotAvalibility>AssociatePlotAvailability(@Body JsonObject object);

    @POST("WebAPI/SaveQuery")
    Call<ResponseChat>sendChat(@Body JsonObject object);


    @POST("WebAPI/Notepad")
    Call<ResCreateNote>createNote(@Body JsonObject requestObject);

    @POST("WebAPI/GetNotepad")
    Call<ResGetNote>getNotes(@Body JsonObject requestObject);

    @POST("WebAPI/DeleteNotes")
    Call<ResDeleteNote>deleteNote(@Body JsonObject requestObject);

}