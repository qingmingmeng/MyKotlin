package com.example.okhttputil.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Animatable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.okhttputil.R

/**
 * Created by zhangjie on 2019/8/2 15:38
 * Description:
 * Passed parameters:
 * Warning:
 */
class Loading(context: Context, style: Int): Dialog(context, style) {
    companion object{
        private var dialog: Dialog? = null
        fun show(context: Context, isShow: Boolean){
            if (isShow){
                if (null == dialog || !dialog!!.isShowing){
                    //dialog为null 或者 未展示
                    dialog = Loading(context, R.style.ProgressHUD)
                    //解析view
                    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val dialogView = inflater.inflate(R.layout.layout_loading, null)
                    //设置dialog view
                    dialog?.addContentView(dialogView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
                    //动画
                    val ivLoading = dialogView.findViewById<ImageView>(R.id.spinnerImageView)
                    val drawable = ivLoading.drawable
                    (drawable as Animatable).start()
                    //点击外部区域弹窗不消失
                    dialog?.setCanceledOnTouchOutside(false)
                    //点击返回键不消失
                    dialog?.setCancelable(false)
                    //展示
                    dialog?.show()
                }
            } else{
                dialog?.dismiss()
                dialog?.cancel()
                dialog = null
            }
        }
    }
}