package com.mindinventory.mediaplayerdemo.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.mindinventory.mediaplayerdemo.R
import com.mindinventory.mediaplayerdemo.presentation.adapter.CustomAdapter
import kotlinx.android.synthetic.main.activity_sample_uicomponent.*

class SampleUIComponentActivity : AppCompatActivity(), View.OnClickListener {

    var spinnerArray = arrayOf("Red", "Blue", "White", "Yellow", "Black", "Green", "Purple", "Orange", "Grey")
    var fruits = arrayOf("Apple", "Grapes", "Mango", "Pineapple", "Strawberry")
    var images = intArrayOf(R.drawable.ic_favorite_enable,
            R.drawable.ic_favorite_disable,
            R.drawable.ic_favorite_enable,
            R.drawable.ic_favorite_disable,
            R.drawable.ic_favorite_enable)

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnclickWebView ->
                startActivity(Intent(this, WebViewActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_uicomponent)
        btnclickWebView.setOnClickListener(this)

        rattingBar.setProgress(3)
//        val stars = rattingBar.getProgressDrawable() as LayerDrawable
//        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP)

        val spinnerArrayAdapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                spinnerArray)
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item)
        spinner.setAdapter(spinnerArrayAdapter)


        val customAdapter = CustomAdapter(this, images, fruits)
        spinner.setAdapter(customAdapter)
    }
}
