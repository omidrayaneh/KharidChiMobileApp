package com.omidrayaneh.omid.kharidchi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omidrayaneh.omid.kharidchi.Interfacers.getProfile;
import com.omidrayaneh.omid.kharidchi.Interfacers.saveProfile;
import com.omidrayaneh.omid.kharidchi.Interfacers.userMessage;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.adapter.MessageAdapter;
import com.omidrayaneh.omid.kharidchi.helper.PrefManager;
import com.omidrayaneh.omid.kharidchi.model.MessageModel;
import com.omidrayaneh.omid.kharidchi.model.Users;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfileActivity extends AppCompatActivity {
    PrefManager pref;
    String mobile = "";
    EditText name,family,address,mobilephone,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(R.string.edit_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        pref = new PrefManager(this);

        mobile = pref.getMobileNumber();

        name=findViewById(R.id.etxt_name);
        family=findViewById(R.id.etxt_family);
        address=findViewById(R.id.etxt_address);
        mobilephone=findViewById(R.id.etxt_mobile);
        phone=findViewById(R.id.etxt_phone);

        GetProfileRetrofit(mobile);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveProfileRetrofit(name.getText().toString(),family.getText().toString(),mobilephone.getText().toString(),address.getText().toString(),phone.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_account);
        item.setVisible(true);
        item = menu.findItem(R.id.action_cart);
        item.setVisible(false);
        item = menu.findItem(R.id.action_search);
        item.setVisible(true);
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

    public void GetProfileRetrofit(String mobile) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        getProfile registerInterface = retrofit.create(getProfile.class);
        Call call = registerInterface.getMobile(mobile);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {

                String stringRequest = new Gson().toJson(response.body());
                if (stringRequest.contains("null")) {
                    Intent i = new Intent(EditProfileActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
                try {
                    JSONObject jsonArray = new JSONObject(stringRequest);

                    if (jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                           name.setText(jsonArray.get("name").toString());
                           family.setText(jsonArray.get("family").toString());
                           mobilephone.setText(jsonArray.get("mobile").toString());
                           address.setText(jsonArray.get("full_address").toString());
                           phone.setText(jsonArray.get("phone").toString());

                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
            }
        });

    }
    public void SaveProfileRetrofit(String name,String family,String mobile,String address,String phone) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        saveProfile registerInterface = retrofit.create(saveProfile.class);
        Call call = registerInterface.saveaddress(name,family,mobile,address,phone);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {

                String stringRequest = new Gson().toJson(response.body());
                Toast.makeText(EditProfileActivity.this, "پروفایل شما یا موفقیت ویرایش شد", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "ثبت اطلاعات با مشکل مواجه شده است. لطفا چند دقیقه بعد امتحان کنید", Toast.LENGTH_LONG).show();
            }
        });

    }
}
