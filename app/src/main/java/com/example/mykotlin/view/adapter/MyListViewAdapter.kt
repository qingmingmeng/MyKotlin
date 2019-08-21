package com.example.mykotlin.view.adapter

import android.content.Context
import android.support.annotation.IdRes
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.mykotlin.R

/**
 * Created by zhangjie on 2019/8/15 17:07
 * Description:
 * Passed parameters:
 * Warning:
 */
class MyListViewAdapter(private var datas: MutableList<String>?, private var context: Context): BaseAdapter() {
    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return datas?.size?:0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView?: View.inflate(context,R.layout.item_weather, null)
        val tvContent = view.bindView<TextView>(R.id.tv_content)
        tvContent.text = datas?.get(position)
        return view
    }

    fun <T : View> View.bindView(@IdRes id: Int): T{
        val viewHolder: SparseArray<View> = tag as? SparseArray<View>?:SparseArray()
        tag = viewHolder
        var childView: View? = viewHolder.get(id)
        if (null == childView){
            childView = findViewById(id)
            viewHolder.put(id,childView)
        }
        return childView as T
    }
}