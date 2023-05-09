package com.example.mymovies.data.network


import com.example.mymovies.util.API_KEY
import com.example.mymovies.util.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/6/2023.
 */

object RETROFIT {
    val API: MoviesApi by lazy {
        val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url
            val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", API_KEY).build()
            request.url(url)
            chain.proceed(request.build())
        }

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()
            .create(MoviesApi::class.java)
    }
}
