package com.mindinventory.mediaplayerdemo.presentation.activities

import android.content.Intent
import android.view.View
import com.mindinventory.mediaplayerdemo.R
import com.mindinventory.mediaplayerdemo.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_master.*

class MasterActivity : BaseActivity(), View.OnClickListener {

    override fun getContentResource() = R.layout.activity_master

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnMediaPlayer -> {
                startActivity(Intent(this, VideoPlayerActivity::class.java))
            }
            R.id.btnSampleUI -> {
                startActivity(Intent(this, SampleUIComponentActivity::class.java))
            }
            R.id.btnWebViewdemo -> {
                startActivity(Intent(this, WebViewActivity::class.java))
            }
            R.id.btnVideoPlayer -> {
                startActivity(Intent(this, VideoPlayerActivity::class.java))
            }
            R.id.btnSingleVideo -> {
                startActivity(Intent(this, SingleVideoViewActivity::class.java))
            }
            R.id.btnCustomSpinner -> {
                startActivity(Intent(this, CustomSpinnerActivity::class.java))
            }
            R.id.btnWebViewSample -> {
                startActivity(Intent(this, WebViewSampleActivity::class.java))
            }
            R.id.btnAPIcall -> {
                startActivity(Intent(this, WebAPIcallActivity::class.java))
            }
        }
    }

    override fun initViews() {
        super.initViews()
        setlisteners()
    }

    private fun setlisteners() {
        btnWebViewSample.setOnClickListener(this)
        btnSampleUI.setOnClickListener(this)
        btnVideoPlayer.setOnClickListener(this)
        btnSingleVideo.setOnClickListener(this)
        btnCustomSpinner.setOnClickListener(this)
        btnWebViewdemo.setOnClickListener(this)
        btnMediaPlayer.setOnClickListener(this)
        btnAPIcall.setOnClickListener(this)
    }
}
