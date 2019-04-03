package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userNewPass {
    @FormUrlEncoded
    @POST("android_sms/add_new_pass.php")
    public Call<Object> sendNewPass(@Field("mobile") String mobile,
                                       @Field("pass") String pass);
}
