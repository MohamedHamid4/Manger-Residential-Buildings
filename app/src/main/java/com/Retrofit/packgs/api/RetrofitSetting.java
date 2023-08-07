package com.Retrofit.packgs.api;
import com.Retrofit.packgs.prefs.AppShedPreferencesController;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSetting {

    private static Retrofit retrofit;
    private static final String BASE_URI = "https://towers.mr-dev.tech/api/";

    private RetrofitSetting() {
    }

    public static synchronized Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            //Builder DesignPattern
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URI)
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("Accept", "application/json");
                builder.addHeader("Content-Type", "application/json");
                builder.addHeader("Authorization", AppShedPreferencesController.getInstance().getToken());
                return chain.proceed(builder.build());
            }
        }).build();

        return client;
    }

}