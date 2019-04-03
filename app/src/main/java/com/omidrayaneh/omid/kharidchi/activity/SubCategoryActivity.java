package com.omidrayaneh.omid.kharidchi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omidrayaneh.omid.kharidchi.Interfacers.getSubCategory;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.adapter.SubCategoryAdapter;
import com.omidrayaneh.omid.kharidchi.model.CategoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubCategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SubCategoryAdapter adapter;
    private List<CategoryModel> categoryModels;
     TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        empty=findViewById(R.id.txt_empty);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String categoryId = bundle.getString("categoryId");
        String categoryName = bundle.getString("categoryName");

        setTitle(categoryName);
        int id=Integer.parseInt(categoryId.replace(".0",""));
        GetSubCategoryRetrofit(id);

        recyclerView =  findViewById(R.id.recycler_view_sub_category);

        categoryModels = new ArrayList<>();
        adapter = new SubCategoryAdapter(categoryModels,this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;


        }
        return super.onOptionsItemSelected(item);    }

    public void GetSubCategoryRetrofit(int id) {

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        getSubCategory registerInterface = retrofit.create(getSubCategory.class);
        Call call = registerInterface.getCategory(id);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest = new Gson().toJson(response.body());

                try {
                    JSONArray array = new JSONArray(stringRequest);
                    if (array.length()==0)
                    {
                        empty.setVisibility(View.VISIBLE);
                    }
                    else {
                        empty.setVisibility(View.GONE);
                    }
                    for (int n = 0; n < array.length(); n++) {

                        CategoryModel categoryModel = new CategoryModel();
                        JSONObject obj = array.getJSONObject(n);
                        categoryModel.setId(Integer.parseInt(obj.get("id").toString().replace(".0", "")));
                        categoryModel.setSub_categoryId(Integer.parseInt(obj.get("id_fk").toString().replace(".0", "")));
                        categoryModel.setName_category(obj.get("name_category").toString());
                        categoryModel.setImage_url(obj.get("image_url").toString());
                        categoryModel.setEnd_category(Integer.parseInt(obj.get("end_cat").toString().replace(".0", "")));
                        categoryModel.setStatus_category(Integer.parseInt(obj.get("status_category").toString().replace(".0", "")));

                        categoryModels.add(n, categoryModel);

                    }
                    if (categoryModels.size() == 0) {

                    } else {
                    }
                } catch (JSONException e) {
                    MainActivity.cat_close.setText("گروهی تعریف نشده");
                    e.printStackTrace();
                }

                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
