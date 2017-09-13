package com.abraham24.beautyofindonesia;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abraham24.beautyofindonesia.Gson.GsonDetail;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    String id_data;
    StringRequest stringRequest;
    RequestQueue requestQueue;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.rating_detail)
    RatingBar ratingDetail;
    @BindView(R.id.text_cret)
    TextView textCret;
    @BindView(R.id.text_kategori)
    TextView textKategori;
    @BindView(R.id.text_lokasi)
    TextView textLokasi;
    @BindView(R.id.text_desc)
    TextView textDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id_data = getIntent().getStringExtra("id_data");


        requestQueue = Volley.newRequestQueue(this);

        stringRequest = new StringRequest(Request.Method.GET, "http://entry.sandbox.gits.id/api/alamku/index.php/api/get/detil/dataalam?itemid=" + id_data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                GsonDetail detail = gson.fromJson(response, GsonDetail.class);

                Picasso.with(DetailActivity.this)
                        .load("http://entry.sandbox.gits.id/api/alamku/uploads/images/" + detail.getData().get(0).getUrl_image())
                        .placeholder(R.mipmap.ic_launcher_round)
                        .into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                toolbarLayout.setBackground(new BitmapDrawable(bitmap));
                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) {

                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }
                        });
                toolbarLayout.setTitle(detail.getData().get(0).getTitle());
                textCret.setText("Created at : " + detail.getData().get(0).getCreated_at());
                textKategori.setText("Category : " + detail.getData().get(0).getCategory());
                textLokasi.setText("Location : " + detail.getData().get(0).getLocation());
                textDesc.setText("Description : " + detail.getData().get(0).getDescription());
                ratingDetail.setRating(detail.getData().get(0).getRate());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, "Maaf, internet anda lambat", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
