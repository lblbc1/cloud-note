package cn.hsp.blog.network.request

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
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