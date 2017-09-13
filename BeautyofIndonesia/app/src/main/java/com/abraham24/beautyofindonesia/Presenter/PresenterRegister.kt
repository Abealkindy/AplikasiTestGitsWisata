package com.abraham24.beautyofindonesia.Presenter

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by KOCHOR on 9/6/2017.
 */
class PresenterRegister {

    var presenterView: PresenterModel? = null

    constructor(presenterView: PresenterModel?) {
        this.presenterView = presenterView
    }

    fun Input(firstname: String, lastname: String, username: String, password: String, bdate: String, gender: String, phone: String) {


        if (firstname.isNotEmpty() && lastname.isNotEmpty() && username.isNotEmpty()
                && password.isNotEmpty() && bdate.isNotEmpty() && gender.isNotEmpty() && phone.isNotEmpty()) {


            var getInit = InitRetrofit().getInitInstance()

            var request = getInit.request_register(firstname, lastname, username, password, bdate, gender, phone)

//                var progress = ProgressDialog(applicationContext)
//                progress.setMessage("loading")
//                progress.show()

            request.enqueue(object : Callback<ResponseInsert> {

                override fun onResponse(call: Call<ResponseInsert>?, response: Response<ResponseInsert>?) {

//                        progress.dismiss()
                    Log.d("response ", response?.message())
                    if (response != null) {
                        if (response.isSuccessful && response.body()?.status == true) {
//                            startActivity(Intent(applicationContext, MainActivity::class.java))
//                            finish()
                            var result = response.body()?.status
//                            var messages = response.body()?.msg

                            presenterView?.hasil(result!!)

                        }


                    }
                }

                override fun onFailure(call: Call<ResponseInsert>?, t: Throwable?) {

//                        progress.dismiss()
                    Log.d("Error Insert", t.toString())
                }

            })
        }
    }


}
