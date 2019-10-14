package com.mindinventory.mediaplayerdemo.presentation.activities

import android.view.View
import android.widget.MediaController
import com.mindinventory.mediaplayerdemo.R
import com.mindinventory.mediaplayerdemo.Utils.KeyUtils.VIDEOFILEPATH
import com.mindinventory.mediaplayerdemo.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_video_view.*

class SingleVideoViewActivity : BaseActivity(), View.OnClickListener {
    private fun configurePausePlayer() {
        videoView.pause()
    }

    override fun getContentResource() = R.layout.activity_video_view

    private fun onClickListeners() {
        btnPlayPause.setOnClickListener(this)
        btnPause.setOnClickListener(this)
    }

    override fun initViews() {
        super.initViews()
        onClickListeners()
    }

    private fun configureMediaPlayer() {

        val mediaController = MediaController(this)
        videoView.setVideoPath(
                VIDEOFILEPATH + "Sintel.mp4")
        videoView.setMediaController(mediaController)
        mediaController.setAnchorView(videoView)
        videoView.requestFocus()
        videoView.start()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnPlayPause -> configureMediaPlayer()
            R.id.btnPause-> configurePausePlayer()
        }
    }
}
