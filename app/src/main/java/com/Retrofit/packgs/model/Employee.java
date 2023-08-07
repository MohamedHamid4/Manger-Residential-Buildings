package com.Retrofit.packgs.model;

import com.Retrofit.packgs.api.ApiController;
import com.Retrofit.packgs.interfaces.ListCalBack;
import com.Retrofit.packgs.interfaces.ProcessCallBack;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Employee {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("national_number")
    @Expose
    public String nationalNumber;
    @SerializedName("active")
    @Expose
    public Boolean active;
    @SerializedName("image_url")
    @Expose
    public String imageUrl;
    @SerializedName("_method")
    @Expose
    public String PUT = "PUT";

    public byte[] imagesByteArray;


    public void createEmployee(ProcessCallBack callBack) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imagesByteArray);
        MultipartBody.Part file = MultipartBody.Part.createFormData("image", "image-file", requestBody);
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("name", RequestBody.create(MediaType.parse("text/plain"), name));
        map.put("mobile", RequestBody.create(MediaType.parse("text/plain"), mobile));
        map.put("national_number", RequestBody.create(MediaType.parse("text/plain"), nationalNumber));
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequest().createEmployee(map, file);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onSuccess(response.body().message);
                } else {
                    try {
                        String error = new String(response.errorBody().bytes(), StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(error);
                        callBack.onFailure(jsonObject.getString("message"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });

    }

    public static void getEmployee(ListCalBack<Employee> listCalBack) {
        Call<BaseResponse<Employee>> call = ApiController.getInstance().getRetrofitRequest().getEmployee();
        call.enqueue(new Callback<BaseResponse<Employee>>() {
            @Override
            public void onResponse(Call<BaseResponse<Employee>> call, Response<BaseResponse<Employee>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listCalBack.onSuccess(response.body().list);

                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Employee>> call, Throwable t) {

            }
        });
    }

    public void delete(ProcessCallBack callback) {
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequest().deleteEmployee(id.toString());
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().message);
                } else {
                    try {
                        String error = new String(response.errorBody().bytes(), StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(error);
                        callback.onFailure(jsonObject.getString("message"));
                        callback.onFailure("");
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }


    public void update(ProcessCallBack callback) {

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("name", RequestBody.create(MediaType.parse("text/plain"), name));
        map.put("mobile", RequestBody.create(MediaType.parse("text/plain"), mobile));
        map.put("national_number", RequestBody.create(MediaType.parse("text/plain"), nationalNumber));
        map.put("_method", RequestBody.create(MediaType.parse("text/plain"), PUT));
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequest().updateEmployee(id, map);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().message);
                } else {
                    try {
                        String error = new String(response.errorBody().bytes(), StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(error);
                        callback.onFailure(jsonObject.getString("message"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callback.onFailure("");
            }
        });
    }

    @Override
    public String toString() {
        return name;
    }
}
