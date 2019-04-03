package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userAddress {
    @FormUrlEncoded
    @POST("android_sms/insert_address.php")
    public Call<Object> sendaddress(@Field("mobile")String mobile,
                                    @Field("town")String town,
                                    @Field("full_address")String fullAddress,
                                    @Field("phone")String phone);

}
