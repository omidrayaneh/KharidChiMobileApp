package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userVerifyOtp {
    @FormUrlEncoded
    @POST("android_sms/verify_otp.php")
    public Call<Object> sendVerifiOtp(@Field("mobile") String mobile
            , @Field("code")String code);
}
