package com.omidrayaneh.omid.kharidchi.adapter;



import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.activity.SearchCategoryActivity;
import com.omidrayaneh.omid.kharidchi.model.prodact_category;

import java.util.List;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter {
    private List<prodact_category> prodactcategoryList;
    private Context mContext;
    int total_types;

    public RecyclerViewDataAdapter(Context context, List<prodact_category> prodactcategoryList) {
        this.prodactcategoryList = prodactcategoryList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_main_category, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        final String categoryName = prodactcategoryList.get(i).getname_category();

        List singleSectionItems = prodactcategoryList.get(i).getProduct();
        ((ItemRowHolder) holder).itemTitle.setText(categoryName);
        ProductDataAdapter itemListDataAdapter = new ProductDataAdapter(mContext, singleSectionItems);
        ((ItemRowHolder) holder).recycler_view_list.setHasFixedSize(true);
        ((ItemRowHolder) holder).recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        ((ItemRowHolder) holder).recycler_view_list.setAdapter(itemListDataAdapter);
        ((ItemRowHolder) holder).btnMore.setOnClickListener(v -> {

            String CategoryName=categoryName.toString();
            Intent intent = new Intent(mContext, SearchCategoryActivity.class);
            intent.putExtra("catName",CategoryName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.getApplicationContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return (null != prodactcategoryList ? prodactcategoryList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView itemTitle;
        protected RecyclerView recycler_view_list;
        protected Button btnMore;

        public ItemRowHolder(View view) {
            super(view);

            this.itemTitle = view.findViewById(R.id.itemTitle);
            this.recycler_view_list =  view.findViewById(R.id.recycler_view_list);
            this.btnMore=  view.findViewById(R.id.btnMore);

        }
    }
}