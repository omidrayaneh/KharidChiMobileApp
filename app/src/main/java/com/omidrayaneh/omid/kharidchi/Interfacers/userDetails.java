package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userDetails {
    @FormUrlEncoded
    @POST("android_sms/return_details.php")
    public Call<Object> getDetails(@Field("details")String details);
}
