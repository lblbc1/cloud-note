/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
package cn.hsp.blog.network

import cn.hsp.blog.base.BaseRepository
import cn.hsp.blog.network.request.AddBlogRequest
import cn.hsp.blog.network.request.LoginRequest
import cn.hsp.blog.network.request.ModifyBlogRequest
import cn.hsp.blog.network.request.RegisterRequest

class BlogRepo : BaseRepository() {
    suspend fun getBlogList(userId: Long) = apiService.getBlogList(userId)
    suspend fun getBlog(blogId: Long) = apiService.getBlog(blogId)
    suspend fun addBlog(title: String, content: String) =
        apiService.addBlog(AddBlogRequest(title, content))

    suspend fun modifyBlog(id: Long, title: String, content: String) = apiService.modifyBlog(
        ModifyBlogRequest(id, title, content)
    )

    suspend fun delBlog(blogId: Long) = apiService.delBlog(blogId)
    suspend fun login(userName: String, password: String) =
        apiService.login(LoginRequest(userName, password))

    suspend fun register(userName: String, password: String) =
        apiService.register(RegisterRequest(userName, password))
}