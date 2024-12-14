package com.hm.greencity.customermanagement.Network;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.models.DeleteBusinessCard.ReqDeleteBusinesscard;
import com.hm.greencity.customermanagement.models.DeleteBusinessCard.ResDeleteBusinesscard;
import com.hm.greencity.customermanagement.models.EmiTracking.ReqEmi;
import com.hm.greencity.customermanagement.models.EmiTracking.ResEmi;
import com.hm.greencity.customermanagement.models.GetSite.ReqGetSite;
import com.hm.greencity.customermanagement.models.GetSite.ResGetSite;
import com.hm.greencity.customermanagement.models.Site.ResSite;
import com.hm.greencity.customermanagement.models.Team.ResTeam;
import com.hm.greencity.customermanagement.models.chatModel.FetchMessageRequest;
import com.hm.greencity.customermanagement.models.chatModel.ResponseChat;
import com.hm.greencity.customermanagement.models.chatModel.getResponseChat;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ChatService {

    @POST("WebAPI/SaveQuery")
    @Multipart
    Call<ResponseChat> sendMessage(
            @Part("Fk_UserId") okhttp3.RequestBody fkUserId,
            @Part("Title") okhttp3.RequestBody title,
            @Part("Description") okhttp3.RequestBody description,
            @Part MultipartBody.Part queryImage

    );

    @POST("WebAPI/GetQueryList")
    Call<getResponseChat> fetchMessages(@Body FetchMessageRequest request);

    @POST("WebAPI/GetVisitRequest")
    Call<ResGetSite>getvisitorlist(@Body ReqGetSite reqGetSite);

    @POST("WebAPI/sitelist")
    Call<ResSite>site();

    @POST("WebAPI/GetTeamDeatils")
    Call<ResTeam>team();

    @POST("WebAPI/DeleteBussinessCard")
    Call<ResDeleteBusinesscard>deletecard(@Body ReqDeleteBusinesscard reqDeleteBusinesscard);


    @POST("WebAPI/GetEMILedger")
    Call<ResEmi>emitracking(@Body ReqEmi reqEmi);


}
