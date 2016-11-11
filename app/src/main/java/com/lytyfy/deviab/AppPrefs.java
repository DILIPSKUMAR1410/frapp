package com.lytyfy.deviab;

/**
 * Created by sonu on 05/11/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPrefs {
    private static final String USER_PREFS = "LYTYFY_USER_PREFS";
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;
    private String user_name = "user_name_prefs";
    private String user_id = "user_id_prefs";
    private String token = "token_prefs";


    public AppPrefs(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public int getUser_id() {
        return appSharedPrefs.getInt(user_id, 0);
    }

    public void setUser_id(int _user_id) {
        prefsEditor.putInt(user_id, _user_id).commit();
    }

    public String getUser_name() {
        return appSharedPrefs.getString(user_name, "unkown");
    }

    public void setUser_name(String _user_name) {
        prefsEditor.putString(user_name, _user_name).commit();
    }

    public String getToken() {
        return appSharedPrefs.getString(token, "unkown");
    }

    public void setToken(String _token) {
        prefsEditor.putString(token, _token).commit();
    }
    public void clearToken(){
        this.prefsEditor.clear();
        this.prefsEditor.commit();
    }
}