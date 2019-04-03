package com.omidrayaneh.omid.kharidchi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.omidrayaneh.omid.kharidchi.Interfacers.userForgotpass;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.app.Config;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotActivity extends AppCompatActivity {
    EditText inputForgotOTP;
    Button btn_sendForgotOTP;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        progressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String mobile = bundle.getString("mobile");


        inputForgotOTP = findViewById(R.id.inputfrogotOtp);
        btn_sendForgotOTP = findViewById(R.id.btn_frogot_OTP);
        btn_sendForgotOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                ForgotOTPpassRetrofit(mobile, inputForgotOTP.getText().toString());
            }
        });

    }


    public void ForgotOTPpassRetrofit(final String userMobile, final String newPass) {


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        userForgotpass registerInterface = retrofit.create(userForgotpass.class);
        Call call = registerInterface.sendForgotpass(userMobile,newPass);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest = response.body().toString();

                if (stringRequest.contains("Sorry! OTP is missing.")) {
                    Toast.makeText(getApplicationContext(), R.string.otp_wrong, Toast.LENGTH_SHORT).show();

                } else if (stringRequest.contains("name") && stringRequest.contains("family") && stringRequest.contains("mobile")) {

                    Intent i = new Intent(ForgotActivity.this, NewPasswordActivity.class);
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
