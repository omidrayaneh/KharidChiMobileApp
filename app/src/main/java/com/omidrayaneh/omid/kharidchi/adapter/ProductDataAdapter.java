package com.omidrayaneh.omid.kharidchi.adapter;



import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.omidrayaneh.omid.kharidchi.Interfacers.getQty;
import com.omidrayaneh.omid.kharidchi.activity.ShowImageActivity;
import com.omidrayaneh.omid.kharidchi.helper.MySQLiteHelper;
import com.omidrayaneh.omid.kharidchi.activity.MainActivity;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.model.product;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductDataAdapter extends RecyclerView.Adapter{

    private List<product> itemsList;
    private Context mContext;
    String all_qty ;


    public ProductDataAdapter(Context context, List<product> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;

    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_main_product, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int i) {


        final product singleItem = itemsList.get(i);

        ((SingleItemRowHolder) holder).id.setText(singleItem.getId()+"");
        ((SingleItemRowHolder) holder).tvTitle.setText(singleItem.getName_product());

        if (singleItem.getDiscont().equals("0")) {
            {
                ((SingleItemRowHolder) holder).tvDiscont.setVisibility(View.INVISIBLE);
                ((SingleItemRowHolder) holder).tvPrice.setText(mContext.getString(R.string.price_whitout_discount) +singleItem.getPrice()+" "+mContext.getString(R.string.toman));
            }
        }
        else
        {
            int price=Integer.parseInt(singleItem.getPrice());
            int discont=Integer.parseInt(singleItem.getDiscont());
            int total=price-(discont*price)/100;
            ((SingleItemRowHolder) holder).tvDiscont.setVisibility(View.VISIBLE);

            ((SingleItemRowHolder) holder).tvPrice.setText(mContext.getString(R.string.price_whitout_discount)+String.valueOf(price)+" "+mContext.getString(R.string.toman));
            (((SingleItemRowHolder) holder)).tvPrice.setTextColor(mContext.getResources().getColor(R.color.red));

            ((SingleItemRowHolder) holder).tvDiscont.setText(mContext.getString(R.string.price_whitout_discount)+ String.valueOf(total)+" "+mContext.getString(R.string.toman));

            ((SingleItemRowHolder)holder).tvPrice.setPaintFlags(((SingleItemRowHolder)holder).tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }
        ((SingleItemRowHolder) holder).tvOwner.setText(mContext.getString(R.string.owner_by)+singleItem.getOwner());


        Glide.with(mContext)
                .load(singleItem.getImage_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.splashscreen)
                .into(((SingleItemRowHolder) holder).itemImage);
        ((SingleItemRowHolder ) holder).itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext,ShowImageActivity.class);
                intent.putExtra("image_url",singleItem.getImage_url());
                intent.putExtra("id",singleItem.getId()+"");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.getApplicationContext().startActivity(intent);
            }
        });

        if (!singleItem.getQty().equals("0"))
        {
            ((SingleItemRowHolder) holder).txt_finished.setVisibility(View.INVISIBLE);
            ((SingleItemRowHolder) holder).btnAddCart.setEnabled(true);
        }
        else
        {
            ((SingleItemRowHolder) holder).txt_finished.setVisibility(View.VISIBLE);
            ((SingleItemRowHolder) holder).btnAddCart.setEnabled(false);
        }
        if(!singleItem.getDiscont().equals("0")){
            ((SingleItemRowHolder) holder).discount.setVisibility(View.VISIBLE);
            ((SingleItemRowHolder) holder).discount.setText(" "+singleItem.getDiscont()+" % "+mContext.getString(R.string.discountShow)+" ");
        }
        else{
            ((SingleItemRowHolder) holder).discount.setVisibility(View.INVISIBLE);
        }
        ((SingleItemRowHolder) holder).btnAddCart.setOnClickListener(view -> {
            int price=Integer.parseInt(singleItem.getPrice());
            int discont=Integer.parseInt(singleItem.getDiscont());
            int total=price-(discont*price)/100;
            GetQtyProductRetrofit(singleItem.getId(),singleItem.getName_product(),singleItem.getPrice(),singleItem.getImage_url(),  singleItem.getOwner(), total+"");
        });

    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle,tvPrice,tvOwner,tvDiscont,txt_finished,discount,id;
        protected ImageView itemImage;
        Button btnAddCart;

        SingleItemRowHolder(final View view) {
            super(view);
            this.id=view.findViewById(R.id.tvId);
            this.tvTitle =  view.findViewById(R.id.tvTitle);
            this.itemImage =  view.findViewById(R.id.itemImage);
            this.tvPrice=view.findViewById(R.id.tvPrice);
            this.tvDiscont=view.findViewById(R.id.tvDiscont);
            this.tvOwner=view.findViewById(R.id.tvOwner);
            this.btnAddCart=view.findViewById(R.id.add_to_card_list);
            this.txt_finished=view.findViewById(R.id.txt_finished);
            this.discount=view.findViewById(R.id.discount);


        }
    }


    public void GetQtyProductRetrofit(int id,String name,String price,String url,String owner,String discount) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").
                addConverterFactory(GsonConverterFactory.create()).build();
        getQty registerInterface = retrofit.create(getQty.class);
        Call call = registerInterface.getQtyCount(name);
        call.enqueue(new Callback() {


            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                //String stringRequest = new Gson().toJson(response.body());
                 all_qty=(response.body().toString());
                MySQLiteHelper db = new MySQLiteHelper(mContext);
                try
                {
                    int totalQty=Integer.parseInt(all_qty);
                    product items=db.selectProduct(name);
                    int QTY=Integer.parseInt(items.getQty());
                    if (totalQty-1>=QTY) {
                        items.setQty((QTY+1)+"");
                        db.updateProduct(items);
                    }
                    else{
                        Toast.makeText(mContext, "تعداد درخواست  بیشتر از موجودی است", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e) {
                    int totalQty=Integer.parseInt(all_qty);
                    //if product not to card run this code
                    if (totalQty>0) {
                        db.addProduct(new product(id,name, price, url, all_qty, "1", owner, discount));
                        MainActivity.mCartItemCount = db.getAllProduct().size();
                        MainActivity.setupBadge();
                    }
                    else {
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