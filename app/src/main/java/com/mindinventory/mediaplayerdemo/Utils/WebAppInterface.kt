package com.mindinventory.mediaplayerdemo.Utils

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast

class WebAppInterface// Instantiate the interface and set the context
internal constructor(internal var mContext: Context) {

    // Show a toast from the web page
    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }

    //Show a Log from webPage
    @JavascriptInterface
    fun showLog(log: String) {
        Log.v("JsLog", log)
    }

    @JavascriptInterface
    fun showAndroidVersion(versionName: String) {
        Toast.makeText(mContext, versionName, Toast.LENGTH_SHORT).show()
    }

}