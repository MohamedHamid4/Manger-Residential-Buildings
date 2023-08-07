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

public class AdMangment {
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("info")
    @Expose
    public String info;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("image_url")
    @Expose
    public String imageUrl;
    @SerializedName("tower_name")
    @Expose
    public String towerName;
    public String PUT = "PUT";

    public byte[] imagesByteArray;

    public AdMangment(String title, String info, String towerName, byte[] imageBytesArray) {
        this.title = title;
        this.info = info;
        this.id = id;
        this.imagesByteArray = imageBytesArray;
        this.towerName = towerName;
    }

    public AdMangment() {
    }

    public void createAdvertising(ProcessCallBack callBack) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imagesByteArray);
        MultipartBody.Part file = MultipartBody.Part.createFormData("image", "image-file", requestBody);
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("title", RequestBody.create(MediaType.parse("text/plain"), title));
        map.put("info", RequestBody.create(MediaType.parse("text/plain"), info));
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequest().addAdvertising(map, file);
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

    public static void getAdvertising(ListCalBack<AdMangment> listCalBack) {
        Call<BaseResponse<AdMangment>> call = ApiController.getInstance().getRetrofitRequest().getAdvertising();
        call.enqueue(new Callback<BaseResponse<AdMangment>>() {
            @Override
            public void onResponse(Call<BaseResponse<AdMangment>> call, Response<BaseResponse<AdMangment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listCalBack.onSuccess(response.body().list);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<AdMangment>> call, Throwable t) {

            }
        });
    }

    public void deleteAdvertising(ProcessCallBack callback) {
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequest().deleteAdvertising(Integer.parseInt(id.toString()));
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

    public void updateAdvertising(ProcessCallBack callback) {
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("name", RequestBody.create(MediaType.parse("text/plain"), title));
        map.put("mobile", RequestBody.create(MediaType.parse("text/plain"), info));
        map.put("_method", RequestBody.create(MediaType.parse("text/plain"), PUT));
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequest().updateAdvertising(id, map);
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
        return title;
    }

}