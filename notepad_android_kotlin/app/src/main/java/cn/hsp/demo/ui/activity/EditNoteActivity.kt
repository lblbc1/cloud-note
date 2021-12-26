package cn.hsp.demo.ui.activity

import cn.hsp.demo.R
import cn.hsp.demo.base.BaseVmActivity
import cn.hsp.demo.utils.Constants.EXTRA_KEY_NOTE_CONTENT
import cn.hsp.demo.utils.Constants.EXTRA_KEY_NOTE_ID
import cn.hsp.demo.utils.Constants.EXTRA_KEY_NOTE_TITLE
import cn.hsp.demo.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_edit_note.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class EditNoteActivity : BaseVmActivity<NoteViewModel>() {
    private var blogId = 0L
    override fun viewModelClass() = NoteViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_edit_note

    override fun initView() {
        blogId = intent.getLongExtra(EXTRA_KEY_NOTE_ID, 0L)
        val blogTitle = intent.getStringExtra(EXTRA_KEY_NOTE_TITLE)
        val blogContent = intent.getStringExtra(EXTRA_KEY_NOTE_CONTENT)
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
