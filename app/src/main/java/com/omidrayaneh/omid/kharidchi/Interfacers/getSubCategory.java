package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface getSubCategory {
    @FormUrlEncoded
    @POST("android_sms/getSubCategory.php")
    public Call<Object> getCategory(@Field("id")int id);
}
