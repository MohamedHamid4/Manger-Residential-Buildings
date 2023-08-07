package com.Retrofit.packgs.interfaces;

import java.util.List;

public interface ListCalBack <T>{
    void onSuccess (List<T> list);
    void onFailure (String message);
}
