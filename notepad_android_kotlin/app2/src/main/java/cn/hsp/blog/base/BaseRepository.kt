package cn.hsp.blog.base

import cn.hsp.blog.network.ApiService
import cn.hsp.blog.network.OkHttpClientUtil
import cn.hsp.blog.utils.Constants.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
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