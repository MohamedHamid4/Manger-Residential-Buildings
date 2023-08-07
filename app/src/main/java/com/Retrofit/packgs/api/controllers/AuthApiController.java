package com.Retrofit.packgs.api.controllers;
import com.Retrofit.packgs.api.ApiController;
import com.Retrofit.packgs.model.Admin;
import com.Retrofit.packgs.model.BaseResponse;
import com.Retrofit.packgs.api.AppController;
import com.Retrofit.packgs.interfaces.ProcessCallBack;
import com.Retrofit.packgs.prefs.AppShedPreferencesController;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthApiController {

    public void login(Admin admin, ProcessCallBack callBack) {
        if (!admin.email.isEmpty() && !admin.password.isEmpty()) {
            admin.login(callBack);
        }else {
            callBack.onFailure("Enter required data!");
        }
    }

    public void logout(ProcessCallBack callBack) {
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequest().logout();
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful() || response.code() == 401 ){
                    AppShedPreferencesController.getInstance().clear();
                    String message = response.body() != null ? response.body().message : "Logged out Successfully";
                    if (response.body() != null){
                        callBack.onSuccess(message);
                    }
                }else {
                    callBack.onFailure("Failed,Something went wrong!!");
                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callBack.onFailure("onFailure: Failed");
            }
        });
    }


}