package cn.hsp.blog.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.hsp.blog.R
import cn.hsp.blog.network.response.Blog
import kotlinx.android.synthetic.main.item_blog.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class BlogListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var onItemClick: (Blog: Blog) -> Unit
    private var dataList = mutableListOf<Blog>()
    fun setData(blogList: List<Blog>) {
        dataList.clear()
        dataList.addAll(blogList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList[position]
        holder.itemView.textView.text = data.title
        holder.itemView.setOnClickListener { onItemClick(data) }
    }

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return ViewHolder(view)
    }

    infix fun setOnItemClick(onClick: (Blog: Blog) -> Unit) {
        this.onItemClick = onClick
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}