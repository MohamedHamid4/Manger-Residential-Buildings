package com.Retrofit.packgs.interfaces;


import com.Retrofit.packgs.enums.ActionType;

public interface TaskCallBack {
    void onActionCallBack(ActionType actionType , int positon , int id);
}
