package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface userState {
    //@FormUrlEncoded
    @GET("android_sms/return_state.php")
    public Call<Object> getState();
}
