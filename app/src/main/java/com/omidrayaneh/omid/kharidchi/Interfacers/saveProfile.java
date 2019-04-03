package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface saveProfile {
    @FormUrlEncoded
    @POST("android_sms/saveProfile.php")
    public Call<Object> saveaddress(@Field("name")String name,
                                    @Field("family")String family,
                                    @Field("mobile")String mobile,
                                    @Field("address")String address,
                                    @Field("phone")String phone);

}
