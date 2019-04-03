package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userPassManager {

        @FormUrlEncoded
        @POST("android_sms/encryptPass.php")
        public Call<Object> sendUserpass(@Field("mobile") String mobile
                                        ,@Field("pass")String pass);

}
