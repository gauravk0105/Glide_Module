package com.example.glide_demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MyPreloadModelProvider implements ListPreloader.PreloadModelProvider {

    List<Store> lst;
    Context cont;

    public MyPreloadModelProvider(List<Store> lst,Context cont)
    {
        this.lst = lst;
        this.cont = cont;
    }

    @Override
    @NonNull
    public List<Store> getPreloadItems(int position) {
        return lst.subList(position, position + 1);
    }

    @Nullable
    @Override
    public RequestBuilder<?> getPreloadRequestBuilder(@NonNull Object item) {
        Store store = (Store) item;
        return
                Glide.with(cont)
                        .load(store.getUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).override(1024,768);
    }

    @Nullable
    public RequestBuilder getPreloadRequestBuilder(Store store) {
        return
                Glide.with(cont)
                        .load(store.getUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).override(1024,768);
    }
}