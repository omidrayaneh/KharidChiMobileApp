package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userTown {
    @FormUrlEncoded
    @POST("android_sms/return_town.php")
    public Call<Object> getTown( @Field("state_name")String state);
}
