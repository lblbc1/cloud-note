package cn.hsp.blog.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
abstract class BaseVmFragment<VM : BaseViewModel> : Fragment() {

    protected open lateinit var mViewModel: VM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initViewModel()
        observe()
        if (savedInstanceState == null) {
            initData()
        }
        initListeners()
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }

    protected abstract fun viewModelClass(): Class<VM>
    protected abstract fun layoutResId(): Int

    open fun initView() {
        // Override if need
    }

    open fun observe() {
        // Override if need
    }

    open fun initData() {
        // Override if need
    }

    open fun initListeners() {
        // Override if need
    }
}
