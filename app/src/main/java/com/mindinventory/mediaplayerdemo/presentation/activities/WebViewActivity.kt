package com.mindinventory.mediaplayerdemo.presentation.activities

import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.mindinventory.mediaplayerdemo.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        with(webView) {
            loadUrl(getString(R.string.website_url))
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.settings.javaScriptCanOpenWindowsAutomatically = true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
