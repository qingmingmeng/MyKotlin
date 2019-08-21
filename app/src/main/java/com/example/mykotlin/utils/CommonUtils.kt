package com.example.mykotlin.utils

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.mykotlin.base.AppApplication
import com.example.mykotlin.view.interfaces.IInterFace
import com.example.mykotlin.view.widget.CommonDialog

/**
 * Created by zhangjie on 2019/7/31 19:29
 * Description:
 * Passed parameters:
 * Warning:
 */
class CommonUtils {
    companion object{
        //弹toast方法
        fun toast(text: String){
            Toast.makeText(AppApplication.context, text, Toast.LENGTH_SHORT).show()
        }
        //弹出普通dialog弹窗
        fun showNomalDialog(context: Context, msg: String){
            CommonDialog.Builder(context)
                .setContent(msg)
                .setOnClickListener(object : IInterFace.DialogClick{
                    override fun onClick(left: TextView, right: TextView, dialog: CommonDialog) {
                        left.visibility = View.GONE
                        right.text = "知道了"
                        right.setOnClickListener {
                            dialog.dismiss()
                        }
                    }
                })
                .create()
                .show()
        }
    }
}