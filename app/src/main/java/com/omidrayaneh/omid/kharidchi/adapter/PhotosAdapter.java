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
import com.omidrayaneh.omid.kharidchi.app.Config;
import com.omidrayaneh.omid.kharidchi.model.Photos;
import com.omidrayaneh.omid.kharidchi.model.product;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {

    private List<Photos> photosList;
    private Context mContext;

    public PhotosAdapter(List<Photos> photosList, Context mContext) {
        this.photosList = photosList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_photos, parent, false);
        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        final Photos photos = photosList.get(position);

        String url= Config.url_base+photos.getWeb_url();

        Glide.with(mContext)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.splashscreen)
                .into( holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(mContext.getApplicationContext()).load(url)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(ShowImageActivity.zoomImage);
            }
        });
    }



    @Override
    public int getItemCount() {
        return photosList.size();
    }

    public class PhotosViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private PhotosViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_photos);

        }
    }


}
