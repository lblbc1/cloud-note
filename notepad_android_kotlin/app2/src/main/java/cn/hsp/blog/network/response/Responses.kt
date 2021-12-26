package cn.hsp.blog.network.response

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
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