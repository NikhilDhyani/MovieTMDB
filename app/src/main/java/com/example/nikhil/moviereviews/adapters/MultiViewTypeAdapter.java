package com.example.nikhil.moviereviews.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nikhil.moviereviews.Activities.DetailActivity;
import com.example.nikhil.moviereviews.PopularMovies.Result;
import com.example.nikhil.moviereviews.R;

import java.util.List;

/**
 * Created by NIKHIL on 22-06-2018.
 */

public class MultiViewTypeAdapter extends RecyclerView.Adapter<MultiViewTypeAdapter.myViewHolder>
{

    Context mcontext;
    List<Result> mphotos;

    public MultiViewTypeAdapter(Context mcontext, List<Result> mphotos) {
        this.mcontext = mcontext;
        this.mphotos = mphotos;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        //Here we the context
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.card_view,null,false);

        return new MultiViewTypeAdapter.myViewHolder(view);

    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {

        holder.tv.setText(mphotos.get(position).getTitle());
        String url = mphotos.get(position).getPosterPath();
        String final_url = "https://image.tmdb.org/t/p/h632/"+url;

        Log.d("MyUrl",final_url);


        //Setting onClickListner

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mcontext,DetailActivity.class);

                 int ido = mphotos.get(position).getId();
                // String s = Integer.toString(ido);
                 intent.putExtra("intVariable",ido);
                Log.d("IDIS",Integer.toString(ido));

                intent.putExtra("Movie_Name",mphotos.get(position).getTitle());
                //   intent.putExtra("Title",mphotos.get(position).getName());
                intent.putExtra("Photo",mphotos.get(position).getBackdropPath());
                //  intent.putExtra("id",mphotos.get(position).getId());
                intent.putExtra("Desc",mphotos.get(position).getOverview());
                mcontext.startActivity(intent);
            }
        });



        Glide.with(mcontext).load(final_url)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);

        //   holder.description.setText(mphotos.get(position).getOverview());


    }

    @Override
    public int getItemCount() {
        return mphotos.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv,description;
        ImageView imageView;

        CardView cardView;

        public myViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cv_id);


            tv = itemView.findViewById(R.id.ctv_id);
            imageView = itemView.findViewById(R.id.ctv_imageView);
            //  description = itemView.findViewById(R.id.Description);
        }
    }
}
