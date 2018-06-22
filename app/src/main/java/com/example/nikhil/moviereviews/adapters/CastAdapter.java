package com.example.nikhil.moviereviews.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.nikhil.moviereviews.CastPOJO.CastItem;
import com.example.nikhil.moviereviews.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by NIKHIL on 22-06-2018.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

       Context mcontext;
       List<CastItem> castLists;

    public CastAdapter(Context mcontext, List<CastItem> castLists) {
        this.mcontext = mcontext;
        this.castLists = castLists;
    }

    @Override
    public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view =inflater.inflate(R.layout.cast_images,parent,false);
        return new CastAdapter.CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CastViewHolder holder, int position) {

      //  holder.textView.setText(castLists.get(position).getName());

       // holder.textView.setText(castLists.get(position).getName());

        String urlpath = castLists.get(position).getProfilePath();

        String final_url = "https://image.tmdb.org/t/p/w185/"+urlpath;


        Log.d("URLISHERE",final_url);
        String temp = " https://image.tmdb.org/t/p/w185//lrhth7yK9p3vy6p7AabDUM1THKl.jpg";

     //   temp = final_url;


/*
              Glide.with(mcontext).load(final_url)
                      .thumbnail(0.5f)
                      .crossFade()
                      .diskCacheStrategy(DiskCacheStrategy.ALL)
                      .into(holder.imageViews);

*/

        Glide.with(mcontext).load(final_url).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.imageViews) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mcontext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.imageViews.setImageDrawable(circularBitmapDrawable);
            }
        });


    }

    @Override
    public int getItemCount() {
        return castLists.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder
    {

       TextView textView;
        ImageView imageViews;

        public CastViewHolder(View itemView) {
            super(itemView);

             //textView = itemView.findViewById(R.id.cast_tv);


            imageViews = itemView.findViewById(R.id.cast_img);

           // textView = itemView.findViewById(R.id.cast_tv);
        }
    }
}
