package com.omidrayaneh.omid.kharidchi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omidrayaneh.omid.kharidchi.Interfacers.userReport;
import com.omidrayaneh.omid.kharidchi.helper.PrefManager;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.adapter.ReportAdapter;
import com.omidrayaneh.omid.kharidchi.model.InvoiceModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReportActivity extends AppCompatActivity {

    List<InvoiceModel> invoices=new ArrayList<>();
    RecyclerView recyclerView;
    ReportAdapter adapter;
    PrefManager pref;
    TextView txt_empty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.puech_record));

        txt_empty=findViewById(R.id.txt_report_empty);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        pref=new PrefManager(this);
        recyclerView=findViewById(R.id.report_recyclerView);
        GetInvoiceReportRetrofit(pref.getMobileNumber());



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_account);
        item.setVisible(false);
        item=menu.findItem(R.id.action_cart);
        item.setVisible(false);
        item=menu.findItem(R.id.action_search);
        item.setVisible(false);
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

    public void GetInvoiceReportRetrofit(final String userMobile) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        userReport registerInterface = retrofit.create(userReport.class);
        Call call = registerInterface.reportInvoice(userMobile);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {

                String stringRequest=new Gson().toJson(response.body());
                if (stringRequest.contains("null"))
                {
                    txt_empty.setVisibility(View.VISIBLE);
                    return;
                }
                try {
                        JSONArray jsonArray = new JSONArray(stringRequest);

                        if (jsonArray.length()>0)
                        {
                            for(int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                InvoiceModel invoiceModel=new InvoiceModel();
                                invoiceModel.setId(Integer.parseInt(obj.get("id").toString().replace(".0","")));
                                invoiceModel.setStatuse(Integer.parseInt(obj.get("status").toString().replace(".0","")));

                                invoiceModel.setDate(obj.get("date").toString());
                                invoiceModel.setInvoiceNo(obj.get("invoice_no").toString());

                                invoiceModel.setTotal(obj.get("price").toString());
                                invoices.add(i,invoiceModel);
                            }
                        }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                adapter = new ReportAdapter(invoices,getApplicationContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call call, Throwable t) {
            }
        });

    }
}
