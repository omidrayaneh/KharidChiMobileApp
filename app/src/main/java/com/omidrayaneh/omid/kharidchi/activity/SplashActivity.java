package com.omidrayaneh.omid.kharidchi.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.google.gson.Gson;
import com.omidrayaneh.omid.kharidchi.Interfacers.userImageSlider;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.model.SliderImage;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;
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

public class SplashActivity extends  AwesomeSplash   {


    @Override
    public void initSplash(ConfigSplash configSplash) {

        configSplash.setBackgroundColor(R.color.colorPrimary); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(1000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_BOTTOM);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.splashscreen); //or any other drawable
        configSplash.setAnimLogoSplashDuration(1000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.BounceInRight); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
        configSplash.setPathSplash(getString(R.string.omidrayaneh)); //set path String
        configSplash.setOriginalHeight(200); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(200); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(1000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.strokeColor); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(500);
        configSplash.setPathSplashFillColor(R.color.fillColor); //path object filling color

        //Customize Title
        configSplash.setTitleSplash(getString(R.string.app_name));

        configSplash.setTitleTextColor(R.color.strokeColor);
        configSplash.setTitleTextSize(45f); //float value
        configSplash.setAnimTitleDuration(2000);
        configSplash.setAnimTitleTechnique(Techniques.FadeIn);


         configSplash.setTitleFont("fonts/byekan.ttf"); //provide string to your font located in assets/fonts/
    }

    @Override
    public void animationsFinished() {

        ImageSliderRetrofit();
    }

    public void ImageSliderRetrofit() {
        final List<SliderImage> imageList= new ArrayList<>();

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

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            SliderImage sliderImage = new SliderImage();
                            sliderImage.setPath(obj.getString("path"));
                            sliderImage.setStatus_search(obj.getString("status_search"));
                            sliderImage.setValue(obj.getString("value"));
                            imageList.add(i, sliderImage);
                        }
                        Configuration configuration = getResources().getConfiguration();
                        configuration.setLayoutDirection(new Locale("fa"));
                        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

                        Intent i=new Intent(SplashActivity.this,MainActivity.class);
                        i.putExtra("LIST", (Serializable) imageList);
                        startActivity(i);
                        finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
               //Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG).show();
                //goto no internet page
                Intent i=new Intent(SplashActivity.this,NoInternetActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

}

