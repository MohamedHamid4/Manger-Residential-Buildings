package com.Retrofit.packgs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoriesResonse {
    @SerializedName("status")
    @Expose
    boolean status;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("list")
    @Expose
    ArrayList<FinanceCategory> financeCategories;

    public CategoriesResonse(boolean status, String message, ArrayList<FinanceCategory> financeCategories) {
        this.status = status;
        this.message = message;
        this.financeCategories = financeCategories;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<FinanceCategory> getFinanceCategories() {
        return financeCategories;
    }

    public void setFinanceCategories(ArrayList<FinanceCategory> financeCategories) {
        this.financeCategories = financeCategories;
    }
}