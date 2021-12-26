package cn.hsp.blog.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.hsp.blog.base.BaseViewModel
import cn.hsp.blog.network.BlogRepo
import cn.hsp.blog.network.response.Blog
import cn.hsp.blog.utils.Constants.SP_KEY_USER_ID
import cn.hsp.blog.utils.SpUtil

class BlogListViewModel : BaseViewModel() {
    private val blogListRepo by lazy { BlogRepo() }
    val dataList: MutableLiveData<List<Blog>> = MutableLiveData()

    fun getBlogList(
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                val userId = SpUtil.get(SP_KEY_USER_ID, 0L)
                dataList.value = blogListRepo.getBlogList(userId)?.data
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }
}