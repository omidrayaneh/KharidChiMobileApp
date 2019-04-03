package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userReport {
    @FormUrlEncoded
    @POST("android_sms/report_invoice.php")
    public Call<Object> reportInvoice(@Field("mobile") String mobile);
}
