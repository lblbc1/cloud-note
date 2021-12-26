package cn.hsp.blog.ui.activity

import android.content.Intent
import cn.hsp.blog.R
import cn.hsp.blog.base.BaseActivity
import cn.hsp.blog.utils.Constants
import cn.hsp.blog.utils.SpUtil
import kotlinx.coroutines.*

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
