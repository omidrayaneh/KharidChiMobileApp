package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userPhotos {

    @FormUrlEncoded
    @POST("android_sms/getProductImages.php")
    public Call<Object> getphotos(@Field("id")int id);
}

