package com.abraham24.beautyofindonesia.Gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KOCHOR on 9/8/2017.
 */

public class GsonWisata {

    @SerializedName("data")
    public List<DataWisata> dataWisata;

    public class DataWisata {

        @SerializedName("title")
        public String title;

        @SerializedName("id_data")
        public Integer id_data;


        @SerializedName("description")
        public String desc;

        @SerializedName("url_image")
        public String url_image;

        @SerializedName("created_at")
        public String created_at;

        @SerializedName("rate")
        public Float rate;
    }
}
