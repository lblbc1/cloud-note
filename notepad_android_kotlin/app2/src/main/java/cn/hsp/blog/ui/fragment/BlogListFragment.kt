package cn.hsp.blog.ui.fragment

import android.content.Intent
import androidx.lifecycle.Observer
import cn.hsp.blog.R
import cn.hsp.blog.base.BaseVmFragment
import cn.hsp.blog.event.RefreshEvent
import cn.hsp.blog.network.response.Blog
import cn.hsp.blog.ui.activity.AddBlogActivity
import cn.hsp.blog.ui.activity.ViewBlogActivity
import cn.hsp.blog.ui.adapter.BlogListAdapter
import cn.hsp.blog.utils.Constants
import cn.hsp.blog.viewmodel.BlogListViewModel
import kotlinx.android.synthetic.main.activity_blog_list.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class BlogListFragment : BaseVmFragment<BlogListViewModel>() {
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
            startActivity(Intent(activity, AddBlogActivity::class.java))
        }
    }
    override fun observe() {
        mViewModel.dataList.observe(this, Observer { adapter.setData(it) })
    }

    private fun onItemClick(blog: Blog) {
        val intent = Intent(activity, ViewBlogActivity::class.java)
        intent.putExtra(Constants.EXTRA_KEY_BLOG_ID, blog.id)
        intent.putExtra(Constants.EXTRA_KEY_BLOG_TITLE, blog.title)
        startActivity(intent)
    }

    override fun onResume(){
        super.onResume()
        initData()
    }
}