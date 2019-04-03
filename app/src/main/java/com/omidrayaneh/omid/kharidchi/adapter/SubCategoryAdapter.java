package com.omidrayaneh.omid.kharidchi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.activity.SearchCategoryActivity;
import com.omidrayaneh.omid.kharidchi.activity.SubCategoryActivity;
import com.omidrayaneh.omid.kharidchi.model.CategoryModel;
import com.omidrayaneh.omid.kharidchi.model.product;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {
    private List<CategoryModel> categoryModels;
    private List<product> productsList;
    private Context mContext;
    private RecyclerView recyclerView;
    private SubCategoryAdapter adapter;
    int id=0;


    public SubCategoryAdapter(List<CategoryModel> categoryModels, Context mContext) {
        this.categoryModels = categoryModels;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_subcategory, parent, false);
        return new SubCategoryAdapter.SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        CategoryModel categoryModel=categoryModels.get(position);
        holder.category_name.setText(categoryModel.getName_category());
        holder.Id.setText(categoryModel.getId()+"");
        holder.end.setText(categoryModel.getEnd_category()+"");
        Glide.with(mContext).load(categoryModel.getImage_url()).diskCacheStrategy(DiskCacheStrategy.ALL).
                centerCrop().error(R.drawable.splashscreen).into(holder.category_image);
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView category_name, Id,end;
        private ImageView category_image;
        public SubCategoryViewHolder(View itemView) {
            super(itemView);
            category_name=itemView.findViewById(R.id.sub_category_name);
            Id =itemView.findViewById(R.id.sub_category_id);
            end =itemView.findViewById(R.id.end_category);
            category_image=itemView.findViewById(R.id.sub_categorythumbnail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int endOfCat=Integer.parseInt(end.getText().toString());
                    if (endOfCat==0){
                        Intent intent=new Intent(mContext.getApplicationContext(),SubCategoryActivity.class);
                        intent.putExtra("categoryId", Id.getText());
                        intent.putExtra("categoryName",category_name.getText().toString());

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.getApplicationContext().startActivity(intent);
                    }
                    else {
                        String CategoryName=category_name.getText().toString();
                        Intent i = new Intent(mContext, SearchCategoryActivity.class);
                        i.putExtra("catName",CategoryName);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.getApplicationContext().startActivity(i);
                    }


                }
            });
        }
    }





}
