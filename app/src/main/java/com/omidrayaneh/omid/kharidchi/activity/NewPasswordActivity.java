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

import com.omidrayaneh.omid.kharidchi.Interfacers.userNewPass;
import com.omidrayaneh.omid.kharidchi.helper.PrefManager;
import com.omidrayaneh.omid.kharidchi.R;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewPasswordActivity extends AppCompatActivity {

    EditText inputNewpass,inputNewrepass;
    Button btn_newPass;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String mobile = bundle.getString("mobile");

        progressBar=findViewById(R.id.progressBar);
        inputNewpass=findViewById(R.id.inputNewPass);
        inputNewrepass=findViewById(R.id.inputNewRepass);

        btn_newPass=findViewById(R.id.btn_new_pass);
        btn_newPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputNewpass.length()<6)
                {
                    Toast.makeText(getApplicationContext(),R.string.less_pass,Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (inputNewpass.length()==0 && inputNewrepass.length()==0)
                {
                    Toast.makeText(getApplicationContext(), R.string.pass_not_null,Toast.LENGTH_SHORT).show();
                    return;
                }

                if(inputNewpass.getText().toString().equals(inputNewrepass.getText().toString()))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    AddNewPassRetrofit(mobile,inputNewpass.getText().toString());
                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.retaype_pass,Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }

    public void AddNewPassRetrofit(final String userMobile, final String userpass) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").addConverterFactory(GsonConverterFactory.create()).build();
        userNewPass userManager = retrofit.create(userNewPass.class);
        Call call = userManager.sendNewPass(userMobile,userpass);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {


                String stringRequest= response.body().toString();

                String name = "";
                String family = "";
                if (stringRequest.contains("Sorry! OTP is missing.")) {
                    Toast.makeText(getApplicationContext(),R.string.otp_wrong , Toast.LENGTH_SHORT).show();

                } else if (stringRequest.contains("name") && stringRequest.contains("family") && stringRequest.contains("mobile")) {

                    try {
                        JSONObject obj = new JSONObject(stringRequest);
                        name = obj.getString("name");
                        family = obj.getString("family");

                    } catch (Throwable t) {
                        Log.e("My App",  stringRequest );
                    }
                    PrefManager pref = new PrefManager(getApplicationContext());
                    pref.createLogin(name, family, userMobile);

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
