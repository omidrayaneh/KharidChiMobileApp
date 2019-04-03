package com.omidrayaneh.omid.kharidchi.Interfacers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userSendInvoiceItem {

    @FormUrlEncoded
    @POST("android_sms/insert_invoice_items.php")
    public Call<Object> sendUserInvoiceItem(@Field("invoice_id") String invoice_id,
                                            @Field("product_name") String product_name,
                                            @Field("qty") String qty,
                                            @Field("price") String price,
                                            @Field("discount")String discount);
}
