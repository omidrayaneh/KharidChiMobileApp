package com.omidrayaneh.omid.kharidchi.activity;

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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omidrayaneh.omid.kharidchi.Interfacers.userMessage;
import com.omidrayaneh.omid.kharidchi.Interfacers.userReport;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.adapter.MessageAdapter;
import com.omidrayaneh.omid.kharidchi.adapter.ReportAdapter;
import com.omidrayaneh.omid.kharidchi.model.InvoiceModel;
import com.omidrayaneh.omid.kharidchi.model.MessageModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageActivity extends AppCompatActivity {

    List<MessageModel> messageModels=new ArrayList<>();

    RecyclerView recyclerView;
    MessageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(R.string.messages);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        recyclerView=findViewById(R.id.message_recyclerView);
        GetMessageRetrofit();
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

    public void GetMessageRetrofit() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        userMessage registerInterface = retrofit.create(userMessage.class);
        Call call = registerInterface.getMessage();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {

                String stringRequest=new Gson().toJson(response.body());
                if (stringRequest.contains("null"))
                {
                    return;
                }
                try {
                    JSONArray jsonArray = new JSONArray(stringRequest);

                    if (jsonArray.length()>0)
                    {
                        for(int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            MessageModel messageModel=new MessageModel();
                            messageModel.setContent(obj.get("content").toString());
                            messageModel.setTitle(obj.get("title").toString());
                            messageModel.setDate(obj.get("expire_date").toString());
                            messageModel.setStatus(Integer.parseInt(obj.get("status").toString().replace(".0","")));

                            messageModels.add(i,messageModel);
                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                adapter = new MessageAdapter(messageModels,getApplicationContext());
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
