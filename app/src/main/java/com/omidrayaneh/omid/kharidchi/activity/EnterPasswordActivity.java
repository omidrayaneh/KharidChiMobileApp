package com.omidrayaneh.omid.kharidchi.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omidrayaneh.omid.kharidchi.Interfacers.userPass;
import com.omidrayaneh.omid.kharidchi.Interfacers.userPassManager;
import com.omidrayaneh.omid.kharidchi.helper.PrefManager;
import com.omidrayaneh.omid.kharidchi.R;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnterPasswordActivity extends AppCompatActivity {

    Button btn_enter;
    EditText inputPass;
    TextView tvForgot;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String mobile = bundle.getString("mobile");

         progressBar = findViewById(R.id.progressBar);

        inputPass = findViewById(R.id.inputEnterPass);

        btn_enter = findViewById(R.id.btn_enter_Pass);
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                if (inputPass.getText().equals(""))
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), R.string.mobile_null,Toast.LENGTH_SHORT).show();
                    return;
                }

                EnterpassRetrofit(mobile, inputPass.getText().toString());
            }
        });

        tvForgot = findViewById(R.id.tv_ForgotPassword);
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                ForgotPass_sendOTP_Retrofit(mobile);
            }
        });
    }

    public void EnterpassRetrofit(final String userMobile, final String userrpass) {


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create()).build();
        userPassManager registerInterface = retrofit.create(userPassManager.class);
        Call call = registerInterface.sendUserpass(userMobile,userrpass);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest = response.body().toString();

                if (stringRequest.contains("false")) {
                    // goto sing up page
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), R.string.incorrect_pass, Toast.LENGTH_SHORT).show();
                } else if (stringRequest.contains("name") && stringRequest.contains("family") && stringRequest.contains("mobile")) {
                    String name = "";
                    String family = "";
                    String status = "";


                    try {

                        JSONObject obj = new JSONObject(stringRequest);
                        name = obj.getString("name");
                        family = obj.getString("family");
                       // mobile = obj.getString("mobile");
                        status = obj.getString("status");


                        if (status.equals("0")) {
                            Toast.makeText(getApplicationContext(), R.string.lost_pass, Toast.LENGTH_SHORT).show();
                            return;
                        }

                        PrefManager pref = new PrefManager(getApplicationContext());
                        pref.createLogin(name, family, userMobile);

                    } catch (Throwable t) {
                        Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                    }
                    finish();
                    progressBar.setVisibility(View.GONE);

                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                progressBar.setVisibility(View.GONE);

            }
        });
    }


    public void ForgotPass_sendOTP_Retrofit(final String userMobile) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        userPass registerInterface = retrofit.create(userPass.class);
        Call call = registerInterface.sendForgotpass(userMobile);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest = response.body().toString();


                if(stringRequest.contains("string(30)"))
                { Intent i=new Intent(EnterPasswordActivity.this,ForgotActivity.class);
                    i.putExtra("mobile",userMobile);
                    startActivity(i);
                    finish();
                    progressBar.setVisibility(View.GONE);

                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                progressBar.setVisibility(View.GONE);

            }
        });
    }
}
