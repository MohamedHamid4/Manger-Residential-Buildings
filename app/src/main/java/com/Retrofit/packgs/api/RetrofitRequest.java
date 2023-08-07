package com.Retrofit.packgs.api;
import com.Retrofit.packgs.model.AdMangment;
import com.Retrofit.packgs.model.Admin;
import com.Retrofit.packgs.model.BaseResponse;
import com.Retrofit.packgs.model.CategoriesResonse;
import com.Retrofit.packgs.model.Employee;
import com.Retrofit.packgs.model.OperationsResponse;
import com.Retrofit.packgs.model.Pop;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface RetrofitRequest {

    /*******     *****************    **********
            LOGIN & LOGOUT END POINTS
     *******    ******************    *********/
    @FormUrlEncoded
    @POST("auth/login")
    Call<BaseResponse<Admin>> login(@Field("email") String email, @Field("password") String password);

    @GET("auth/logout")
    Call<BaseResponse> logout();

    /*******     *****************    **********
                EMPLOYEES END POINTS
     *******    ******************    *********/
    @Multipart
    @POST("employees")
    Call<BaseResponse> createEmployee(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part image);

    @GET("employees")
    Call<BaseResponse<Employee>> getEmployee();

    @DELETE("employees/{id}")
    Call<BaseResponse> deleteEmployee(@Path("id") String id);

    @Multipart
    @POST("employees/{id}")
    Call<BaseResponse> updateEmployee(@Path("id") int id, @PartMap Map<String, RequestBody> params);

    /*******     *****************    **********
                    USER END POINTS
     *******     *****************    **********/
    @GET("users")
    Call<BaseResponse<Pop>> getUsers();

    @DELETE("users/{id}")
    Call<BaseResponse> deleteUser(@Path("id") int id);

    @Multipart
    @POST("users")
    Call<BaseResponse> addUser(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part image);


    @Multipart
    @POST("users/{id}")
    Call<BaseResponse> updateUser(@Path("id") int id, @PartMap Map<String, RequestBody> params);

    /*******     *****************    **********
                CATEGORIES END POINTS
     *******    ******************    *********/
    @GET("categories")
    Call<CategoriesResonse>  getCategories();

    /*******     *****************    **********
                 OPERATIONS END POINTS
     *******    ******************    *********/
    @GET("operations")
    Call<OperationsResponse> getOperations();

    @FormUrlEncoded
    @POST("operations")
    Call<BaseResponse> addOperation(@FieldMap Map<String,String> map);

    @DELETE("operations/{id}")
    Call<BaseResponse> deleteOperation(@Path("id") int id);

    /*******     *****************    **********
                 AD MANGMENT END POINTS
     *******    ******************    *********/
    @Multipart
    @POST("advertisements")
    Call<BaseResponse> addAdvertising(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part image);

    @GET("advertisements")
    Call<BaseResponse<AdMangment>> getAdvertising();

    @Multipart
    @POST("advertisements/{id}")
    Call<BaseResponse> updateAdvertising(@Path("id") int id , @PartMap Map<String, RequestBody> params);

    @DELETE("advertisements/{id}")
    Call<BaseResponse> deleteAdvertising(@Path("id") int id);
}