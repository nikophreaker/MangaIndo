package com.nikx.mangaindo.tools

import android.os.Build
import android.webkit.WebResourceRequest

import android.webkit.WebView

import android.webkit.WebViewClient
import androidx.annotation.RequiresApi


class MyWebViewClient : WebViewClient() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        view.loadUrl(request.url.toString())
        return true
    }
}