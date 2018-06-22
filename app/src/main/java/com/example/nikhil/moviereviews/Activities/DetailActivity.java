package com.example.nikhil.moviereviews.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nikhil.moviereviews.CastPOJO.CastItem;
import com.example.nikhil.moviereviews.CastPOJO.Credits;
import com.example.nikhil.moviereviews.CastPOJO.RICast;
import com.example.nikhil.moviereviews.R;
import com.example.nikhil.moviereviews.adapters.CastAdapter;
import com.example.nikhil.moviereviews.apis.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NIKHIL on 22-06-2018.
 */


public class DetailActivity extends AppCompatActivity {

    ImageView imageView;

    RecyclerView recyclerView;

    TextView textView, title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.d_img);

        recyclerView = findViewById(R.id.d_rv_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        textView = findViewById(R.id.d_multiTv);
        title = findViewById(R.id.d_movie_name);


        //Receive data

        Intent intent = getIntent();

        int id = intent.getExtras().getInt("intVariable");
        Log.d("DDOS",Integer.toString(id));


      //  int idd = Integer.parseInt(id);
        //Make a call to api to get cast name

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.base_url).addConverterFactory(GsonConverterFactory.create()).build();

        API api = retrofit.create(API.class);

        Call<RICast> call = api.castishere(id);

        call.enqueue(new Callback<RICast>() {
            @Override
            public void onResponse(Call<RICast> call, Response<RICast> response) {

                RICast castObject = response.body(); //here we have an object RICast

                Credits credits = castObject.getCredits(); //Now, we have an object of type credits

                List<CastItem> castItems = credits.getCast();

                recyclerView.setAdapter(new CastAdapter(DetailActivity.this,castItems));




            }

            @Override
            public void onFailure(Call<RICast> call, Throwable t) {

            }
        });



        String Url = intent.getExtras().getString("Photo");

        String final_url = "https://image.tmdb.org/t/p/h632/" + Url;



        String Title = intent.getExtras().getString("Movie_Name");


        String description = intent.getExtras().getString("Desc");



        Glide.with(getApplicationContext()).load(final_url)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=cxLG2wtE7TM")));

                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
            }
        });

        textView.setText(description);

        textView.setMovementMethod(new ScrollingMovementMethod());

        title.setText(Title);

    }
}
