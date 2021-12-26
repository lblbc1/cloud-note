/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
package cn.hsp.demo.network

import cn.hsp.demo.network.request.AddNoteRequest
import cn.hsp.demo.network.request.LoginRequest
import cn.hsp.demo.network.request.ModifyNoteRequest
import cn.hsp.demo.network.request.RegisterRequest
import cn.hsp.demo.network.response.Note
import cn.hsp.demo.network.response.LoginResp
import cn.hsp.demo.network.response.RegisterResp
import cn.hsp.demo.network.response.Result
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
    suspend fun getDataList(@Path("userId") userId: Long): Result<List<Note>?>?

    @GET("blog/api/query/{blogId}")
    suspend fun queryData(@Path("blogId") blogId: Long): Result<Note?>?

    @GET("blog/api/del/{blogId}")
    suspend fun deleteData(@Path("blogId") blogId: Long): Result<String?>?

    @POST("blog/api/add")
    suspend fun addData(@Body request: AddNoteRequest): Result<String?>?

    @POST("blog/api/modify")
    suspend fun modifyData(@Body request: ModifyNoteRequest): Result<String?>?


//    @GET("/article/list/{page}/json")
//    suspend fun getArticleList(@Path("page") page: Int): ApiResult<Pagination<Article>>


}