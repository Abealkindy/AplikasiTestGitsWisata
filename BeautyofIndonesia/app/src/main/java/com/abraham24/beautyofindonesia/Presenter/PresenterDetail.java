package com.abraham24.beautyofindonesia.Presenter;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by KOCHOR on 9/9/2017.
 */

public class PresenterDetail {
    PresenterDetailInterface interfaces;

    public PresenterDetail(PresenterDetailInterface interfaces) {
        this.interfaces = interfaces;
    }

    public void request_detail(final Context context, String id_data) {

        StringRequest stringRequest;
        RequestQueue requestQueue;

        requestQueue = Volley.newRequestQueue(context);



        stringRequest = new StringRequest(Request.Method.GET, "http://entry.sandbox.gits.id/api/alamku/index.php/api/get/detil/dataalam?itemid=" + id_data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {
                    interfaces.Response(response);

                } else {
                    Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();


            }
        });

        requestQueue.add(stringRequest);
    }
}
