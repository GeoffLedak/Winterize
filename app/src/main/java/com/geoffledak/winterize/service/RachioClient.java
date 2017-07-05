package com.geoffledak.winterize.service;

import com.geoffledak.winterize.model.person.Info;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Geoff Ledak on 7/3/2017.
 */

public interface RachioClient {


    @GET("person/info")
    Call<Info> infoForPerson(@Header("Authorization") String token);





}
