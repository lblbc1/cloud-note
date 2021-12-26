package cn.hsp.blog.base

import cn.hsp.blog.network.ApiService
import cn.hsp.blog.network.OkHttpClientUtil
import cn.hsp.blog.utils.Constants.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseRepository {
    val apiService: ApiService by lazy {
        val okHttpClient = OkHttpClientUtil.getClient()
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(ApiService::class.java)
    }
}