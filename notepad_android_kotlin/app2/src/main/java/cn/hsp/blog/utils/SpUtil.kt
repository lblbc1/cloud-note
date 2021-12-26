package cn.hsp.blog.utils

import android.content.Context
import cn.hsp.blog.App

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
object SpUtil {
    private fun getSpFile() =
        App.context.getSharedPreferences("default_sp_file", Context.MODE_PRIVATE)

    fun put(key: String, value: String) = getSpFile().edit().putString(key, value).apply()
    fun get(key: String, defaultValue: String) = getSpFile().getString(key, defaultValue)

    fun put(key: String, value: Long) = getSpFile().edit().putLong(key, value).apply()
    fun get(key: String, defaultValue: Long) = getSpFile().getLong(key, defaultValue)

}