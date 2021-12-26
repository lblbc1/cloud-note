package cn.hsp.blog.network.response

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
data class Result<T>(val code: Int, val msg: String, val data: T?) {

    fun isSuccess(): Boolean {
        return code == 0
    }
}
