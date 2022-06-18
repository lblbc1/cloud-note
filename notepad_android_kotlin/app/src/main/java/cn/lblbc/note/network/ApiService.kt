/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
package cn.lblbc.note.network

import cn.lblbc.note.network.request.AddNoteRequest
import cn.lblbc.note.network.request.LoginRequest
import cn.lblbc.note.network.request.ModifyNoteRequest
import cn.lblbc.note.network.request.RegisterRequest
import cn.lblbc.note.network.response.Note
import cn.lblbc.note.network.response.LoginResp
import cn.lblbc.note.network.response.RegisterResp
import cn.lblbc.note.network.response.Result
import retrofit2.http.*

interface ApiService {

    @POST("user/login")
    @Headers("ignoreToken:true")
    suspend fun login(@Body request: LoginRequest): Result<LoginResp?>?

    @POST("user/register")
    @Headers("ignoreToken:true")
    suspend fun register(@Body request: RegisterRequest): Result<RegisterResp?>?

    @GET("note/list")
    suspend fun getDataList(): Result<List<Note>?>?

    @GET("note/query/{noteId}")
    suspend fun queryData(@Path("noteId") noteId: Long): Result<Note?>?

    @GET("note/del/{noteId}")
    suspend fun deleteData(@Path("noteId") noteId: Long): Result<String?>?

    @POST("note/add")
    suspend fun addData(@Body request: AddNoteRequest): Result<String?>?

    @POST("note/modify")
    suspend fun modifyData(@Body request: ModifyNoteRequest): Result<String?>?
}