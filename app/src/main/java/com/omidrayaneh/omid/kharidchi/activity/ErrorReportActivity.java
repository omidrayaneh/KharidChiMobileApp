package com.omidrayaneh.omid.kharidchi.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omidrayaneh.omid.kharidchi.Interfacers.userPostReport;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.helper.PrefManager;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ErrorReportActivity extends AppCompatActivity {

    EditText txtreport;
    String mobile;
    PrefManager pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_report);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        txtreport=findViewById(R.id.txt_report);
        pref=new PrefManager(getApplicationContext());
        mobile=pref.getMobileNumber();

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt=txtreport.getText().toString();
                if(txt.isEmpty()){
                    Toast.makeText(ErrorReportActivity.this, "متن ارسالی خالیست", Toast.LENGTH_SHORT).show();
                }else {
                    SendReportRetrofit(mobile,txtreport.getText().toString().trim());
                }
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void SendReportRetrofit( String mobile,String report) {

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        userPostReport registerInterface = retrofit.create(userPostReport.class);
        Call call = registerInterface.sendreport(mobile, report);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest = new Gson().toJson(response.body());
                if (stringRequest.contains("true"))
                    Toast.makeText(getApplicationContext(), R.string.tanks,Toast.LENGTH_SHORT).show();
                finish();

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
