package com.abraham24.beautyofindonesia.Gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KOCHOR on 9/8/2017.
 */

public class GsonDetail {
    @SerializedName("status")
    private boolean status;
    @SerializedName("status_code")
    private int status_code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Data> data;

    public boolean isStatus() {
        return status;
    }

    public int getStatus_code() {
        return status_code;
    }

    public String getMessage() {
        return message;
    }

    public List<Data> getData() {
        return data;
    }


    public class Data {
        @SerializedName("id_data")
        private int id_data;
        @SerializedName("title")
        private String title;
        @SerializedName("description")
        private String description;
        @SerializedName("url_image")
        private String url_image;
        @SerializedName("rate")
        private float rate;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("category")
        private String category;
        @SerializedName("Location")
        private String location;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getId_data() {
            return id_data;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getUrl_image() {
            return url_image;
        }

        public float getRate() {
            return rate;
        }

        public String getCreated_at() {
            return created_at;
        }

    }
}
