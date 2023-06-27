package com.example.listedapp

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitService {

    @GET("dashboardNew")
    suspend fun getData(@Header("Authorization") token: String): Response<ResponseModel>

    companion object{
        var HOST_URL:String="https://api.inopenapp.com/api/v1/";
        var retrofitService:RetrofitService?=null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(HOST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

}