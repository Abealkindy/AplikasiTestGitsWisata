package com.abraham24.beautyofindonesia.Gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KOCHOR on 9/8/2017.
 */

public class GsonLogIn {

    @SerializedName("data")
    private List<Datass> datasses;

    public List<Datass> getDatasses() {
        return datasses;
    }

    public class Datass {

        @SerializedName("id_user")
        private String id_user;

        public String getId_user() {
            return id_user;
        }

        public void setId_user(String id_user) {
            this.id_user = id_user;
        }
    }
}
