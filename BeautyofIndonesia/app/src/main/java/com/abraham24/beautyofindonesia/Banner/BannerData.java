package com.abraham24.beautyofindonesia.Banner;

import java.util.ArrayList;

/**
 * Created by KOCHOR on 9/6/2017.
 */

public class BannerData {

    private ArrayList<Integer> id_data = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> description = new ArrayList<>();
    private ArrayList<String> url_image = new ArrayList<>();

    public void clear() {
        id_data.clear();
        title.clear();
        description.clear();
        url_image.clear();
    }

    void addId_Data(Integer id_data) {
        this.id_data.add(id_data);
    }

    void addTitle(String title) {
        this.title.add(title);
    }

    void addDescription(String description) {
        this.description.add(description);
    }

    void addUrl_Image(String url_image) {
        this.url_image.add(url_image);
    }

    public int getId_Data(Integer position) {
        return id_data.get(position);
    }

    public void addData(Integer id_data, String title, String description, String url_image) {
        addId_Data(id_data);
        addTitle(title);
        addDescription(description);
        addUrl_Image(url_image);
    }

    public String getTitle(Integer position) {
        return title.get(position);
    }

    public String getDescription(Integer position) {
        return description.get(position);
    }

    public String url_image(Integer position) {
        return url_image.get(position);
    }


}
