package cn.hsp.blog.ui.activity

import android.content.Intent
import cn.hsp.blog.R
import cn.hsp.blog.base.BaseActivity
import cn.hsp.blog.utils.Constants
import cn.hsp.blog.utils.SpUtil
import kotlinx.coroutines.*
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class SplashActivity : BaseActivity() {
    override fun layoutResId(): Int = R.layout.activity_splash

    override fun initView() {
        GlobalScope.launch(Dispatchers.IO) {
            delay(500)
            if (isLoggedIn()) {
                startActivity(Intent(this@SplashActivity, BlogListActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
            finish()
        }
    }

    private fun isLoggedIn() = !SpUtil.get(Constants.SP_KEY_TOKEN, "").isNullOrEmpty()
}
