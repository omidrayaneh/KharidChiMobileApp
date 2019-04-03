package com.omidrayaneh.omid.kharidchi.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;
import com.omidrayaneh.omid.kharidchi.Interfacers.userPhotos;
import com.omidrayaneh.omid.kharidchi.Interfacers.userSendInvoiceItem;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.adapter.PhotosAdapter;
import com.omidrayaneh.omid.kharidchi.app.Config;
import com.omidrayaneh.omid.kharidchi.model.Photos;
import com.omidrayaneh.omid.kharidchi.model.product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ShowImageActivity extends AppCompatActivity {
    public static PhotoView zoomImage;
    ImageView iv_banner6_1;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    String url = "";
    private List<Photos> photosList = new ArrayList<>();
    PhotosAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        recyclerView=findViewById(R.id.photo_recyclerView);

        url = getIntent().getStringExtra("image_url");
        String id=getIntent().getStringExtra("id");



        zoomImage = findViewById(R.id.myImage);
        iv_banner6_1 = findViewById(R.id.banner);
        fab=findViewById(R.id.fab_close);fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Glide.with(this).load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(zoomImage);

        GetImageRetrofit(Integer.parseInt(id));

        Glide.with(getApplicationContext()).load(Config.URL_Banner6_2).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                iv_banner6_1.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).into(iv_banner6_1);

    }

    public void GetImageRetrofit(final int product_id ) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create()).build();
        userPhotos registerInterface = retrofit.create(userPhotos.class);
        Call call = registerInterface.getphotos(product_id);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest = new Gson().toJson(response.body());
                if (stringRequest.contains("null"))
                {
                    iv_banner6_1.setVisibility(View.VISIBLE);
                }
                else{
                    iv_banner6_1.setVisibility(View.GONE);
                    try {
                        JSONArray array = new JSONArray(stringRequest);
                        for (int n = 0; n < array.length(); n++) {
                            Photos photos =new Photos();
                            JSONObject obj = array.getJSONObject(n);
                            photos.setId(Integer.parseInt(obj.get("id").toString().replace(".0", "")));
                            photos.setProduct_id(Integer.parseInt(obj.get("product_id").toString().replace(".0", "")));
                            photos.setWeb_url(obj.get("web_url").toString());
                            photosList.add(n,photos);
                        }

                        mAdapter=new PhotosAdapter(photosList, getApplicationContext());
                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(mAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
