package cn.hsp.blog.ui.activity

import cn.hsp.blog.R
import cn.hsp.blog.base.BaseVmActivity
import cn.hsp.blog.event.RefreshEvent
import cn.hsp.blog.utils.Constants.EXTRA_KEY_BLOG_CONTENT
import cn.hsp.blog.utils.Constants.EXTRA_KEY_BLOG_ID
import cn.hsp.blog.utils.Constants.EXTRA_KEY_BLOG_TITLE
import cn.hsp.blog.viewmodel.BlogViewModel
import kotlinx.android.synthetic.main.activity_edit_blog.*
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class EditBlogActivity : BaseVmActivity<BlogViewModel>() {
    private var blogId = 0L
    override fun viewModelClass() = BlogViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_edit_blog

    override fun initView() {
        blogId = intent.getLongExtra(EXTRA_KEY_BLOG_ID, 0L)
        val blogTitle = intent.getStringExtra(EXTRA_KEY_BLOG_TITLE)
        val blogContent = intent.getStringExtra(EXTRA_KEY_BLOG_CONTENT)
        titleEt.setText(blogTitle)
        contentEt.setText(blogContent)
        initToolbar()
    }

    private fun initToolbar() {
        toolbar.inflateMenu(R.menu.edit_blog)
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_save -> saveBlog()
            }
            false
        }
    }

    private fun saveBlog() {
        mViewModel.modifyBlog(blogId, titleEt.text.toString(), contentEt.text.toString())
        finish()
    }
}
