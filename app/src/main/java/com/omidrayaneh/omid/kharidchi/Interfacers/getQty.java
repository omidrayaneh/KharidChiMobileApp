package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface getQty {
    @FormUrlEncoded
    @POST("android_sms/getQty.php")
    public Call<Object> getQtyCount(@Field("name")String name);
}

