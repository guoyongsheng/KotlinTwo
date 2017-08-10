package demo.wesley.com.kotlintwo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 *
 * @author wesley
 * @date: 2017-7-14
 * @Description: 适配器
 */
class MainAdapter constructor(context: Context, val list: MutableList<String>) :
        RecyclerView.Adapter<MainAdapter.ItemViewHolder>() {

    private var listener: OnItemClickListener? = null

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        val view: View? = inflater.inflate(R.layout.item_main, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        holder?.tv_value?.text = list[position]
        holder?.itemView?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                listener?.onItemClick(position)
            }
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val tv_value: TextView? = itemView?.findViewById(R.id.item_value) as TextView
    }

    fun addAllItems(dataSource: MutableList<String>) {
        list.addAll(list.size, dataSource)
        notifyDataSetChanged()
    }


    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }


    interface OnItemClickListener {

        fun onItemClick(position: Int)
    }
}