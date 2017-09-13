package com.abraham24.beautyofindonesia.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.abraham24.beautyofindonesia.Gson.GsonWisata;
import com.abraham24.beautyofindonesia.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by KOCHOR on 9/8/2017.
 */

public class AdapterWisata extends RecyclerView.Adapter<AdapterWisata.ViewHolder> {

    List<GsonWisata.DataWisata> gsonwisata;
    Context context;

    public AdapterWisata(Context applicationContext, List<GsonWisata.DataWisata> dataWisata) {

        context = applicationContext;
        gsonwisata = dataWisata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wadah_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.nama_wisata.setText(gsonwisata.get(position).title);
        Picasso.with(context).load("http://entry.sandbox.gits.id/api/alamku/uploads/images/" + gsonwisata.get(position).url_image).into(new Target() {

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.image_wisata.setBackground(new BitmapDrawable(context.getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
                Log.d("TAG", "FAILED");
            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {
                Log.d("TAG", "Prepare Load");
            }
        });
//        holder.card_wisata.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(context.getApplicationContext(), DetailActivity.class);
////                intent.putExtra("title", gsonwisata.get(position).title);
////                intent.putExtra("describe", gsonwisata.get(position).desc);
////                intent.putExtra("gambar", gsonwisata.get(position).url_image);
////                intent.putExtra("rate", gsonwisata.get(position).rate);
////                intent.putExtra("time", gsonwisata.get(position).created_at);
//                intent.putExtra("id", gsonwisata.get(position).id_data);
//                context.startActivity(intent);
//            }
//        });
        holder.rating_bar_wisata.setRating(gsonwisata.get(position).rate);

    }


    @Override
    public int getItemCount() {
        return gsonwisata.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        TextView nama_wisata;
        ImageView image_wisata;

        RatingBar rating_bar_wisata;

        protected ViewHolder(View itemView) {
            super(itemView);

            nama_wisata = (TextView) itemView.findViewById(R.id.text_wisata);
            image_wisata = (ImageView) itemView.findViewById(R.id.image_wisata);

            rating_bar_wisata = (RatingBar) itemView.findViewById(R.id.rbWisata);

        }
    }


}
