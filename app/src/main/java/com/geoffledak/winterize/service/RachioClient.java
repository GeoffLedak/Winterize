package com.geoffledak.winterize.service;

import com.geoffledak.winterize.model.Info;
import com.geoffledak.winterize.model.InfoFull;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Geoff Ledak on 7/3/2017.
 */

public interface RachioClient {


    @GET("person/info")
    Call<Info> idForPerson(@Header("Authorization") String token);




    @GET("person/{personId}")
    Call<InfoFull> infoFullForPerson(@Header("Authorization") String token, @Path("personId") String personIdd);




    // curl -X GET -H "Authorization: Bearer 599c4261-103d-4e9a-b5c4-06558c7fcbe9" https://api.rach.io/1/public/person/da0b7585-e3c2-42bc-887d-6e11dd209630





}
