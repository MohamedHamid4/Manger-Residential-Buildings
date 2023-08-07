package com.Retrofit.packgs.model;


import android.util.Log;

import com.Retrofit.packgs.api.ApiController;
import com.Retrofit.packgs.api.AppController;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.Retrofit.packgs.interfaces.ProcessCallBack;
import com.Retrofit.packgs.prefs.AppShedPreferencesController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("tower_name")
    @Expose
    public String towerName;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("token_type")
    @Expose
    public String tokenType;
    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;

    public String password;


    public void login(ProcessCallBack callBack) {
        Call<BaseResponse<Admin>> call = ApiController.getInstance().getRetrofitRequest().login(email, password);
        call.enqueue(new Callback<BaseResponse<Admin>>() {
            @Override
            public void onResponse(Call<BaseResponse<Admin>> call, Response<BaseResponse<Admin>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AppShedPreferencesController.getInstance().save(response.body().data);
                    callBack.onSuccess(response.body().message);
                } else {
                    try {
                        String error = new String(response.errorBody().bytes(), StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(error);
                        Log.e("Retrofit-API", "onResponse: " + error);
                        Log.e("Retrofit-API", "onResponse: " + jsonObject.getString("message"));
                        callBack.onFailure(jsonObject.getString("message"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Admin>> call, Throwable t) {
                callBack.onFailure("Something went wrong");
            }
        });
    }

}