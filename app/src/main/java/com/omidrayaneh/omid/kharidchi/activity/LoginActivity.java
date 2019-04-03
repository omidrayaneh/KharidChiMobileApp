package com.omidrayaneh.omid.kharidchi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.omidrayaneh.omid.kharidchi.Interfacers.userManager;
import com.omidrayaneh.omid.kharidchi.R;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_close;
    EditText inputMobile;
    Button btn;
    TextView  user_mobile;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        inputMobile = findViewById(R.id.inputMobile);
        btn = findViewById(R.id.btn_request_sms);
        btn.setOnClickListener(this);
        iv_close = findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);
        user_mobile = findViewById(R.id.tv_user_name);
        user_mobile = findViewById(R.id.tv_user_mobile);
        progressBar=findViewById(R.id.progressBar);
    }

    private static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{11}$";
        return mobile.matches(regEx);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.btn_request_sms:
                if (inputMobile.length()<11)
                {
                    Toast.makeText(getApplicationContext(), R.string.less_mobile,Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isValidPhoneNumber(inputMobile.getText().toString())) {
                    progressBar.setVisibility(View.VISIBLE);
                    SetnMobileRetrofit(inputMobile.getText().toString());
                }

                break;
        }
    }




    public void SetnMobileRetrofit(final String userMobile) {


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        userManager userManager = retrofit.create(userManager.class);
        Call call = userManager.sendMobile(userMobile);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {

                Intent i=null;

                String strinRequest= response.body().toString();
                if (strinRequest.contains("name"))
                {
                    i=new Intent(getApplicationContext(),EnterPasswordActivity.class);

                }
                else if(strinRequest.contains("false"))
                {
                    i=new Intent(getApplicationContext(),SingUpActivity.class);
                }
                else if(strinRequest.contains("block"))
                {
                    Toast.makeText(getApplicationContext(),getString(R.string.acount_block) +"\n"+
                            getString(R.string.blocked),Toast.
                            LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    return;

                }
                i.putExtra("mobile", userMobile);
                startActivity(i);
                finish();
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                progressBar.setVisibility(View.GONE);

            }
        });


    }

}







