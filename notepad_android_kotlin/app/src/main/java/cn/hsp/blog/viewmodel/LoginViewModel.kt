package cn.hsp.blog.viewmodel

import cn.hsp.blog.base.BaseViewModel
import cn.hsp.blog.network.BlogRepo
import cn.hsp.blog.utils.Constants.SP_KEY_TOKEN
import cn.hsp.blog.utils.Constants.SP_KEY_USER_ID
import cn.hsp.blog.utils.SpUtil

class LoginViewModel : BaseViewModel() {
    private val blogRepo by lazy { BlogRepo() }

    fun login(
        userName: String, password: String,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                val resp = blogRepo.login(userName, password)
                if (resp?.isSuccess() == true) {
                    SpUtil.put(SP_KEY_TOKEN, resp?.data?.token ?: "")
                    SpUtil.put(SP_KEY_USER_ID, resp?.data?.id ?: 0L)
                    onSuccess?.invoke()
                } else {
                    onFailure?.invoke(resp?.msg ?: "")
                }
            },
            { onFailure?.invoke(it.message ?: "error") },
            { onComplete?.invoke() })
    }

    fun register(
        userName: String, password: String,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                val resp = blogRepo.register(userName, password)
                if (resp?.isSuccess() == true) {
                    onSuccess?.invoke()
                } else {
                    onFailure?.invoke(resp?.msg ?: "")
                }
            },
            { onFailure?.invoke(it.message ?: "error") },
            { onComplete?.invoke() })
    }
}