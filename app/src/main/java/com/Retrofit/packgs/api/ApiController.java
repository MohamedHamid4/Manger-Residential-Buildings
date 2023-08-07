package com.Retrofit.packgs.api;

public class ApiController {

    private RetrofitRequest retrofitRequest;

    private static ApiController instance;

    public ApiController() {
        retrofitRequest = RetrofitSetting.getRetrofitInstance().create(RetrofitRequest.class);
    }

        public static synchronized ApiController getInstance() {
        if (instance == null) {
            instance = new ApiController();
        }
        return instance;
    }

    public RetrofitRequest getRetrofitRequest() {
        return retrofitRequest;
    }

}