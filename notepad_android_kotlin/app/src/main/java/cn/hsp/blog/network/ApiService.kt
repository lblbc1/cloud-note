/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
package cn.hsp.blog.network

import cn.hsp.blog.network.request.AddBlogRequest
import cn.hsp.blog.network.request.LoginRequest
import cn.hsp.blog.network.request.ModifyBlogRequest
import cn.hsp.blog.network.request.RegisterRequest
import cn.hsp.blog.network.response.Blog
import cn.hsp.blog.network.response.LoginResp
import cn.hsp.blog.network.response.RegisterResp
import cn.hsp.blog.network.response.Result
import retrofit2.http.*

interface ApiService {

    @POST("api/login")
    @Headers("ignoreToken:true")
    suspend fun login(@Body request: LoginRequest): Result<LoginResp?>?

    @POST("api/register")
    @Headers("ignoreToken:true")
    suspend fun register(@Body request: RegisterRequest): Result<RegisterResp?>?

    @GET("blog/api/list/{userId}")
    @Headers("ignoreToken:true")
    suspend fun getBlogList(@Path("userId") userId: Long): Result<List<Blog>?>?

    @GET("blog/api/query/{blogId}")
    suspend fun getBlog(@Path("blogId") blogId: Long): Result<Blog?>?

    @GET("blog/api/del/{blogId}")
    suspend fun delBlog(@Path("blogId") blogId: Long): Result<String?>?

    @POST("blog/api/add")
    suspend fun addBlog(@Body request: AddBlogRequest): Result<String?>?

    @POST("blog/api/modify")
    suspend fun modifyBlog(@Body request: ModifyBlogRequest): Result<String?>?


//    @GET("/article/list/{page}/json")
//    suspend fun getArticleList(@Path("page") page: Int): ApiResult<Pagination<Article>>


}