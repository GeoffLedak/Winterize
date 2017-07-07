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




    // curl -X GET -H "Authorization: Bearer 599c4261-103d-4e9a-b5c4-06558c7fcbe9" https://api.rach.io/1/public/person/da0b7585-e3c2-42bc-887d-6e11dd209630

    // curl -X PUT -H "Content-Type: application/json" -H "Authorization: Bearer 8e600a4c-0027-4a9a-9bda-dc8d5c90350d" -d '{ "id" : "d3e99d27-25e4-47dd-b354-1db5a84c99d7", "duration" : 60 }' https://api.rach.io/1/public/zone/start





}
