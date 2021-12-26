package cn.hsp.blog.network.response


data class Blog(
    var id: Long = 0,
    var title: String = "",
    var content: String = ""
)

class LoginResp(
    val id: Long = 0,
    val token: String = ""
)

class RegisterResp(
    val id: Long = 0
)