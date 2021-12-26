package cn.hsp.blog.utils

import android.content.Context
import cn.hsp.blog.App

object SpUtil {
    private fun getSpFile() =
        App.context.getSharedPreferences("default_sp_file", Context.MODE_PRIVATE)

    fun put(key: String, value: String) = getSpFile().edit().putString(key, value).apply()
    fun get(key: String, defaultValue: String) = getSpFile().getString(key, defaultValue)

    fun put(key: String, value: Long) = getSpFile().edit().putLong(key, value).apply()
    fun get(key: String, defaultValue: Long) = getSpFile().getLong(key, defaultValue)

}