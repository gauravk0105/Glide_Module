package com.example.glide_demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.ObjectKey;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Store> mDataset;
    private Context cont;
    private RequestManager glide;


    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView show_info;
        public ImageView show_image;
        public MyViewHolder(View view) {
            super(view);
            show_image = (ImageView) view.findViewById(R.id.image);
            show_info = (TextView) view.findViewById(R.id.info);
        }
    }

    public MyAdapter(RequestManager glide,List<Store> myDataset,Context cont) {
        this.mDataset = myDataset;
        this.cont = cont;
        this.glide = glide;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    @SuppressLint("CheckResult")
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Store store = mDataset.get(position);
//        .signature(new ObjectKey(System.currentTimeMillis())).

        holder.show_info.setText(store.getInfo());
        glide.load(store.getUrl()).thumbnail(0.1f).override(1024,768).diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontTransform().into(holder.show_image);
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // Return the size of your dataset (invoked by the layout manager)

}