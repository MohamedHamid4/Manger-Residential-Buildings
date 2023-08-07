package com.Retrofit.packgs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponse <T> {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public  T data;

    @SerializedName("list")
    @Expose
    public  List<T> list;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status=" + status +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", list=" + list +
                '}';
    }
}
