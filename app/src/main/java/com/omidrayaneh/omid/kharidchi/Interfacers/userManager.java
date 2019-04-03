package com.omidrayaneh.omid.kharidchi.Interfacers;

import com.omidrayaneh.omid.kharidchi.model.Users;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface userManager {
    @FormUrlEncoded
    @POST("android_sms/user_exists.php")
    public Call<Object>  sendMobile(@Field("mobile") String mobile);
}

