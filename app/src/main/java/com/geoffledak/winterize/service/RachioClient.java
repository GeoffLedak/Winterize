package com.geoffledak.winterize.service;

import com.geoffledak.winterize.model.Info;
import com.geoffledak.winterize.model.InfoFull;
import com.geoffledak.winterize.model.RunZone;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Geoff Ledak on 7/3/2017.
 */

public interface RachioClient {

    @GET("person/info")
    Call<Info> idForPerson(@Header("Authorization") String token);

    @GET("person/{personId}")
    Call<InfoFull> infoFullForPerson(@Header("Authorization") String token, @Path("personId") String personIdd);

    @PUT("device/on")
    Call<String> turnDeviceOn(@Header("Authorization") String token, @Body Info info);

    @PUT("device/off")
    Call<String> turnDeviceOff(@Header("Authorization") String token, @Body Info info);

    @PUT("zone/start")
    Call<String> turnZoneOn(@Header("Authorization") String token, @Body RunZone zoneInfo);
}
