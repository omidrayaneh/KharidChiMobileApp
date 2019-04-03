package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userPass {
    @FormUrlEncoded
    @POST("android_sms/forgot_pass.php")
    public Call<Object> sendForgotpass(@Field("mobile") String mobile);
}
