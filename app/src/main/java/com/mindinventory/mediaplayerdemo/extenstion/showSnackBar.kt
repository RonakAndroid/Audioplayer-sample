package com.mindinventory.mediaplayerdemo.extenstion

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Developer 190
 */

fun View.showSnackBar(msg: String) {
    Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG).show()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}