package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userSendInvoice {
    @FormUrlEncoded
    @POST("android_sms/insert_invoice.php")
    public Call<Object> sendUserInvoice(@Field("userMobile") String mobile);

}
