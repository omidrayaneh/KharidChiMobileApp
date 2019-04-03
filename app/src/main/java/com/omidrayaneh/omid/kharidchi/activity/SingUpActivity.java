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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omidrayaneh.omid.kharidchi.Interfacers.userSingUp;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.app.Config;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingUpActivity extends AppCompatActivity {


    Button btn_SingUp;
    EditText inputName,inputFamily,inputPass,inputRepass;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String mobile= bundle.getString("mobile");


        progressBar=findViewById(R.id.progressBar);
        inputName=findViewById(R.id.inputName);
        inputFamily=findViewById(R.id.inputfamily);
        inputPass=findViewById(R.id.inputPass);
        inputRepass=findViewById(R.id.inputRepass);

        btn_SingUp=findViewById(R.id.btn_sign_up);
        btn_SingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputName.length()==0||inputFamily.length()==0||inputPass.length()==0||inputRepass.length()==0)
                {
                    Toast.makeText(getApplicationContext(), R.string.null_fild,Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (inputPass.length()<6){
                    Toast.makeText(getApplicationContext(),R.string.less_pass,Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (inputName.length()<3||inputFamily.length()<3)
                {
                    Toast.makeText(getApplicationContext(), R.string.less_name,Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (!inputPass.getText().toString().equals(inputRepass.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), R.string.repass,Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                SignUpRetrofit(inputName.getText().toString()
                    ,inputFamily.getText().toString()
                    ,mobile
                    ,inputPass.getText().toString());
            }
        });
    }
   /* public void SignUpRetrofit2(final String name,final String family, final String mobile,final String pass ) {
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create()).build();
        userSingUp userSingUp=retrofit.create(userSingUp.class);

    }*/

    public void SignUpRetrofit(final String name,final String family, final String mobile,final String pass ) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        userSingUp userManager = retrofit.create(userSingUp.class);
        Call call = userManager.sendSingup(name,family,mobile,pass);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {

                String stringRequest = response.body().toString();
                if(stringRequest.contains("string(91)")) {
                    Toast.makeText(getApplicationContext(), R.string.not_mobile,Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(stringRequest.contains("string(30)"))
                {
                    Intent i=new Intent(getApplicationContext(),OtpActivity.class);
                    i.putExtra("mobile",mobile);startActivity(i);
                    finish();
                }
                else if(stringRequest.contains("string(53)"))
                {
                    Toast.makeText(getApplicationContext(), R.string.wrong_mobile,Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
