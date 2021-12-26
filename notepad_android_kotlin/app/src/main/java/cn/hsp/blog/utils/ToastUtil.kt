package cn.hsp.blog.utils

import android.widget.Toast
import cn.hsp.blog.App

fun toast(message: String) {
    Toast.makeText(App.context, message, Toast.LENGTH_SHORT).show()
}