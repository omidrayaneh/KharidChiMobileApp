package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getCategory {

    //@FormUrlEncoded
    @GET("android_sms/getCategory.php")
    public Call<Object> get_category();
}
