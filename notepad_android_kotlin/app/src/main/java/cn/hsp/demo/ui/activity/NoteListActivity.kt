package cn.hsp.demo.ui.activity

import android.content.Intent
import androidx.lifecycle.Observer
import cn.hsp.demo.R
import cn.hsp.demo.base.BaseVmActivity
import cn.hsp.demo.network.response.Note
import cn.hsp.demo.ui.adapter.NoteListAdapter
import cn.hsp.demo.utils.Constants.EXTRA_KEY_NOTE_ID
import cn.hsp.demo.utils.Constants.EXTRA_KEY_NOTE_TITLE
import cn.hsp.demo.viewmodel.NoteListViewModel
import kotlinx.android.synthetic.main.activity_note_list.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class NoteListActivity : BaseVmActivity<NoteListViewModel>() {
    private val adapter = NoteListAdapter()
    override fun viewModelClass() = NoteListViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_note_list

    override fun initView() {
        rv.adapter = adapter
        adapter.setOnItemClick(this::onItemClick)

        swipeRefreshLayout.setOnRefreshListener {
            initData()
        }
    }

    override fun initData() {
        mViewModel.queryDataList(
            onSuccess = {
                swipeRefreshLayout.isRefreshing = true
            },
            onComplete = {
                swipeRefreshLayout.isRefreshing = false
            })
    }

    override fun initListeners() {
        addIv.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
    }
    override fun observe() {
        mViewModel.dataList.observe(this, Observer { adapter.setData(it) })
    }

    private fun onItemClick(note: Note) {
        val intent = Intent(this, ViewNoteActivity::class.java)
        intent.putExtra(EXTRA_KEY_NOTE_ID, note.id)
        intent.putExtra(EXTRA_KEY_NOTE_TITLE, note.title)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        initData()
    }
}
