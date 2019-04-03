package com.omidrayaneh.omid.kharidchi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.activity.SubCategoryActivity;
import com.omidrayaneh.omid.kharidchi.model.CategoryModel;
import com.omidrayaneh.omid.kharidchi.model.product;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<CategoryModel> categoryModels;
    private List<product> productsList;
    private Context mContext;

    public CategoryAdapter(List<CategoryModel> categoryModels, Context mContext) {
        this.categoryModels = categoryModels;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_list, parent, false);
        return new CategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        CategoryModel categoryModel = categoryModels.get(position);


        holder.category_name.setText(categoryModel.getName_category());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(mContext.getApplicationContext(),SubCategoryActivity.class);
                int id=categoryModel.getId();
                intent.putExtra("categoryId",id+"");
                intent.putExtra("categoryName",categoryModel.getName_category());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.getApplicationContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        public RecyclerView recyclerView;
        private TextView category_name;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            category_name = itemView.findViewById(R.id.txtCategory_name);
            recyclerView = itemView.findViewById(R.id.search_recyclerView);
        }
    }
}
