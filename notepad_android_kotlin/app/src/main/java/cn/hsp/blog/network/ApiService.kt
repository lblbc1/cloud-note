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