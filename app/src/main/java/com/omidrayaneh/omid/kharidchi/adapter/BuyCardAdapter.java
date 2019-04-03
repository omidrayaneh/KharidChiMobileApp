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
import com.omidrayaneh.omid.kharidchi.activity.BuyActivity;
import com.omidrayaneh.omid.kharidchi.activity.MainActivity;
import com.omidrayaneh.omid.kharidchi.activity.ShowImageActivity;
import com.omidrayaneh.omid.kharidchi.model.product;

import java.util.List;

public class BuyCardAdapter extends RecyclerView.Adapter<BuyCardAdapter.BuyViewHolder> {
     private String stringRequest;

    private List<product> productList;
    private Context mContext;

    public BuyCardAdapter(List<product> productList, Context mContext) {
        this.productList = productList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BuyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_buy, parent, false);
        return new BuyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyViewHolder holder, int position) {
        final product products = productList.get(position);
        Glide.with(mContext).load(products.getImage_url()).diskCacheStrategy(DiskCacheStrategy.ALL).
                centerCrop().error(R.drawable.ic_shopping_cart).into(holder.imageView_buy);

        holder.name_buy.setText(products.getName_product());
        int price=Integer.parseInt(products.getPrice());
        int discount=Integer.parseInt(products.getDiscont());
        if (discount!=0)
        {
            holder.price_buy.setText(String.valueOf(discount)+" "+mContext.getString(R.string.toman));
        }
        else {
            holder.price_buy.setText(String.valueOf(price)+" "+mContext.getString(R.string.toman));
        }
        holder.qty_buy.setText(mContext.getString(R.string.qty)+" "+products.getQty());
        ( holder).imageView_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext,ShowImageActivity.class);
                intent.putExtra("image_url",products.getImage_url());
                intent.putExtra("id",products.getId()+"");

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.getApplicationContext().startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class BuyViewHolder extends RecyclerView.ViewHolder {

        private TextView name_buy, price_buy,qty_buy;
        private ImageView imageView_buy;
        private BuyViewHolder(View itemView) {
            super(itemView);
            name_buy = itemView.findViewById(R.id.txtCardProduct_buy);
            price_buy = itemView.findViewById(R.id.txtCardPrice_buy);
            qty_buy = itemView.findViewById(R.id.txtqty_buy);
            imageView_buy = itemView.findViewById(R.id.imageCard_buy);

            if (productList.size()>0) {
                int cost=Integer.parseInt(MainActivity.cost.replace(".0",""));
                int min_cost=Integer.parseInt(MainActivity.min_cost.replace(".0",""));
                BuyActivity.txtInvoice.setText(( mContext.getString(R.string.total_invoice) + Total()+" "+ mContext.getString(R.string.toman)));
                if (TotalDiscount()==0){
                    BuyActivity.txtDiscount.setText(( mContext.getString(R.string.discount)+" 0 "));
                }
                else {
                    BuyActivity.txtDiscount.setText(( mContext.getString(R.string.discount)+"  "+ TotalDiscount()+" "+ mContext.getString(R.string.toman)));
                }

                if (Total()-TotalDiscount()>min_cost)
                {

                    BuyActivity.txtCost.setText(( mContext.getString(R.string.shipp) + " 0 "));
                    BuyActivity.totalCard_buy.setText(( mContext.getString(R.string.total_invoice_without_discount) +( Total()-TotalDiscount())+" "+ mContext.getString(R.string.toman)));

                }
                else
                {
                    BuyActivity.txtCost.setText(( mContext.getString(R.string.shipp) + cost+" "+ mContext.getString(R.string.toman)));
                    BuyActivity.totalCard_buy.setText(( mContext.getString(R.string.total_invoice_without_discount) +( Total()-TotalDiscount()+cost)+" "+ mContext.getString(R.string.toman)));

                }
            }
        }
    }

    private int Total() {
        int x = 0;
        for (int i = 0; i < productList.size(); i++) {
            x += (Integer.parseInt(productList.get(i).getPrice())) * Integer.parseInt(productList.get(i).getQty());
        }

        return x;
    }
    private int TotalDiscount() {
        int x = 0;
        for (int i = 0; i < productList.size(); i++) {
                int price=Integer.parseInt(productList.get(i).getPrice());
                int discount=Integer.parseInt(productList.get(i).getDiscont());
                int qty=Integer.parseInt(productList.get(i).getQty());
                if (discount!=0){
                    x += (price-discount)*qty;
                }
                else{
                    x += 0;
                }
        }

        return x;
    }

}
