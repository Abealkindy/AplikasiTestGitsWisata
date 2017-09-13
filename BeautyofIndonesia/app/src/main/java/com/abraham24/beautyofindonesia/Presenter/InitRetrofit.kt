package com.abraham24.beautyofindonesia.Presenter

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by KOCHOR on 9/6/2017.
 */
class InitRetrofit {

    fun getInitRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://entry.sandbox.gits.id/api/alamku/index.php/api/post/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getUpload(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://entry.sandbox.gits.id/api/alamku/index.php/api/post/data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getInitInstance(): ApiService {
        return getInitRetrofit().create(ApiService::class.java)
    }


}

interface ApiService {
    @FormUrlEncoded
    @POST("account")
    fun request_register(
            @Field("first_name") firstname: String,
            @Field("last_name") lastname: String,
            @Field("username") username: String,
            @Field("password") password: String,
            @Field("bdate") bdate: String,
            @Field("gender") gender: String,
            @Field("phone") phone: String
    ): Call<ResponseInsert>


}

class ResponseInsert {
    var status: Boolean? = null
}
