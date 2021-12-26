package cn.hsp.blog.ui.activity

import cn.hsp.blog.R
import cn.hsp.blog.base.BaseVmActivity
import cn.hsp.blog.event.RefreshEvent
import cn.hsp.blog.viewmodel.BlogViewModel
import kotlinx.android.synthetic.main.activity_edit_blog.*

class AddBlogActivity : BaseVmActivity<BlogViewModel>() {
    override fun viewModelClass() = BlogViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_add_blog

    override fun initView() {
        initToolbar()
    }

    private fun initToolbar() {
        toolbar.inflateMenu(R.menu.add_blog)
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_save -> saveBlog()
            }
            false
        }
    }

    private fun saveBlog() {
        mViewModel.addBlog(titleEt.text.toString(), contentEt.text.toString())
        finish()
    }
}
