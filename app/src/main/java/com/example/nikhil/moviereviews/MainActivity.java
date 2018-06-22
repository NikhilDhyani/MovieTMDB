package com.example.nikhil.moviereviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nikhil.moviereviews.PopularMovies.PopularMovies;
import com.example.nikhil.moviereviews.PopularMovies.Result;
import com.example.nikhil.moviereviews.adapters.MultiViewTypeAdapter;
import com.example.nikhil.moviereviews.apis.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView;


        initViews();

    }

        private void initViews() {

            recyclerView = findViewById(R.id.rv_id);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

            //recyclerView.setAdapter(getApplicationContext(),userr);
            loadJson();
        }

        private void loadJson() {


            //Create API
            Retrofit retrofit = new Retrofit.Builder().baseUrl(API.base_url).addConverterFactory(GsonConverterFactory.create()).build();


            API api = retrofit.create(API.class);

            //Call method

            //Call<List<Result>> call = api.getMovies();

            Call<PopularMovies> call = api.getMovies();


            call.enqueue(new Callback<PopularMovies>() {
                @Override
                public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
                    PopularMovies movies = response.body();

                    List<Result> results = movies.getResults();

//                Log.d("Page",movies.getPage().toString());
//                Log.d("Total_result",movies.getTotalResults().toString());

//                Log.d("Title",results.get(0).getTitle());

                    recyclerView.setAdapter(new MultiViewTypeAdapter(MainActivity.this,results));



                }

                @Override
                public void onFailure(Call<PopularMovies> call, Throwable t) {

                }
            });



        }
    }

