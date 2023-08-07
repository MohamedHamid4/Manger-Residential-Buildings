package com.Retrofit.packgs.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.Retrofit.packgs.api.AppController;
import com.Retrofit.packgs.model.Admin;


enum prefKeys {
    loggedIn, id, name, towerName, email, token
}

public class AppShedPreferencesController {

    public static AppShedPreferencesController instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public AppShedPreferencesController() {
        sharedPreferences = AppController.getInstance().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
    }

    public static AppShedPreferencesController getInstance() {
        if (instance == null) {
            instance = new AppShedPreferencesController();
        }
        return instance;
    }

    public String getToken() {
        return sharedPreferences.getString(prefKeys.token.name(), "");
    }

    public void save(Admin admin) {
        editor = sharedPreferences.edit();
        editor.putBoolean(prefKeys.loggedIn.name(), true);
        editor.putInt(prefKeys.id.name(), admin.id);
        editor.putString(prefKeys.name.name(), admin.name);
        editor.putString(prefKeys.email.name(), admin.email);
        editor.putString(prefKeys.towerName.name(), admin.towerName);
        editor.putString(prefKeys.token.name(), "Bearer "+ admin.token);
        editor.apply();

    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(prefKeys.loggedIn.name(), false);
    }

    public void clear() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


}
