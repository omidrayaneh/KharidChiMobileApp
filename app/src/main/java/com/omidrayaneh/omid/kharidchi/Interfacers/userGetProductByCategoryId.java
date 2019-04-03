package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userGetProductByCategoryId {
    @FormUrlEncoded()
    @POST("android_sms/search_category.php")
    public Call<Object> sendValue(@Field("value")String value);
}
