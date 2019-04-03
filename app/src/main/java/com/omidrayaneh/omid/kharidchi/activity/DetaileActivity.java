package com.omidrayaneh.omid.kharidchi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.omidrayaneh.omid.kharidchi.Interfacers.userDetails;
import com.omidrayaneh.omid.kharidchi.helper.MySQLiteHelper;
import com.omidrayaneh.omid.kharidchi.helper.PrefManager;
import com.omidrayaneh.omid.kharidchi.R;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetaileActivity extends AppCompatActivity {
    MySQLiteHelper db;
    public static TextView textCartItemCount;
    public static   int mCartItemCount = 0;
    PrefManager pref;
    TextView txtDetailName,txtDetailValue;
    ImageView ivDetailpath;
    String detailName;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        txtDetailName=findViewById(R.id.txt_details_name);
        txtDetailValue=findViewById(R.id.txt_details_value);
        ivDetailpath=findViewById(R.id.iv_details);


        Intent i=getIntent();
        Bundle bundle=i.getExtras();
        detailName=bundle.getString("detail");



        txtDetailName.setText(detailName);


        pref=new PrefManager(this);
        db = new MySQLiteHelper(getApplicationContext());
        mCartItemCount=db.getAllProduct().size();
        setupBadge();
        GetDetaliRetrofit(detailName);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item;
        item = menu.findItem(R.id.action_account);
        item.setVisible(false);
        /*item=menu.findItem(R.id.action_cart);
        item.setVisible(false);*/
        final MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);


        db = new MySQLiteHelper(getApplicationContext());
        setupBadge();
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public static void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void GetDetaliRetrofit(String details) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create()).build();
        userDetails registerInterface = retrofit.create(userDetails.class);
        Call call = registerInterface.getDetails(details);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest=new Gson().toJson(response.body());

                JSONObject obj = null;
                try {
                    obj = new JSONObject(stringRequest);
                    txtDetailName.setText( obj.getString("detail_name"));
                    txtDetailValue.setText( obj.getString("detail_value"));
                    Glide.with(getApplicationContext())
                            .load(obj.getString("detail_image"))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            .error(R.drawable.splashscreen)
                            .into(ivDetailpath);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}