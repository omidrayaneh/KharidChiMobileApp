package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.GET;

public interface userMessage {
    @GET("android_sms/getMessage.php")
    public Call<Object> getMessage();
}
