package com.example.glide_demo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.util.FixedPreloadSizeProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private final int imageWidthPixels = 1024;
    private final int imageHeightPixels = 768;
    List<Store> myDataSet;
    public static String[] url_image = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg",
    };
    long t1=0,t2=0;

    /*
        Url from Which the image is requested
    */
    String url="http://uncleanly-membrane.000webhostapp.com/Image.png";
    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img = (ImageView) findViewById(R.id.img);
        //Glide.with(this).diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontTransform().thumbnail(thumbnailRequest).load(url).centerCrop().into(img);
        //Log.d("Optimise : ", "onCreate: "+(t2-t1));
        Glide.with(this)
                .load(url)
                .thumbnail(0.1f)
                /*
                    Attaching Signature
                */
                .signature(new ObjectKey(System.currentTimeMillis()))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .dontTransform()
                .into(img);

        List<Store> myDataSet = new ArrayList<Store>();
        for(int i=0;i<url_image.length;i++)
            myDataSet.add(new Store(url_image[i],"Image "+i));
        for(int i=4;i<=100;i++)
            myDataSet.add(new Store("https://via.placeholder.com/300/000000/FFFFFF/?text=demo"+i,"Image "+10+i));
//        ListPreloader.PreloadSizeProvider sizeProvider =
//                new FixedPreloadSizeProvider(imageWidthPixels, imageHeightPixels);
//        PreloadModelProvider modelProvider = new PreloadModelProvider(myDataSet,getApplicationContext());
//        RecyclerViewPreloader<Store> preloader = new RecyclerViewPreloader<Store>(
//                Glide.with(this), modelProvider, sizeProvider, 30 /*maxPreload*/);
//
//
//        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
//
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        mAdapter = new MyAdapter(Glide.with(this),myDataSet,getApplicationContext());
//       // recyclerView.addOnScrollListener(preloader);
//        recyclerView.setAdapter(mAdapter);


    }


    private class PreloadModelProvider implements ListPreloader.PreloadModelProvider {

        List<Store> lst;
        Context cont;

        public PreloadModelProvider(List<Store> lst,Context cont)
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
                            .load(store.getUrl()).override(1024,768).diskCacheStrategy(DiskCacheStrategy.ALL).dontTransform();
        }

        @Nullable
        public RequestBuilder getPreloadRequestBuilder(Store store) {
            return
                    Glide.with(cont)
                            .load(store.getUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).override(1024,768);
        }
    }
}
