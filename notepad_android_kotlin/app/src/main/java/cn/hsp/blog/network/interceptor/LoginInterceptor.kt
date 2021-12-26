/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
package cn.hsp.blog.network.interceptor

import cn.hsp.blog.utils.Constants.SP_KEY_TOKEN
import cn.hsp.blog.utils.SpUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class LoginInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        //请求头添加token
        val newRequest = checkIgnoreToken(request)
        if (null != newRequest) {
            request = newRequest
        }
        return chain.proceed(request)
    }

    private fun checkIgnoreToken(_request: Request): Request? {
        var request = _request
        return try {
            val ignoreToken: Boolean
            val ignoreTokenStr = request.header("ignoreToken") ?: ""
            ignoreToken = if (ignoreTokenStr == "true") {
                java.lang.Boolean.parseBoolean(ignoreTokenStr)
            } else {
                false
            }
            if (!ignoreToken) {
                request = request.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                return request
            }
            null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private val token: String?
        get() = SpUtil.get(SP_KEY_TOKEN, "")
}