package com.mindinventory.mediaplayerdemo.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mindinventory.mediaplayerdemo.R
import com.mindinventory.mediaplayerdemo.presentation.adapter.CustomAdapter
import kotlinx.android.synthetic.main.activity_main2.*

class CustomSpinnerActivity : AppCompatActivity() {

    var countryNames = arrayOf("India", "China", "Australia", "Portugle", "America", "New Zealand")
    var flags = intArrayOf(R.drawable.ic_favorite_disable, R.drawable.ic_expand_more, R.drawable.ic_pause_black, R.drawable.ic_favorite_enable, R.drawable.ic_favorite_disable, R.drawable.ic_favorite_disable)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //Getting the instance of Spinner and applying OnItemSelectedListener on it

        val customAdapter = CustomAdapter(this, flags, countryNames)
        spn.adapter = customAdapter
    }
}
