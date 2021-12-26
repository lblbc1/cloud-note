package cn.hsp.blog.network

class ApiException(var code: Int, override var message: String) : RuntimeException()