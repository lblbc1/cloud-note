package cn.hsp.blog.network.request

class LoginRequest(
    val name: String = "",
    val password: String = ""
)

class RegisterRequest(
    val name: String = "",
    val password: String = ""
)

class AddBlogRequest(
    val title: String = "",
    val content: String = ""
)

class ModifyBlogRequest(
    val id: Long = 0,
    val title: String = "",
    val content: String = ""
)