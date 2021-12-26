package cn.hsp.demo.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.hsp.demo.base.BaseViewModel
import cn.hsp.demo.network.BlogRepo
import cn.hsp.demo.network.response.Blog
import cn.hsp.demo.utils.Constants.SP_KEY_USER_ID
import cn.hsp.demo.utils.SpUtil
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
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