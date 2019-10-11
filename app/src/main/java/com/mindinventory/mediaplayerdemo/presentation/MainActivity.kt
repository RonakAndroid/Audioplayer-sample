package com.mindinventory.mediaplayerdemo.presentation

import android.os.Bundle
import android.widget.Toast
import com.mindinventory.mediaplayerdemo.R
import com.mindinventory.mediaplayerdemo.Utils.KeyUtils
import com.mindinventory.mediaplayerdemo.extenstion.show
import com.mindinventory.mediaplayerdemo.presentation.adapter.MediaAdapter
import com.mindinventory.mediaplayerdemo.presentation.base.BaseActivity
import com.mindinventory.mediaplayerdemo.presentation.model.Media
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_media.*


class MainActivity : BaseActivity() {
    val mediaList = arrayListOf<Media>()

    lateinit var m1: Media
    private val mediaAdapter by lazy {
        MediaAdapter(this, rvMusicFiles, this::onPlayPause)
    }

    override fun getContentResource(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initViews() {
        super.initViews()
        m1 = Media(mediaAdapter.itemCount,
                false,
                "2.00",
                "10",
                KeyUtils.FILEPATH + "1.mp3")
        for (i in 1..20) {
            mediaList.add(m1)
        }
        rvMusicFiles.adapter = mediaAdapter
        mediaAdapter.addDataList(mediaList)
    }

    fun onPlayPause(media: Media) {
        if (media.isPlaying) {
//            mediaAdapter.startAudio()

            Toast.makeText(this, "media.isPlaying == " + media.isPlaying, Toast.LENGTH_LONG).show()
            media.isPlaying = false
        } else {
            Toast.makeText(this, "isPlaying== " + media.isPlaying, Toast.LENGTH_LONG).show()
            mediaAdapter.stopAudio()
            seekbar.show()
            media.isPlaying = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaAdapter.releaseMedia()
    }

    override fun onPause() {
        mediaAdapter.stopAudio()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        mediaAdapter.initializeMedia()
    }
}
