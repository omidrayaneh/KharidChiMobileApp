package com.omidrayaneh.omid.kharidchi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.omidrayaneh.omid.kharidchi.Interfacers.userVerifyOtp;
import com.omidrayaneh.omid.kharidchi.helper.PrefManager;
import com.omidrayaneh.omid.kharidchi.R;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OtpActivity extends AppCompatActivity {
    EditText inputOTP;
    Button btn_sendOTP;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String mobile = bundle.getString("mobile");

        progressBar=findViewById(R.id.progressBar);
        inputOTP = findViewById(R.id.inputOtp);
        btn_sendOTP = findViewById(R.id.btn_OTP);
        btn_sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputOTP.length()==0)
                {
                    Toast.makeText(getApplicationContext(), R.string.null_otp,Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(inputOTP.length()<6)
                {
                    Toast.makeText(getApplicationContext(), R.string.opt_less,Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                ActiveOTPRetrofit(mobile, inputOTP.getText().toString());
            }
        });

    }

    public void ActiveOTPRetrofit(final String userMobile, final String userOtp) {


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").addConverterFactory(GsonConverterFactory.create()).build();
        userVerifyOtp userManager = retrofit.create(userVerifyOtp.class);
        Call call = userManager.sendVerifiOtp(userMobile,userOtp);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {

                String stringRequest = response.body().toString();

                String name = "";
                String family = "";
                if (stringRequest.contains("Sorry! OTP is missing.")) {
                    Toast.makeText(getApplicationContext(),R.string.otp_wrong, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                } else if (stringRequest.contains("name") && stringRequest.contains("family") && stringRequest.contains("mobile")) {

                    try {
                        JSONObject obj = new JSONObject(stringRequest);
                        name = obj.getString("name");
                        family = obj.getString("family");

                    } catch (Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                    }

                    PrefManager pref = new PrefManager(getApplicationContext());
                    pref.createLogin(name, family, userMobile);

                    Toast.makeText(getApplicationContext(), R.string.tanks_reg, Toast.LENGTH_SHORT).show();
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
