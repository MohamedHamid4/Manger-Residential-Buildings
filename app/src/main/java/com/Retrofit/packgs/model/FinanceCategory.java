package com.Retrofit.packgs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FinanceCategory {
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("actions_count")
    @Expose
    String actions_count;
    @SerializedName("total")
    @Expose
    String total;

    public FinanceCategory(int id, String name, String actions_count, String total) {
        this.id = id;
        this.name = name;
        this.actions_count = actions_count;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActions_count() {
        return actions_count;
    }

    public void setActions_count(String actions_count) {
        this.actions_count = actions_count;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return name;
    }
}
