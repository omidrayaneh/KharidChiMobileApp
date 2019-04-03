package com.omidrayaneh.omid.kharidchi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPickerListener;
import com.omidrayaneh.omid.kharidchi.activity.ShowImageActivity;
import com.omidrayaneh.omid.kharidchi.helper.MySQLiteHelper;
import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.activity.CardActivity;
import com.omidrayaneh.omid.kharidchi.model.product;

import java.util.List;

public class CardProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<product> productList;
    private Context mContext;


    public CardProductAdapter(List<product> productList, Context mContext) {
        this.productList = productList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card, parent, false);

        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        final product products = productList.get(position);
        final MainViewHolder vh = (MainViewHolder) holder;

        vh.name.setText(products.getName_product());

        int price = Integer.parseInt(products.getPrice());
       int discount =Integer.parseInt(products.getDiscont());
        if (price-discount != 0) {
            vh.discount.setVisibility(View.VISIBLE);
            vh.price.setText(mContext.getString(R.string.price_whitout_discount) + String.valueOf(discount) + " " + mContext.getString(R.string.toman));
            vh.discount.setText(mContext.getString(R.string.price_whitout_discount) + price + " " + mContext.getString(R.string.toman));
            vh.discount.setPaintFlags(vh.discount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            vh.discount.setVisibility(View.INVISIBLE);
            vh.price.setText(mContext.getString(R.string.price_whitout_discount) + String.valueOf(price) + " " + mContext.getString(R.string.toman));
        }
        Glide.with(mContext).load(products.getImage_url()).diskCacheStrategy(DiskCacheStrategy.ALL).
                centerCrop().error(R.drawable.splashscreen).into( vh.imageView);
        vh.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext,ShowImageActivity.class);
                intent.putExtra("image_url",products.getImage_url());
                intent.putExtra("id",products.getId()+"");

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.getApplicationContext().startActivity(intent);
            }
        });

        vh.txt_qty.setText(products.getQty());

        final MySQLiteHelper db = new MySQLiteHelper(mContext);

        vh.ic_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int totalQty = Integer.parseInt(products.getAll_qty());
                int qty = Integer.parseInt(products.getQty());
                qty++;
                if (totalQty >= qty) {
                    vh.txt_qty.setText(qty+"");
                    products.setQty(qty + "");
                    db.updateProduct(products);
                    CardActivity.txtTotal.setText(mContext.getResources().getString(R.string.total_invoice) + Total() + " " + mContext.getString(R.string.toman));
                    CardActivity.fab.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(mContext, "تعداد درخواست  بیشتر از موجودی است", Toast.LENGTH_SHORT).show();

                }
            }
        });

        vh.ic_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int totalQty = Integer.parseInt(products.getAll_qty());
                int qty = Integer.parseInt(products.getQty());
                qty--;
                if (qty < 1)
                    return;
                vh.txt_qty.setText(qty+"");
                products.setQty(qty + "");
                db.updateProduct(products);
                CardActivity.txtTotal.setText(mContext.getResources().getString(R.string.total_invoice) + Total() + " " + mContext.getString(R.string.toman));
                CardActivity.fab.setVisibility(View.VISIBLE);

            }
        });

        vh.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySQLiteHelper db = new MySQLiteHelper(mContext);
                db.deleteProduct(productList.get(position));
                productList.remove(position);
                CardActivity.mCartItemCount = productList.size();
                CardActivity.setupBadge();

                notifyDataSetChanged();
                if (productList.size() == 0) {

                    CardActivity.txtTotal.setText(R.string.card_empty);
                    CardActivity.txtTotal.setBackgroundColor(view.getResources().getColor(R.color.empty));
                    CardActivity.fab.setVisibility(View.GONE);
                } else {
                    CardActivity.txtTotal.setVisibility(View.VISIBLE);
                    CardActivity.txtTotal.setBackgroundColor(view.getResources().getColor(R.color.add_to_card));
                    CardActivity.txtTotal.setText(view.getResources().getString(R.string.total_invoice) + Total() + " " + mContext.getString(R.string.toman));
                    CardActivity.fab.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    private class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView name, price, discount, remove, txt_qty;
        private ImageView imageView, ic_add, ic_remove;

        private MainViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtCardProduct);
            discount = itemView.findViewById(R.id.txtDiscount);
            price = itemView.findViewById(R.id.txtCardPrice);
            imageView = itemView.findViewById(R.id.imageCard);
            remove = itemView.findViewById(R.id.txt_remove);

            ic_add = itemView.findViewById(R.id.ic_add);
            ic_remove = itemView.findViewById(R.id.ic_minus);
            txt_qty = itemView.findViewById(R.id.txt_qty);
            if (productList.size() == 0) {
                CardActivity.txtTotal.setText(R.string.card_empty);
                CardActivity.txtTotal.setBackgroundColor(itemView.getResources().getColor(R.color.empty));

            } else {
                CardActivity.txtTotal.setVisibility(View.VISIBLE);
                CardActivity.txtTotal.setBackgroundColor(itemView.getResources().getColor(R.color.add_to_card));
                CardActivity.txtTotal.setText(itemView.getResources().getString(R.string.total_invoice) + Total() +
                        " " + mContext.getString(R.string.toman));
                CardActivity.fab.setVisibility(View.VISIBLE);
            }

        }
    }

    public int Total() {
        int x = 0;
        for (int i = 0; i < productList.size(); i++) {
            int dis=Integer.parseInt(productList.get(i).getDiscont());
            int price=Integer.parseInt(productList.get(i).getPrice());
            int qty=Integer.parseInt(productList.get(i).getQty());
            if (dis!=0){
                x += (dis)*qty;
            }
            else{
                x += (price)*qty;
            }
        }
        return x;
    }

}
