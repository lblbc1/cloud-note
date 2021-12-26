package cn.hsp.blog.ui.activity

import android.content.Intent
import cn.hsp.blog.R
import cn.hsp.blog.base.BaseVmActivity
import cn.hsp.blog.viewmodel.LoginViewModel
import com.cxyzy.utils.ext.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseVmActivity<LoginViewModel>() {
    override fun viewModelClass() = LoginViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_login

    override fun initListeners() {
        loginBtn.setOnClickListener {
            mViewModel.login(
                userNameEt.text.toString(),
                passwordEt.text.toString(),
                onSuccess = {
                    startActivity(Intent(this@LoginActivity, BlogListActivity::class.java))
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
