package com.example.nikhil.moviereviews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public void onBindViewHolder(CastViewHolder holder, int position) {

        holder.textView.setText(castLists.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return castLists.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder
    {

        TextView textView;

        public CastViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.cast_tv);
        }
    }
}
