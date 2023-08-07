
package com.Retrofit.packgs.model;

import android.util.Log;

import androidx.annotation.NonNull;

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

public class Pop {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("national_number")
    @Expose
    public String nationalNumber;
    @SerializedName("family_members")
    @Expose
    public String familyMembers;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("image_url")
    @Expose
    public String imageUrl;
    @SerializedName("tower_name")
    @Expose
    public String towerName;
    @SerializedName("_method")
    @Expose
    public String PUT = "PUT";
    public byte[] imagesByteArray;


    public static void getUsers(ListCalBack<Pop> listCalBack) {
        Call<BaseResponse<Pop>> call = ApiController.getInstance().getRetrofitRequest().getUsers();
        call.enqueue(new Callback<BaseResponse<Pop>>() {
            @Override
            public void onResponse(Call<BaseResponse<Pop>> call, Response<BaseResponse<Pop>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listCalBack.onSuccess(response.body().list);

                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Pop>> call, Throwable t) {

            }
        });
    }

    public void deleteUser(ProcessCallBack processCallBack) {
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequest().deleteUser(id);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    processCallBack.onSuccess(response.body().message);
                } else {
                    try {
                        String error = new String(response.errorBody().bytes(), StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(error);
                        processCallBack.onFailure(jsonObject.getString("message"));

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



    public void addUser(ProcessCallBack callback) {

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imagesByteArray);
        MultipartBody.Part file = MultipartBody.Part.createFormData("image", "image-file", requestBody);
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("name", RequestBody.create(MediaType.parse("text/plain"), name));
        map.put("email", RequestBody.create(MediaType.parse("text/plain"), email));
        map.put("mobile", RequestBody.create(MediaType.parse("text/plain"), mobile));
        map.put("national_number", RequestBody.create(MediaType.parse("text/plain"), nationalNumber));
        map.put("family_members", RequestBody.create(MediaType.parse("text/plain"), familyMembers));
        map.put("gender", RequestBody.create(MediaType.parse("text/plain"), gender));
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequest().addUser(map, file);

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
                        Log.d("TAG", "onResponse: "+jsonObject.getString("message"));
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
        map.put("_method", RequestBody.create(MediaType.parse("text/plain"), PUT));
        map.put("name", RequestBody.create(MediaType.parse("text/plain"), name));
        map.put("mobile", RequestBody.create(MediaType.parse("text/plain"), mobile));
        map.put("national_number", RequestBody.create(MediaType.parse("text/plain"), nationalNumber));
        map.put("family_members", RequestBody.create(MediaType.parse("text/plain"), familyMembers));
        map.put("gender", RequestBody.create(MediaType.parse("text/plain"), gender));
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequest().updateUser(id, map);
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

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
