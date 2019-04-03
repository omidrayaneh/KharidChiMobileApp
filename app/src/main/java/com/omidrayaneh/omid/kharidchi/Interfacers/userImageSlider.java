package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userImageSlider {

    @POST("android_sms/getSliderImage.php")
    public Call<Object> getSlider();
}
