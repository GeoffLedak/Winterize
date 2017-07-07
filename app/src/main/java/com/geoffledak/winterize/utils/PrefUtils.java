package com.geoffledak.winterize.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Geoff Ledak on 7/6/2017.
 */

public class PrefUtils {

    public static void saveString(Context context, String key, String string) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(key, string).apply();
    }


    public static String getString(Context context, String key) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        if( preferences.contains( key ) )
            return preferences.getString(key, "");
        else
            return "";
    }


    public static void removeKey(Context context, String key) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        if( preferences.contains( key ) )
            preferences.edit().remove(key).apply();
    }


    public static boolean keyExists(Context context, String key) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.contains(key);
    }

}
