package com.omidrayaneh.omid.kharidchi.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.omidrayaneh.omid.kharidchi.Interfacers.getCategory;
import com.omidrayaneh.omid.kharidchi.Interfacers.userCost;
import com.omidrayaneh.omid.kharidchi.adapter.CategoryAdapter;
import com.omidrayaneh.omid.kharidchi.helper.FontsOverride;
import com.omidrayaneh.omid.kharidchi.helper.MySQLiteHelper;
import com.omidrayaneh.omid.kharidchi.helper.PrefManager;
import com.omidrayaneh.omid.kharidchi.adapter.MainSliderAdapter;
import com.omidrayaneh.omid.kharidchi.app.Config;
import com.omidrayaneh.omid.kharidchi.model.CategoryModel;
import com.omidrayaneh.omid.kharidchi.model.SliderImage;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.adapter.RecyclerViewDataAdapter;
import com.omidrayaneh.omid.kharidchi.model.prodact_category;
import com.omidrayaneh.omid.kharidchi.model.product;
import com.omidrayaneh.omid.kharidchi.services.PicassoImageLoadingService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import co.ronash.pushe.Pushe;
import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ss.com.bannerslider.Slider;
import ss.com.bannerslider.event.OnSlideClickListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static List<CategoryModel> categoryModels = new ArrayList<>();

    public static CategoryAdapter adapter;
    public static RecyclerView category_recyclerview;
    ImageView iv_banner6_1;
    public static TextView cat_close;




    public static String subCatId;
    TextView navUsername, categoryName;
    public TextView navMobile;
    String name = "";
    String family = "";
    String mobile = "";
    private ProgressDialog pDialog;
    List<prodact_category> allProdactcategory;
    @SuppressLint("StaticFieldLeak")
    public static TextView textCartItemCount;
    public static int mCartItemCount = 0;
    MySQLiteHelper db;
    private PrefManager pref;
    NavigationView navigationView;
    ArrayList<SliderImage> imageList;
    public static String cost;
    public static String min_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        imageList = (ArrayList<SliderImage>) i.getSerializableExtra("LIST");

        category_recyclerview = findViewById(R.id.category_recycler_view);
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/byekan.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/byekan.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/byekan.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/byekan.ttf");
        //delet mysql database
        //getApplication().deleteDatabase("ProductDB");//delet mysql database
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);
        categoryName = findViewById(R.id.txtCategory_name);
        View headerView = navigationView.getHeaderView(0);
        navUsername = headerView.findViewById(R.id.tv_user_name);
        navMobile = headerView.findViewById(R.id.tv_user_mobile);

        iv_banner6_1 = findViewById(R.id.banner6_1);
        cat_close = findViewById(R.id.close_cat);

        Glide.with(getApplicationContext()).load(Config.URL_Banner6_1).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                iv_banner6_1.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).into(iv_banner6_1);

        db = new MySQLiteHelper(getApplicationContext());
        mCartItemCount = db.getAllProduct().size();
        db.close();
        setupBadge();
        Pushe.initialize(this, true);

        Slider slider = findViewById(R.id.banner_slider);

        slider.setAdapter(new MainSliderAdapter(imageList.size(), imageList));
        slider.setOnSlideClickListener(new OnSlideClickListener() {
            @Override
            public void onSlideClick(int position) {
                int status = Integer.parseInt(imageList.get(position).getStatus_search().replace(".0", ""));
                if (status == 1) {
                    String value = imageList.get(position).getValue();
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    intent.putExtra("search_value", value);
                    startActivity(intent);
                }
            }
        });
        pref = new PrefManager(getApplicationContext());

        Menu menu = navigationView.getMenu();
        MenuItem nav_error_report = menu.findItem(R.id.nav_error_report);
        MenuItem nav_about = menu.findItem(R.id.nav_about);
        MenuItem nav_report = menu.findItem(R.id.nav_report);
        nav_error_report.setTitle("ارسال نظرها و پیشنهادها");
        nav_about.setTitle("درباره ما");
        nav_report.setTitle("گزارش خرید");
        Objects.requireNonNull(getSupportActionBar()).setTitle("خریدچی");
        GetCategoryRetrofit();
        GetCostRetrofit();
        cat_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryModels.clear();
                adapter = new CategoryAdapter(categoryModels, getApplicationContext());
                adapter.notifyDataSetChanged();
                cat_close.setVisibility(View.GONE);
                GetCategoryRetrofit();
            }
        });
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        allProdactcategory = new ArrayList<>();
        MainPageWebService(Config.URL_Main_Product_List);
        //ImageSliderRetrofit();
        Slider.init(new PicassoImageLoadingService(this));


    }


    public void shared() {
        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        if (prefs == null) {
            return;
        }
        name = pref.getUserDetails().get("name");
        family = prefs.getString("family", null);
        mobile = prefs.getString("mobile", null);

        if (name != null || family != null || mobile != null) {

            navMobile.setVisibility(View.VISIBLE);
            navUsername.setVisibility(View.VISIBLE);
            navUsername.setText(name + " " + family);
            navMobile.setText(mobile);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = actionView.findViewById(R.id.cart_badge);
        setupBadge();

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_account) {
            if (pref.getMobileNumber() == null) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            } else {
                AlertDialog(item);
            }
            return true;
        } else if (id == R.id.action_cart) {
            Intent i = new Intent(MainActivity.this, CardActivity.class);
            startActivity(i);
        } else if (id == R.id.action_search) {
            Intent i = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_report) {
            if (pref.getMobileNumber() != null) {
                Intent i = new Intent(getApplicationContext(), ReportActivity.class);
                i.putExtra("min_price","");
                i.putExtra("cost","");
                startActivity(i);
            } else {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        }
        if (id==R.id.nav_message)
        {
            Intent i = new Intent(MainActivity.this, MessageActivity.class);
            startActivity(i);
        }
        if(id==R.id.edit_profile){
            Intent i = new Intent(MainActivity.this, EditProfileActivity.class);
            startActivity(i);
        }
        if (id == R.id.nav_error_report) {
            Intent i = new Intent(MainActivity.this, ErrorReportActivity.class);
            startActivity(i);
        }
        if (id == R.id.nav_about) {
            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(i);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void AlertDialog(MenuItem item) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setMessage(R.string.exit_user);
        alertDialog.setIcon(R.drawable.ic_close);
        alertDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                pref.clearSession();
                name = "";
                family = "";
                mobile = "";
                navUsername.setVisibility(View.GONE);
                navMobile.setVisibility(View.GONE);
            }
        });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        shared();
        mCartItemCount = db.getAllProduct().size();
        db.close();
        setupBadge();
    }

    public void MainPageWebService(String url) {
        showProgressDialog();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onFinish() {
                // Completed the request (either success or failure)
                dismissProgressDialog();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                dismissProgressDialog();

                if (statusCode == 200 && response != null) {
                    //Log.i("response-", response.toString());
                    try {
                        JSONArray dataArary = response.getJSONArray("data");


                        for (int i = 0; i < dataArary.length(); i++) {
                            JSONObject sectionObj = (JSONObject) dataArary.get(i);

                            String title = sectionObj.getString("title");
                            List<product> products = new ArrayList<>();
                            JSONArray sectionsArray = sectionObj.getJSONArray("section");
                            for (int j = 0; j < sectionsArray.length(); j++) {

                                JSONObject obj = (JSONObject) sectionsArray.get(j);

                                product product = new product();
                                product.setId(obj.getInt("id_product"));
                                product.setname_product(obj.getString("name"));
                                product.setImage_url(obj.getString("image"));
                                product.setPrice(obj.getString("price"));
                                product.setQty(obj.getString("qty"));
                                product.setDiscont(obj.getString("discount"));
                                product.setOwner(obj.getString("owner_name"));

                                products.add(product);
                            }

                            prodact_category prodactcategory = new prodact_category();
                            prodactcategory.setname_category(title);
                            prodactcategory.setProduct(products);
                            allProdactcategory.add(prodactcategory);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
                    }

                    // setting data to RecyclerView
                    if (allProdactcategory != null) {

                        RecyclerView my_recycler_view = findViewById(R.id.my_recycler_view);
                        my_recycler_view.setNestedScrollingEnabled(false);
                        my_recycler_view.setHasFixedSize(true);
                        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(getApplicationContext(), allProdactcategory);
                        my_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                        my_recycler_view.setAdapter(adapter);

                    } else {
                        Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    dismissProgressDialog();
                    Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                }
                super.onSuccess(statusCode, headers, response);
            }
        });

    }

    public void showProgressDialog() {

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage(getString(R.string.wait));
        pDialog.setCancelable(false);

        if (!pDialog.isShowing()) pDialog.show();
    }

    public void dismissProgressDialog() {
        if (pDialog != null) pDialog.dismiss();
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

    public void GetCategoryRetrofit() {

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        getCategory registerInterface = retrofit.create(getCategory.class);
        Call call = registerInterface.get_category();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                //  String stringRequest = response.body().toString();
                String stringRequest = new Gson().toJson(response.body());

                try {
                    JSONArray array = new JSONArray(stringRequest);

                    categoryModels.clear();
                    for (int n = 0; n < array.length(); n++) {
                        CategoryModel categoryModel = new CategoryModel();

                        JSONObject obj = array.getJSONObject(n);
                        if (obj.isNull("id_fk")) {
                            categoryModel.setId(Integer.parseInt(obj.get("id").toString().replace(".0", "")));
                            categoryModel.setName_category(obj.get("name_category").toString());
                            categoryModel.setStatus_category(Integer.parseInt(obj.get("status_category").toString().replace(".0", "")));
                            categoryModels.add(n, categoryModel);

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter = new CategoryAdapter(categoryModels, getApplicationContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                category_recyclerview.setLayoutManager(layoutManager);
                category_recyclerview.setItemAnimator(new DefaultItemAnimator());
                category_recyclerview.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public void GetCostRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create()).build();
        userCost registerInterface = retrofit.create(userCost.class);
        Call call = registerInterface.requestcost();
        call.enqueue(new Callback() {


            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest = new Gson().toJson(response.body());
                try {
                    JSONArray array = new JSONArray(stringRequest);
                    for (int n = 0; n < array.length(); n++) {
                        JSONObject obj = array.getJSONObject(n);
                        cost = (obj.getString("cost_value"));
                        min_cost = (obj.getString("minimum_cost"));
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

}
