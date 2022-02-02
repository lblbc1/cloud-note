package cn.lblbc.note.utils

import android.widget.Toast
import cn.lblbc.note.App
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
fun toast(message: String) {
    Toast.makeText(App.context, message, Toast.LENGTH_SHORT).show()
}