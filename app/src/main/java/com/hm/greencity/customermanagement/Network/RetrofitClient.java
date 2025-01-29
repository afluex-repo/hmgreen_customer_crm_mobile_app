package com.hm.greencity.customermanagement.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;
import com.hm.greencity.customermanagement.retrofit.ApiServices;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://crm.hmgreencity.com/";

    public static Retrofit getClient() {
        if (retrofit == null) {
            // Create logging interceptor for debugging requests
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);  // Log all request/response bodies
            // Create OkHttpClient with timeout and logging interceptor
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)  // Set read timeout to 30 seconds
                    .connectTimeout(30, TimeUnit.SECONDS)  // Set connection timeout to 30 seconds
                    .addInterceptor(loggingInterceptor);  // Add logging interceptor

            // Build Retrofit instance
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)  // Base URL for API
                    .addConverterFactory(GsonConverterFactory.create())  // Gson converter to handle JSON response
                    .client(httpClient.build())  // Use custom OkHttpClient with timeouts and logging
                    .build();
        }

        return retrofit;
    }

    // Method to get API services
    public static ApiServices getApiServices() {
        return getClient().create(ApiServices.class);
    }
}
