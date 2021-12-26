package cn.hsp.demo.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.hsp.demo.base.BaseViewModel
import cn.hsp.demo.network.NoteRepo
import cn.hsp.demo.network.response.Note
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class NoteViewModel : BaseViewModel() {
    private val blogListRepo by lazy { NoteRepo() }
    val note: MutableLiveData<Note> = MutableLiveData()

    fun getBlog(
        blogId: Long,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                note.value = blogListRepo.queryData(blogId)?.data
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }


    fun addData(
        title: String,
        content: String,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                blogListRepo.addData(title, content)
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
                blogListRepo.modifyData(id, title, content)
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
                blogListRepo.deleteData(blogId)
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }
}