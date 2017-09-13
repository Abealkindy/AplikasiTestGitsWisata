package com.abraham24.beautyofindonesia;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.abraham24.beautyofindonesia.Adapter.AdapterWisata;
import com.abraham24.beautyofindonesia.Banner.BannerData;
import com.abraham24.beautyofindonesia.Gson.GsonWisata;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.events.OnBannerClickListener;
import ss.com.bannerslider.views.BannerSlider;

public class ListWisataActivity extends AppCompatActivity {


    StringRequest stringRequestList;
    RequestQueue requestQueueList;
    GsonWisata gson_wisata;


    int kategori;


    @BindView(R.id.sliderWisata)
    BannerSlider sliderWisata;
    @BindView(R.id.recycler_wisata)
    RecyclerView recyclerWisata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wisata);
        ButterKnife.bind(this);

        recyclerWisata = (RecyclerView) findViewById(R.id.recycler_wisata);
        GridLayoutManager grid = new GridLayoutManager(this, 2);
        recyclerWisata.setLayoutManager(grid);


        requestQueueList = Volley.newRequestQueue(this);
        requestVolley(1);


// Clear data for the new ones

    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_category) {

            final Dialog dialog = new Dialog(ListWisataActivity.this);
            dialog.setContentView(R.layout.wadah_alert);
            dialog.setTitle("Pilih kategori");
            dialog.setCancelable(true);
            RadioButton dataran_tinggi = (RadioButton) dialog.findViewById(R.id.radio_button_dataran_tinggi);
            RadioButton dataran_rendah = (RadioButton) dialog.findViewById(R.id.radio_button_dataran_rendah);
            RadioButton pantai = (RadioButton) dialog.findViewById(R.id.radio_button_pantai);

            dataran_tinggi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    kategori = 1;


                }
            });
            dataran_rendah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    kategori = 2;

                }
            });
            pantai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    kategori = 3;

                }
            });

            Button button_submit = (Button) dialog.findViewById(R.id.button_submit);
            button_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requestVolley(kategori);
                    dialog.dismiss();
                }
            });

            dialog.show();
//            return true;
        } else if (id == R.id.action_logout) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        } else if (id == R.id.action_upload) {
            String id_user = getIntent().getStringExtra("id_user");
            Intent a = new Intent(getApplicationContext(), Upload.class);

            a.putExtra("id_user_2", id_user);

            startActivity(a);

            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    private void requestVolley(int kategori) {
        String url = "http://entry.sandbox.gits.id/api/alamku/index.php/api/get/filter/dataalam?kategori=" + kategori;

        stringRequestList = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                sliderWisata = (BannerSlider) findViewById(R.id.sliderWisata);
                List<Banner> banners = new ArrayList<>();
                final BannerData bannerData = new BannerData();

                GsonBuilder bulder = new GsonBuilder();
                Gson gsonn = bulder.create();
                gson_wisata = gsonn.fromJson(response, GsonWisata.class);

                AdapterWisata adapter = new AdapterWisata(ListWisataActivity.this, gson_wisata.dataWisata);
                recyclerWisata.setAdapter(adapter);
                recyclerWisata.addItemDecoration(new DividerItemDecoration(2, dpToPx(5), true));

                recyclerOnClickMain(recyclerWisata, ListWisataActivity.this, gson_wisata);
//
                banners.clear();
                bannerData.clear();
                sliderWisata.removeAllBanners();

                for (int i = 0; i < 5; i++) {
                    bannerData.addData(
                            gson_wisata.dataWisata.get(i).id_data,
                            gson_wisata.dataWisata.get(i).title,
                            gson_wisata.dataWisata.get(i).desc,
                            gson_wisata.dataWisata.get(i).url_image
                    );
                    banners.add(new RemoteBanner("http://entry.sandbox.gits.id/api/alamku/uploads/images/" + gson_wisata.dataWisata.get(i).url_image));
                }

                sliderWisata.setBanners(banners);



                sliderWisata.setMustAnimateIndicators(true);

                sliderWisata.setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void onClick(int position) {


                        Intent a = new Intent(getApplicationContext(), DetailActivity.class);
                        a.putExtra("id_data", "" + bannerData.getId_Data(position));
                        startActivity(a);

                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListWisataActivity.this, "Maaf, internet anda lambat", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueueList.add(stringRequestList);
    }

    public static void recyclerOnClickMain(RecyclerView rvRecipe, final Context mContext, final GsonWisata gsonWisata) {
        rvRecipe.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {

                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);

                    Intent detail = new Intent(mContext.getApplicationContext(), DetailActivity.class);

//
                    detail.putExtra("id_data", "" + gsonWisata.dataWisata.get(position).id_data);

                    mContext.startActivity(detail);


                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });
    }


}
