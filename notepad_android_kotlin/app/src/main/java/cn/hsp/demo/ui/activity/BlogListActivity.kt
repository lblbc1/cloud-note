package cn.hsp.demo.ui.activity

import android.content.Intent
import androidx.lifecycle.Observer
import cn.hsp.demo.R
import cn.hsp.demo.base.BaseVmActivity
import cn.hsp.demo.network.response.Blog
import cn.hsp.demo.ui.adapter.BlogListAdapter
import cn.hsp.demo.utils.Constants.EXTRA_KEY_BLOG_ID
import cn.hsp.demo.utils.Constants.EXTRA_KEY_BLOG_TITLE
import cn.hsp.demo.viewmodel.BlogListViewModel
import kotlinx.android.synthetic.main.activity_blog_list.*
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class BlogListActivity : BaseVmActivity<BlogListViewModel>() {
    private val adapter = BlogListAdapter()
    override fun viewModelClass() = BlogListViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_blog_list

    override fun initView() {
        rv.adapter = adapter
        adapter.setOnItemClick(this::onItemClick)

        swipeRefreshLayout.setOnRefreshListener {
            initData()
        }
    }

    override fun initData() {
        mViewModel.getBlogList(
            onSuccess = {
                swipeRefreshLayout.isRefreshing = true
            },
            onComplete = {
                swipeRefreshLayout.isRefreshing = false
            })
    }

    override fun initListeners() {
        addIv.setOnClickListener {
            startActivity(Intent(this, AddBlogActivity::class.java))
        }
    }
    override fun observe() {
        mViewModel.dataList.observe(this, Observer { adapter.setData(it) })
    }

    private fun onItemClick(blog: Blog) {
        val intent = Intent(this, ViewBlogActivity::class.java)
        intent.putExtra(EXTRA_KEY_BLOG_ID, blog.id)
        intent.putExtra(EXTRA_KEY_BLOG_TITLE, blog.title)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        initData()
    }
}
