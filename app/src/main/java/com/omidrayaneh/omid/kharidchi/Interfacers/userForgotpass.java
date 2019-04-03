package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userForgotpass {
    @FormUrlEncoded
    @POST("android_sms/verify_forgot_otp.php")
    public Call<Object> sendForgotpass(@Field("mobile") String mobile, @Field("code") String newCode);
}
