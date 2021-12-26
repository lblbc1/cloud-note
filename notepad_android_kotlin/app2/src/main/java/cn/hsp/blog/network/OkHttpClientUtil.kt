package cn.hsp.blog.network

import cn.hsp.blog.network.interceptor.HttpLogInterceptor
import cn.hsp.blog.network.interceptor.LoginInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
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
