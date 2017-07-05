package com.geoffledak.winterize.utils;

import android.content.Context;

import com.geoffledak.winterize.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Geoff Ledak on 7/5/2017.
 */

public class APIUtils {

    public static Retrofit buildRetrofit(Context context) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.api_base_url))
                .addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

    public static String buildTokenString(Context context, String token) {

        return context.getResources().getString(R.string.api_auth_prefix) + " " + token;
    }

}
