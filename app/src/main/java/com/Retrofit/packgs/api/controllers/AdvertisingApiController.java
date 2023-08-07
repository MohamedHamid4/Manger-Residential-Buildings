package com.Retrofit.packgs.api.controllers;
import com.Retrofit.packgs.interfaces.ListCalBack;
import com.Retrofit.packgs.interfaces.ProcessCallBack;
import com.Retrofit.packgs.model.AdMangment;

public class AdvertisingApiController {

    public void createAdvertising(AdMangment adMangment, ProcessCallBack callBack) {
        if (!adMangment.title.isEmpty() && !adMangment.info.isEmpty() && adMangment.imagesByteArray != null){
            adMangment.createAdvertising(callBack);
        }else {
            callBack.onFailure("Enter required data!");
        }
    }

    public void getAdvertising(ListCalBack<AdMangment> listCalBack){
        AdMangment.getAdvertising(listCalBack);
    }

    public void deleteAdvertising(AdMangment adMangment , ProcessCallBack callback ){
        adMangment.deleteAdvertising(callback);
    }


    public void updateAdvertising(AdMangment adMangment , ProcessCallBack callback){
        if (adMangment.title != null && adMangment.info != null){
            adMangment.updateAdvertising(callback);
        }else {
            callback.onFailure("Enter required data!");
        }
    }
}