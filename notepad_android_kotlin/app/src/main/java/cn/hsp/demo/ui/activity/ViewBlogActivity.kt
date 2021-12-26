package cn.hsp.demo.ui.activity

import android.content.Intent
import androidx.lifecycle.Observer
import cn.hsp.demo.R
import cn.hsp.demo.base.BaseVmActivity
import cn.hsp.demo.network.response.Blog
import cn.hsp.demo.utils.Constants.EXTRA_KEY_BLOG_CONTENT
import cn.hsp.demo.utils.Constants.EXTRA_KEY_BLOG_ID
import cn.hsp.demo.utils.Constants.EXTRA_KEY_BLOG_TITLE
import cn.hsp.demo.viewmodel.BlogViewModel
import kotlinx.android.synthetic.main.activity_view_blog.*
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class ViewBlogActivity : BaseVmActivity<BlogViewModel>() {
    private var blogId = 0L
    private var blog: Blog? = null
    override fun viewModelClass() = BlogViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_view_blog

    override fun initView() {
        blogId = intent.getLongExtra(EXTRA_KEY_BLOG_ID, 0L)
        initToolbar()
    }

    private fun initToolbar() {
        toolbar.inflateMenu(R.menu.view_blog)
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_modify -> gotoModifyBlogPage()
                R.id.action_del -> delBlog()
            }
            false
        }
    }

    private fun delBlog() {
        mViewModel.delBlog(blogId)
        finish()
    }

    private fun gotoModifyBlogPage() {
        val intent = Intent(this, EditBlogActivity::class.java)
        intent.putExtra(EXTRA_KEY_BLOG_ID, blog?.id)
        intent.putExtra(EXTRA_KEY_BLOG_TITLE, blog?.title)
        intent.putExtra(EXTRA_KEY_BLOG_CONTENT, blog?.content)
        startActivity(intent)
    }

    override fun initData() {
        mViewModel.getBlog(blogId)
    }

    override fun observe() {
        mViewModel.blog.observe(this, Observer {
            blog = it
            titleEt.text = it.title
            contentEt.text = it.content
        }
        )
    }

    override fun onResume() {
        super.onResume()
        initData()
    }
}
