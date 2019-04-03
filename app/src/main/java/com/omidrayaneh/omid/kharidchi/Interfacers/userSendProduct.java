package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userSendProduct {

    @FormUrlEncoded
    @POST("android_sms/search_product.php")
    public Call<Object> sendProduct(@Field("value") String product);
}
