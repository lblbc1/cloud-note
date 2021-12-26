package cn.hsp.blog.network

import cn.hsp.blog.network.interceptor.HttpLogInterceptor
import cn.hsp.blog.network.interceptor.LoginInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpClientUtil {
    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .apply {
                    addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                    addInterceptor(HttpLogInterceptor())
                    addInterceptor(LoginInterceptor())
                }.build()
    }
}
