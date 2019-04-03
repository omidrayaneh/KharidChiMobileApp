package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userPostReport {
    @FormUrlEncoded
    @POST("android_sms/insert_report.php")
    public Call<Object> sendreport(@Field("mobile")String mobile,
                                   @Field("report")String report);

}
