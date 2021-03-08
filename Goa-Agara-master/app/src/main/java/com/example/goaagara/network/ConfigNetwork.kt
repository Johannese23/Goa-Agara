package com.example.goaagara.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigNetwork{
    companion object{
        fun getRetrofit():NewsServices{
            val retrofit = Retrofit.Builder()
                .baseUrl("http://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val services = retrofit.create(NewsServices::class.java)

            return services
        }
    }
}