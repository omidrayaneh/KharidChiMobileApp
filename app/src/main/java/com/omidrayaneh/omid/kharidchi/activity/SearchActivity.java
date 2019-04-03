package com.omidrayaneh.omid.kharidchi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.omidrayaneh.omid.kharidchi.Interfacers.getCategory;
import com.omidrayaneh.omid.kharidchi.Interfacers.userSendProduct;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.adapter.CategoryAdapter;
import com.omidrayaneh.omid.kharidchi.adapter.SearchProductAdapter;
import com.omidrayaneh.omid.kharidchi.helper.MySQLiteHelper;
import com.omidrayaneh.omid.kharidchi.model.CategoryModel;
import com.omidrayaneh.omid.kharidchi.model.product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity    {
    MaterialSearchView searchView;
    private List<product> productsList = new ArrayList<>();
    SearchProductAdapter adapter;
    RecyclerView recyclerView;
    MySQLiteHelper db;
    public static int mCartItemCount = 0;
    public static TextView textCartItemCount;
    TextView empty;

    List<CategoryModel> categoryModels=new ArrayList<>();
    RecyclerView category_recyclerview;
    CategoryAdapter adapter_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        category_recyclerview=findViewById(R.id.category_recycler_view);
        GetCategoryRetrofit();
        empty=findViewById(R.id.txt_null);

        db = new MySQLiteHelper(getApplicationContext());
        List<product> productsList = db.getAllProduct();
        mCartItemCount = db.getAllProduct().size();
        db.close();
        setupBadge();
        recyclerView = findViewById(R.id.search_recyclerView);

        searchView =  findViewById(R.id.search_view);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null)
        {
            final String search_value= bundle.getString("search_value");
            searchView.setQuery(search_value,true);
            SearchProductRetrofit(search_value);
            searchView.closeSearch();
        }

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                SearchProductRetrofit(query);
                searchView.closeSearch();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                if (productsList.size()>0)
                {
                    productsList.clear();
                    adapter=new SearchProductAdapter();
                    adapter.notifyDataSetChanged();
                }


                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCartItemCount = db.getAllProduct().size();
        db.close();
        setupBadge();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        searchView.showSearch();
        MenuItem item_account;
        item_account = menu.findItem(R.id.action_account);
        item_account.setVisible(false);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount =  actionView.findViewById(R.id.cart_badge);
        db = new MySQLiteHelper(getApplicationContext());
        setupBadge();
        db.close();
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_cart:
                Intent i=new Intent(SearchActivity.this,CardActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void SearchProductRetrofit(final String product) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        userSendProduct registerInterface = retrofit.create(userSendProduct.class);
        Call call = registerInterface.sendProduct(product);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {

                String stringRequest = new Gson().toJson(response.body());
                productsList.clear();
                try {
                    JSONArray array = new JSONArray(stringRequest);
                    empty.setVisibility(View.GONE);

                    for (int n = 0; n < array.length(); n++) {
                        product productModel = new product();
                        JSONObject obj = array.getJSONObject(n);
                        productModel.setId(Integer.parseInt(obj.get("id").toString().replace(".0","")));
                        productModel.setname_product(obj.get("name_product").toString());
                        productModel.setQty(obj.get("qty").toString());

                        productModel.setPrice(obj.get("price").toString());
                        productModel.setDiscont(obj.get("discount").toString());
                        productModel.setImage_url(obj.get("image_url").toString());
                        productModel.setOwner(obj.get("owner_name").toString());

                        productsList.add(n, productModel);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                        empty.setVisibility(View.VISIBLE);
                }
                adapter = new SearchProductAdapter(productsList, getApplicationContext());
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setNestedScrollingEnabled(false);

                //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
               // recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public void GetCategoryRetrofit() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        getCategory registerInterface = retrofit.create(getCategory.class);
        Call call = registerInterface.get_category();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                //  String stringRequest = response.body().toString();
                String stringRequest=new Gson().toJson(response.body());

                try {
                    JSONArray array = new JSONArray(stringRequest);
                    for (int n = 0; n < array.length(); n++) {
                        CategoryModel categoryModel=new CategoryModel();
                        JSONObject obj = array.getJSONObject(n);
                        categoryModel.setId(Integer.parseInt(obj.get("id").toString().replace(".0","")));
                        categoryModel.setName_category(obj.get("name_category").toString());
                        categoryModel.setStatus_category(Integer.parseInt(obj.get("status_category").toString().replace(".0","")));
                        categoryModels.add(n,categoryModel);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter_ = new CategoryAdapter(categoryModels,getApplicationContext());
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

                category_recyclerview.setLayoutManager(layoutManager);
                category_recyclerview.setItemAnimator(new DefaultItemAnimator());
                category_recyclerview.setAdapter(adapter_);


            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
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
}
