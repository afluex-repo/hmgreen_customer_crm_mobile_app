package com.hm.greencity.customermanagement.Network;
import com.hm.greencity.customermanagement.models.chatModel.FetchMessageRequest;
import com.hm.greencity.customermanagement.models.chatModel.RequestChat;
import com.hm.greencity.customermanagement.models.chatModel.ResponseChat;
import com.hm.greencity.customermanagement.models.chatModel.getResponseChat;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ChatService {

//    @POST("WebAPI/SaveQuery")
//    Call<ResponseChat> sendMessage(@Body RequestChat messageRequest);

    @POST("WebAPI/SaveQuery")
    @Multipart
    Call<ResponseChat> sendMessage(
            @Part("Fk_UserId") okhttp3.RequestBody fkUserId,
            @Part("Title") okhttp3.RequestBody title,
            @Part("Description") okhttp3.RequestBody description,
            @Part MultipartBody.Part queryImage // Use MultipartBody.Part for image part
    );

    @POST("WebAPI/GetQueryList") // Replace with your actual endpoint
    Call<getResponseChat> fetchMessages(@Body FetchMessageRequest request);



}
