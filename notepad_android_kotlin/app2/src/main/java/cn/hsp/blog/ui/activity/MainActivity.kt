package cn.hsp.blog.ui.activity

import androidx.fragment.app.Fragment
import cn.hsp.blog.R
import cn.hsp.blog.base.BaseActivity
import cn.hsp.blog.ui.fragment.BlogListFragment
import cn.hsp.blog.ui.fragment.MineFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class MainActivity : BaseActivity() {
    override fun layoutResId(): Int = R.layout.activity_main

    private var lastIndex = 0
    private var mFragments = mutableListOf<Fragment>()
    override fun initView() {
        initBottomNavigation()
    }

    override fun initData() {
        mFragments = ArrayList()
        mFragments.add(BlogListFragment())
        mFragments.add(MineFragment())
        // 初始化展示MessageFragment
        setFragmentPosition(0)
    }

    private fun initBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_message -> setFragmentPosition(0)
                R.id.menu_contacts -> setFragmentPosition(1)
                else -> {
                }
            }
            true
        })
    }

    private fun setFragmentPosition(position: Int) {
        val ft = supportFragmentManager.beginTransaction()
        val currentFragment = mFragments[position]
        val lastFragment = mFragments[lastIndex]
        lastIndex = position
        ft.hide(lastFragment)
        if (!currentFragment.isAdded) {
            supportFragmentManager.beginTransaction().remove(currentFragment).commit()
            ft.add(R.id.ll_frameLayout, currentFragment)
        }
        ft.show(currentFragment)
        ft.commitAllowingStateLoss()
    }
}