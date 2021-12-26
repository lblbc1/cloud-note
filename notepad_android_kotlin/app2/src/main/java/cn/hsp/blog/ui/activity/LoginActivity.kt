package cn.hsp.blog.ui.activity

import android.content.Intent
import cn.hsp.blog.R
import cn.hsp.blog.base.BaseVmActivity
import cn.hsp.blog.viewmodel.LoginViewModel
import com.cxyzy.utils.ext.toast
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class LoginActivity : BaseVmActivity<LoginViewModel>() {
    override fun viewModelClass() = LoginViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_login

    override fun initListeners() {
        loginBtn.setOnClickListener {
            mViewModel.login(
                userNameEt.text.toString(),
                passwordEt.text.toString(),
                onSuccess = {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                },
                onFailure = {
                    toast(it)
                })
        }
        registerBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }
}
