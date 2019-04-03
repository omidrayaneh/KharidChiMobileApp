package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface getProfile {

    @FormUrlEncoded
    @POST("android_sms/getProfile.php")
    public Call<Object> getMobile(@Field("mobile")String mobile);
}
