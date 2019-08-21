package com.example.mykotlin.view.widget

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.mykotlin.R
import com.example.mykotlin.view.interfaces.IInterFace

/**
 * Created by zhangjie on 2019/7/31 15:35
 * Description:
 * Passed parameters:
 * Warning:
 */
class CommonDialog(context: Context): Dialog(context) {
    class Builder(private val context: Context){
        private var title: String? = "提示" //标题
        private var content: String? = "系统出现异常" //内容
        private var listener: IInterFace.DialogClick? = null //回调
        //lateinit var onClick: (left: TextView, right: TextView, dialog: CommonDialog) -> Builder//点击回调方法

        //设置标题
        fun setTitle(title: String = "提示"): Builder {
            this.title = title
            return this
        }

        fun setTitle(title: Int = R.string.title): Builder {
            this.title = context.getString(title)
            return this
        }

        //设置提示内容
        fun setContent(content: String = "系统出现异常"): Builder {
            this.content = content
            return this
        }

        fun setContent(content: Int = R.string.content): Builder {
            this.content = context.getString(content)
            return this
        }

        fun setOnClickListener(listener: IInterFace.DialogClick): Builder {
            this.listener = listener
            return this
        }

        fun create(): CommonDialog{
            //dialog
            val dialog = CommonDialog(context)
            //解析view
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_view, null)
            //设置dialog view
            dialog.addContentView(dialogView,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
            //设置标题
            dialogView.findViewById<TextView>(R.id.tv_title).text = title
            //设置内容
            dialogView.findViewById<TextView>(R.id.tv_content).text = content
            //点击事件，直接将按钮回调回去，使用者自己处理
            //onClick.invoke(dialogView.findViewById(R.id.tv_left), dialogView.findViewById(R.id.tv_right), dialog)
            listener?.onClick(dialogView.findViewById(R.id.tv_left), dialogView.findViewById(R.id.tv_right), dialog)
            //点击外部区域弹窗不消失
            dialog.setCanceledOnTouchOutside(false)

            //设置窗口宽高
            val window = dialog.window
            val attributes = window?.attributes
            val windowManager = (context as Activity).windowManager
            val defaultDisplay = windowManager.defaultDisplay
            attributes?.width = (defaultDisplay.width * 0.77).toInt()
            window?.attributes = attributes
            return dialog
        }
    }
}