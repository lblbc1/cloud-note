package cn.hsp.blog.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.hsp.blog.base.BaseViewModel
import cn.hsp.blog.network.BlogRepo
import cn.hsp.blog.network.response.Blog

class BlogViewModel : BaseViewModel() {
    private val blogListRepo by lazy { BlogRepo() }
    val blog: MutableLiveData<Blog> = MutableLiveData()

    fun getBlog(
        blogId: Long,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                blog.value = blogListRepo.getBlog(blogId)?.data
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }


    fun addBlog(
        title: String,
        content: String,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                blogListRepo.addBlog(title, content)
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

    fun modifyBlog(
        id: Long,
        title: String,
        content: String,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                blogListRepo.modifyBlog(id, title, content)
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

    fun delBlog(
        blogId: Long,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                blogListRepo.delBlog(blogId)
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }
}