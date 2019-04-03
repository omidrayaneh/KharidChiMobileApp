package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userSingUp {

    @FormUrlEncoded
    @POST("android_sms/request.php")
    public Call<Object> sendSingup(@Field("name") String name,
                                    @Field("family")String family,
                                    @Field("mobile")String mobile,
                                    @Field("pass")String pass);
}
