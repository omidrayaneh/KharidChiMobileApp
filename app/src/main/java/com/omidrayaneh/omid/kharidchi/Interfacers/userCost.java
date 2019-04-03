package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userCost {
    //@FormUrlEncoded
    @POST("android_sms/getCost.php")
    public Call<Object> requestcost();
}
