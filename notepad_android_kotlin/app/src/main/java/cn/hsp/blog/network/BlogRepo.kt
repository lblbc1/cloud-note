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