package com.mindinventory.mediaplayerdemo.presentation.activities

import android.os.Bundle
import android.util.Log
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.mindinventory.mediaplayerdemo.R
import com.mindinventory.mediaplayerdemo.Utils.WebAppInterface
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView.loadUrl("file:///android_asset/index.html")

        webView.addJavascriptInterface(WebAppInterface(this), "AndroidInterface") // To call methods in Android from using js in the html, AndroidInterface.showToast, AndroidInterface.getAndroidVersion etc
        val webSettings = webView.getSettings()
        webSettings.setJavaScriptEnabled(true)
        webView.setWebViewClient(MyWebViewClient())
        webView.setWebChromeClient(MyWebChromeClient())
    }

    private inner class MyWebChromeClient : WebChromeClient() {
        override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
            Log.d("LogTag", message + "Printed Log")
            result.confirm()
            return true
        }
    }

    class MyWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            //Calling a javascript function in html page
            view.loadUrl("javascript:alert(showVersion('called by Android'))")
            Log.i("TAG", "CommonUrl" + url)
        }
    }
}

