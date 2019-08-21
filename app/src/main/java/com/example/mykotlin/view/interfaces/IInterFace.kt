package com.example.mykotlin.view.interfaces

import android.widget.TextView
import com.example.mykotlin.view.widget.CommonDialog

/**
 * Created by zhangjie on 2019/7/31 20:39
 * Description:
 * Passed parameters:
 * Warning:
 */
class IInterFace {
    interface DialogClick{//dialog点击事件
        fun onClick(left: TextView, right: TextView, dialog: CommonDialog)
    }
}