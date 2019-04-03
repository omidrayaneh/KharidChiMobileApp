package com.omidrayaneh.omid.kharidchi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omidrayaneh.omid.kharidchi.Interfacers.userAddressExist;
import com.omidrayaneh.omid.kharidchi.Interfacers.userSendInvoice;
import com.omidrayaneh.omid.kharidchi.Interfacers.userSendInvoiceItem;
import com.omidrayaneh.omid.kharidchi.helper.MySQLiteHelper;
import com.omidrayaneh.omid.kharidchi.helper.PrefManager;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.adapter.BuyCardAdapter;
import com.omidrayaneh.omid.kharidchi.model.product;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuyActivity extends AppCompatActivity {
    public static TextView textCartItemCount;
    public static   int mCartItemCount = 0;
    boolean active;
    MySQLiteHelper db;
    private List<product> productsList = new ArrayList<>();
    public static Button button_next;
    public static TextView totalCard_buy,txtCost,txtInvoice,txtDiscount;
    PrefManager pref;
    RecyclerView recyclerView ;
    String invoice_id_;
    RadioButton rb_online,rb_atHome;



    TextView state,town,address,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button_next=findViewById(R.id.Save_buy);
        totalCard_buy =findViewById(R.id.totalBuyPrice);
        txtCost=findViewById(R.id.txtCost);
        txtInvoice=findViewById(R.id.txtInvoice);
        txtDiscount=findViewById(R.id.txtDiscount);

        rb_online=findViewById(R.id.payOnline);
        rb_atHome=findViewById(R.id.payَAtHome);
        rb_atHome.setChecked(true);

        rb_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_atHome.setChecked(true);
                rb_online.setChecked(false);
                Toast.makeText(BuyActivity.this, "بزودی", Toast.LENGTH_SHORT).show();
            }
        });

        rb_atHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_online.setChecked(true);
                rb_online.setChecked(false);
            }
        });
        recyclerView=  findViewById(R.id.recycler_buy);
        state=findViewById(R.id.txtState);
        town=findViewById(R.id.txtTown);
        address=findViewById(R.id.txtAddress);
        phone=findViewById(R.id.txtPhone);
        pref=new PrefManager(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        db = new MySQLiteHelper(getApplicationContext());
        productsList=db.getAllProduct();
        mCartItemCount=db.getAllProduct().size();
        setupBadge();

        GetAddressRetrofit(pref.getMobileNumber());

        BuyCardAdapter mAdapter = new BuyCardAdapter(productsList, getApplicationContext());
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        button_next.setOnClickListener(view -> {

            SendInvoiceRetrofit(pref.getMobileNumber(),db.getAllProduct());
            product p = new product();
            for (int i=0;i<productsList.size();i++) {
                p.setId(productsList.get(i).getId());
                db.deleteProduct(p);
            }
            Toast.makeText(getApplicationContext(), R.string.saved_factor,Toast.LENGTH_LONG).show();
            finish();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item;
         item = menu.findItem(R.id.action_account);
        item.setVisible(false);
        /*item=menu.findItem(R.id.action_cart);
        item.setVisible(false);*/
        final MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);
        db = new MySQLiteHelper(getApplicationContext());
        setupBadge();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mCartItemCount=db.getAllProduct().size();
        setupBadge();
    }
    public static void setupBadge() {
        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void GetAddressRetrofit(final String userMobile) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        userAddressExist registerInterface = retrofit.create(userAddressExist.class);
        Call call = registerInterface.requestAddress(userMobile);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest=new Gson().toJson(response.body());
                if(stringRequest.contains("null")) {
                    //go to add address
                    Intent i=new Intent(getApplicationContext(),AddressActivity.class);
                    startActivity(i);
                    finish();
                }
                else {

                    try {
                        JSONObject obj = new JSONObject(stringRequest);

                        town.setText( obj.getString("town_name"));
                        state.setText(  obj.getString("state_name"));
                        address.setText( obj.getString("full_address"));
                        phone .setText( obj.getString("phone"));


                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }


            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
    public void SendInvoiceRetrofit(final String userMobile, final List<product> productsList) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create()).build();
        userSendInvoice registerInterface = retrofit.create(userSendInvoice.class);
        Call call = registerInterface.sendUserInvoice(userMobile);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest = response.body().toString();
                invoice_id_ = stringRequest;
                for (int i = 0; i < productsList.size(); i++) {
                    SendInvoiceItemsRetrofit(invoice_id_, productsList.get(i).getName_product(), productsList.get(i).getQty(), productsList.get(i).getPrice(),productsList.get(i).getDiscont());
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
    public void SendInvoiceItemsRetrofit(final String invoice_id, final String product_name, final String qty, final String price,final  String disount) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create()).build();
        userSendInvoiceItem registerInterface = retrofit.create(userSendInvoiceItem.class);
        Call call = registerInterface.sendUserInvoiceItem(invoice_id,product_name,qty,price,disount);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest = response.body().toString();
                if (stringRequest.equals("true")) {
                    invoice_id_ = "";
                    for (int i=0;i>productsList.size();i++)
                    {
                        product p=new product();
                        p.setId(productsList.get(i).getId());
                        p.setName_product(productsList.get(i).getName_product());
                        p.setQty(productsList.get(i).getQty());
                        p.setPrice(productsList.get(i).getPrice());
                        p.setDiscont(productsList.get(i).getDiscont());
                        db.deleteProduct(p);
                    }
                        productsList.clear();

                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}

