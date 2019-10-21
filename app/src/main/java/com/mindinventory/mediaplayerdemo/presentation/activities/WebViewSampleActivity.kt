package com.mindinventory.mediaplayerdemo.presentation.activities

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.mindinventory.mediaplayerdemo.R
import kotlinx.android.synthetic.main.activity_web_view_sample.*

class WebViewSampleActivity : AppCompatActivity() {
    private var TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_sample)

        webViewSample.loadUrl("https://www.mindinventory.com/")

        webViewSample.setWebViewClient(MyBrowser())
        val webSettings = webViewSample.getSettings()
        webSettings.setJavaScriptEnabled(true)

    }

    class MyBrowser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

            if (url == "https://www.mindinventory.com/fitness-solutions.php") {
                activityBack(WebViewActivity())
            }
            return true
        }

        companion object {
            fun activityBack(context: Activity) {
                context.onBackPressed()
            }
        }
    }
}
