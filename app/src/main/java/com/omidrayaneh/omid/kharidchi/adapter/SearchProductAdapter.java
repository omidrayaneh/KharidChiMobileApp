package com.omidrayaneh.omid.kharidchi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.omidrayaneh.omid.kharidchi.Interfacers.getQty;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.activity.MainActivity;
import com.omidrayaneh.omid.kharidchi.activity.SearchActivity;
import com.omidrayaneh.omid.kharidchi.activity.SearchCategoryActivity;
import com.omidrayaneh.omid.kharidchi.activity.ShowImageActivity;
import com.omidrayaneh.omid.kharidchi.helper.MySQLiteHelper;
import com.omidrayaneh.omid.kharidchi.model.product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.ViewHolder> {
    private List<product> productList;
    private Context mContext;
    String all_qty;
    int id;

    public SearchProductAdapter() {
    }

    public SearchProductAdapter(List<product> productList, Context mContext) {
        this.productList = productList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search_product, parent, false);
        return new SearchProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final product singleItem = productList.get(position);

        holder.tvName.setText(productList.get(position).getName_product());
        holder.tvOwner.setText(mContext.getString(R.string.owner_by)+" "+productList.get(position).getOwner());
        int id=singleItem.getId();
        int price = Integer.parseInt(singleItem.getPrice());
        int discount = (Integer.parseInt(singleItem.getDiscont()) * Integer.parseInt(singleItem.getPrice())) / 100;
        int total = (price - discount);

        if (discount != 0) {

            holder.tvPrice.setText(mContext.getString(R.string.price_whitout_discount) + String.valueOf(price) + " " + mContext.getString(R.string.toman));
            holder.tvPrice.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.tvPrice.setPaintFlags(holder.tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvDiscount.setText(mContext.getString(R.string.price_whitout_discount) + total + " " + mContext.getString(R.string.toman));


        } else {
            holder.tvDiscount.setVisibility(View.INVISIBLE);
            holder.tvPrice.setText(mContext.getString(R.string.price_whitout_discount) + String.valueOf(price - discount) + " " + mContext.getString(R.string.toman));
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ShowImageActivity.class);
                intent.putExtra("image_url", singleItem.getImage_url());
                intent.putExtra("id", id+"");

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.getApplicationContext().startActivity(intent);
            }
        });
        Glide.with(mContext).load(productList.get(position).getImage_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
                .centerCrop().error(R.drawable.ic_shopping_cart).into((holder).imageView);
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetQtyProductRetrofit(id,singleItem.getName_product(), singleItem.getPrice(), singleItem.getImage_url(), singleItem.getOwner(), singleItem.getDiscont());
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvPrice, tvDiscount, tvName,tvOwner;
        Button btnAdd;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.search_itemImage);
            tvPrice = itemView.findViewById(R.id.search_tvPrice);
            tvName = itemView.findViewById(R.id.search_tvTitle);
            tvDiscount = itemView.findViewById(R.id.search_tvDiscont);
            tvOwner = itemView.findViewById(R.id.search_owner);
            btnAdd = itemView.findViewById(R.id.search_add_to_card_list);
        }
    }


    public void GetQtyProductRetrofit(int id,String name, String price, String url, String owner, String discount) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create()).build();
        getQty registerInterface = retrofit.create(getQty.class);
        Call call = registerInterface.getQtyCount(name);
        call.enqueue(new Callback() {


            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                //String stringRequest = new Gson().toJson(response.body());
                all_qty = (response.body().toString());
                MySQLiteHelper db = new MySQLiteHelper(mContext);
                try {
                    int totalQty = Integer.parseInt(all_qty);
                    product items = db.selectProduct(name);
                    int QTY = Integer.parseInt(items.getQty());
                    if (totalQty - 1 >= QTY) {
                        items.setQty((QTY + 1) + "");
                        db.updateProduct(items);
                    } else {
                        Toast.makeText(mContext, "تعداد درخواست  بیشتر از موجودی است", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    int totalQty = Integer.parseInt(all_qty);
                    //if product not to card run this code
                    if (totalQty > 0) {
                        int price_ = Integer.parseInt(price);
                        int dis_ = (Integer.parseInt(discount) * price_) / 100;
                        int dis = (price_ - dis_);

                        db.addProduct(new product(id,name, price, url, all_qty, "1", owner, dis + ""));
                        SearchActivity.mCartItemCount = db.getAllProduct().size();
                        SearchActivity.setupBadge();
                        SearchCategoryActivity.mCartItemCount = db.getAllProduct().size();
                        SearchCategoryActivity.setupBadge();
                    } else {
                        Toast.makeText(mContext, "تعداد درخواست  بیشتر از موجودی است", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }

        });
    }

}
