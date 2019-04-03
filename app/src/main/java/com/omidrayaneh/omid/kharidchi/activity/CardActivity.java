package com.omidrayaneh.omid.kharidchi.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.omidrayaneh.omid.kharidchi.Interfacers.userCost;
import com.omidrayaneh.omid.kharidchi.helper.MySQLiteHelper;
import com.omidrayaneh.omid.kharidchi.helper.PrefManager;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.adapter.CardProductAdapter;
import com.omidrayaneh.omid.kharidchi.model.product;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CardActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static TextView textCartItemCount;
    public static int mCartItemCount = 0;
    MySQLiteHelper db;
    @SuppressLint("StaticFieldLeak")
    public static TextView txtTotal;
    private PrefManager pref;
    @SuppressLint("StaticFieldLeak")
    public static FloatingActionButton fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pref = new PrefManager(getApplicationContext());
        //GetCostRetrofit();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        txtTotal=findViewById(R.id.txtCardTotalValue);
        RecyclerView recycler = findViewById(R.id.recycler1);
        db = new MySQLiteHelper(getApplicationContext());
        List<product> productsList = db.getAllProduct();
        CardProductAdapter mAdapter = new CardProductAdapter(productsList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(mLayoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(mAdapter);
        fab=findViewById(R.id.fab);
        if (productsList.size()==0)
        {
            txtTotal.setText(R.string.card_empty);
            txtTotal.setBackgroundColor(getResources().getColor(R.color.empty));
            fab.setVisibility(View.GONE);
        }
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            if (pref.getMobileNumber()!=null)
            {
                AlertDialog();
            }
            else
            {
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCartItemCount = db.getAllProduct().size();
        setupBadge();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem itemaccount = menu.findItem(R.id.action_account);
        itemaccount.setVisible(false);

        MenuItem itemsearch = menu.findItem(R.id.action_search);
        itemsearch.setVisible(false);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount =  actionView.findViewById(R.id.cart_badge);
        db = new MySQLiteHelper(getApplicationContext());
        setupBadge();
        return true;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void AlertDialog() {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(CardActivity.this);
        //alertDialog.setTitle("Confirm Buy...");
        alertDialog.setMessage(R.string.sure_buy);
        alertDialog.setIcon(R.drawable.ic_close);
        // Setting Negative "YES" Button
        alertDialog.setPositiveButton(R.string.yes, (dialog, which) -> {
            Intent i=new Intent(CardActivity.this,BuyActivity.class);
            startActivity(i);
            finish();
        });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton(R.string.no, (dialog, which) -> dialog.cancel());
        alertDialog.show();
    }
    public void GetCostRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create()).build();
        userCost registerInterface = retrofit.create(userCost.class);
        Call call = registerInterface.requestcost();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
               String stringRequest=new Gson().toJson(response.body());
                try {
                    JSONArray array = new JSONArray(stringRequest);
                    for (int n = 0; n < array.length(); n++) {
                        JSONObject obj = array.getJSONObject(n);
                       // cost_value=( obj.getString("cost_value"));
                       // min_cost=(  obj.getString("minimum_cost"));
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

}
