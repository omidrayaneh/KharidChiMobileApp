package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userAddressExist {

    @FormUrlEncoded
    @POST("android_sms/requestAddress.php")
    public Call<Object> requestAddress(@Field("mobile") String mobile);

}
