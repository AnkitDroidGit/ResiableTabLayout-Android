package com.cogitator.resizabletabs

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 04/05/2018 (MM/DD/YYYY)
 */
class ResizableTabItemContainer : LinearLayout {

    private var tabItem: ResizableTabItemView? = null

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        gravity = Gravity.CENTER_VERTICAL
        tabItem = ResizableTabItemView(context)
        addView(tabItem)
    }

    fun setItemConfig(config: ResizableTabItemData) {
        updateLayoutParams(config)
        setItemSize(config)
        tabItem?.setFromColor(config.inactiveColor)
        tabItem?.setToColor(config.activeColor)
        tabItem?.setDrawable(config.drawable)
    }

    fun expand() {
        tabItem?.expand()
    }

    fun collapse() {
        tabItem?.collapse()
    }

    private fun setItemSize(config: ResizableTabItemData) {
        val params = layoutParams
        val size = config.size
        params.height = (size + size * 0.2).toInt()
        layoutParams = params
        tabItem?.setItemSize(size)
        requestLayout()
    }

    private fun updateLayoutParams(config: ResizableTabItemData){
        val params: LinearLayout.LayoutParams = layoutParams as LayoutParams
        params.setMargins(config.space,0,config.space, 0)
        layoutParams = params
    }
}