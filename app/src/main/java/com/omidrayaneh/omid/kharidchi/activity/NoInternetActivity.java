package com.omidrayaneh.omid.kharidchi.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.omidrayaneh.omid.kharidchi.Interfacers.userImageSlider;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.model.SliderImage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoInternetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        TextView tv_no_internet=findViewById(R.id.no_internet);
        tv_no_internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
            }
        });

        Button btn=findViewById(R.id.btn_try_internet);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageSliderRetrofit();
                }
        });
    }

    public void ImageSliderRetrofit() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create()).build();
        userImageSlider registerInterface = retrofit.create(userImageSlider.class);
        Call call = registerInterface.getSlider();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest=new Gson().toJson(response.body());
                try {
                    JSONArray jsonArray = new JSONArray(stringRequest);
                    if (jsonArray.length() > 0) {
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //no internet
            }
        });
    }
}
