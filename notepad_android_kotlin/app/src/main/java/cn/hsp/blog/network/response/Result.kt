package cn.hsp.blog.network.response

data class Result<T>(val code: Int, val msg: String, val data: T?) {

    fun isSuccess(): Boolean {
        return code == 0
    }
}
