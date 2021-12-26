package cn.hsp.demo.utils

import android.content.Context
import cn.hsp.demo.App
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
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