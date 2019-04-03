package com.omidrayaneh.omid.kharidchi.adapter;

import android.view.View;
import android.widget.Toast;

import com.omidrayaneh.omid.kharidchi.Interfacers.userSendInvoiceItem;
import com.omidrayaneh.omid.kharidchi.activity.MainActivity;
import com.omidrayaneh.omid.kharidchi.model.SliderImage;
import com.omidrayaneh.omid.kharidchi.model.product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ss.com.bannerslider.Slider;
import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    private int SliderCount;
    List<SliderImage> sliderImages;
    public MainSliderAdapter(int slider, List<SliderImage> sliderImages)
    {
        this.SliderCount=slider;
        this.sliderImages=sliderImages;
    }
    @Override
    public int getItemCount() {
        return SliderCount;
    }
    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {

          String[] p=new String[sliderImages.size()];
        for (int i=0;i<sliderImages.size();i++)
        {
            p[i]=sliderImages.get(i).getPath();
        }
        switch (position) {
            case 0:
                viewHolder.bindImageSlide(p[0]);
                break;
            case 1:
                viewHolder.bindImageSlide(p[1]);
                break;
            case 2:
                viewHolder.bindImageSlide(p[2]);
                break;
            case 3:
                viewHolder.bindImageSlide(p[3]);
                break;
            case 4:
                viewHolder.bindImageSlide(p[4]);
                break;
            case 5:
                viewHolder.bindImageSlide(p[5]);
                break;
            case 6:
                viewHolder.bindImageSlide(p[6]);
                break;
            case 7:
                viewHolder.bindImageSlide(p[7]);
                break;
            case 8:
                viewHolder.bindImageSlide(p[8]);
                break;
            case 9:
                viewHolder.bindImageSlide(p[9]);
                break;
            case 10:
                viewHolder.bindImageSlide(p[10]);
                break;
        }

    }


}