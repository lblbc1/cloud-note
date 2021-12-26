package cn.hsp.blog.ui.activity

import android.content.Intent
import cn.hsp.blog.R
import cn.hsp.blog.base.BaseVmActivity
import cn.hsp.blog.viewmodel.LoginViewModel
import com.cxyzy.utils.ext.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.passwordEt
import kotlinx.android.synthetic.main.activity_login.registerBtn
import kotlinx.android.synthetic.main.activity_login.userNameEt
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseVmActivity<LoginViewModel>() {
    override fun viewModelClass() = LoginViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_register

    override fun initListeners() {
        registerBtn.setOnClickListener {
            mViewModel.register(
                    userNameEt.text.toString(),
                    passwordEt.text.toString(),
                    onSuccess = {
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                    },
                    onFailure = {
                        toast(it)
                    })
        }
    }
}
