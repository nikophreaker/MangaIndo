package com.nikx.mangaindo.tools

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.annotation.RequiresApi


class MyWebView : WebView {
    constructor(context: Context?) : super(context!!) {
        initDefaultSetting()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        initDefaultSetting()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        initDefaultSetting()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int,
                defStyleRes: Int) : super(context!!, attrs, defStyleAttr, defStyleRes) {
        initDefaultSetting()
    }

    private fun initDefaultSetting() {
        val webSettings = this.settings
        webSettings.javaScriptEnabled = true
        webChromeClient = WebChromeClient()
        webViewClient = MyWebViewClient()
    }

    /**
     * Load Web View with url
     */
    fun load(url: String?) {
        this.loadUrl(url!!)
    }
}