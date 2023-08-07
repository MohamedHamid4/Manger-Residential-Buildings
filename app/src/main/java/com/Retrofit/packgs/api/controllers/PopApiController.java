package com.Retrofit.packgs.api.controllers;

import com.Retrofit.packgs.interfaces.ListCalBack;
import com.Retrofit.packgs.interfaces.ProcessCallBack;
import com.Retrofit.packgs.model.Pop;

public class PopApiController {

    public void getUsers(ListCalBack<Pop> listCalBack) {
        Pop.getUsers(listCalBack);
    }

    public void deleteUser(Pop user, ProcessCallBack callback) {
        user.deleteUser(callback);
    }

    public void addUser(Pop user, ProcessCallBack callBack) {
        if (!user.name.isEmpty() && !user.email.isEmpty() && !user.mobile.isEmpty()
                && !user.nationalNumber.isEmpty() && !user.familyMembers.isEmpty() && user.imagesByteArray != null) {
            user.addUser(callBack);
        } else {
            callBack.onFailure("Enter required data!");
        }
    }

    public void update(Pop user , ProcessCallBack callback){
        if (user.name != null && user.mobile != null && user.nationalNumber != null && user.familyMembers != null && user.gender != null) {
            user.update(callback);
        }else {
            callback.onFailure("Enter required data!");
        }
    }

}
