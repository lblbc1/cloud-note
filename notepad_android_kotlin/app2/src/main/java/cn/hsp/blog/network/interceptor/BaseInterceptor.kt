package cn.hsp.blog.network.interceptor

import cn.hsp.blog.utils.Constants.LOGIN_URL
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * okhttp基础拦截器，对指定接口不做拦截
 * @author 花生皮编程(CSDN、简书、掘金、今日头条、微信公众号、抖音、快手、B站、西瓜视频)
 * 编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
 */
abstract class BaseInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return if (shouldIntercept(request)) {
            interceptMe(chain)
        } else {
            chain.proceed(request)
        }
    }

    private fun shouldIntercept(request: Request) = !request.url().url().path.endsWith(LOGIN_URL)
    abstract fun interceptMe(chain: Interceptor.Chain): Response
}
