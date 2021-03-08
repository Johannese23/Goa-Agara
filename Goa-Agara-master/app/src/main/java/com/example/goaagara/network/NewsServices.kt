package com.example.goaagara.network

import com.example.goaagara.model.ResponseServer
import retrofit2.Call
import retrofit2.http.GET

interface NewsServices {

    @GET("v2/everything?q=apple&from=2021-02-24&to=2021-02-26&sortBy=popularity&apiKey=838ff6f536854b3ea2c8121ebe7d2390")
    fun getDataNewsApple(): Call<ResponseServer>

    @GET("v2/everything?q=tesla&from=2021-01-26&sortBy=publishedAt&apiKey=838ff6f536854b3ea2c8121ebe7d2390")
    fun getDataNewsTesla(): Call<ResponseServer>

    @GET("v2/top-headlines?country=us&category=business&apiKey=838ff6f536854b3ea2c8121ebe7d2390")
    fun getDataNewsBusiness(): Call<ResponseServer>

    @GET("v2/top-headlines?sources=techcrunch&apiKey=838ff6f536854b3ea2c8121ebe7d2390")
    fun getDataNewsHeadlines(): Call<ResponseServer>
}