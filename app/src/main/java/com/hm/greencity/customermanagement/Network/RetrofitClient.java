package com.hm.greencity.customermanagement.Network;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;
import com.hm.greencity.customermanagement.retrofit.ApiServices;


public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://crm.hmgreencity.com/";

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .readTimeout(290, TimeUnit.SECONDS)
                    .connectTimeout(290, TimeUnit.SECONDS);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    public static ApiServices getApiServices() {
        return getClient().create(ApiServices.class);
    }
}
