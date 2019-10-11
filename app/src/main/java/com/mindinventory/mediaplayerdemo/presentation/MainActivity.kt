package com.mindinventory.mediaplayerdemo.presentation

import com.mindinventory.mediaplayerdemo.R
import com.mindinventory.mediaplayerdemo.Utils.KeyUtils
import com.mindinventory.mediaplayerdemo.presentation.adapter.MediaAdapter
import com.mindinventory.mediaplayerdemo.presentation.base.BaseActivity
import com.mindinventory.mediaplayerdemo.presentation.model.Media
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    val mediaList = arrayListOf<Media>()

    lateinit var m1: Media
    lateinit var m2: Media
    lateinit var m3: Media
    lateinit var m4: Media
    lateinit var m5: Media
    lateinit var m6: Media
    lateinit var m7: Media
    lateinit var m8: Media
    lateinit var m9: Media
    lateinit var m10: Media

    private val mediaAdapter by lazy {
        MediaAdapter(this, rvMusicFiles)
    }

    override fun getContentResource(): Int = R.layout.activity_main

    override fun initViews() {
        super.initViews()
        m1 = Media(mediaAdapter.itemCount, false, "2.00", "6:13", KeyUtils.FILEPATH + "1.mp3")
        m2 = Media(mediaAdapter.itemCount, false, "2.00", "7:06", KeyUtils.FILEPATH + "2.mp3")
        m3 = Media(mediaAdapter.itemCount, false, "2.00", "5:54", KeyUtils.FILEPATH + "3.mp3")
        m4 = Media(mediaAdapter.itemCount, false, "2.00", "5:03", KeyUtils.FILEPATH + "4.mp3")
        m5 = Media(mediaAdapter.itemCount, false, "2.00", "5:54", KeyUtils.FILEPATH + "5.mp3")
        m6 = Media(mediaAdapter.itemCount, false, "2.00", "4:40", KeyUtils.FILEPATH + "6.mp3")
        m7 = Media(mediaAdapter.itemCount, false, "2.00", "7:01", KeyUtils.FILEPATH + "7.mp3")
        m8 = Media(mediaAdapter.itemCount, false, "2.00", "8:25", KeyUtils.FILEPATH + "8.mp3")
        m9 = Media(mediaAdapter.itemCount, false, "2.00", "6:29", KeyUtils.FILEPATH + "9.mp3")
        m10 = Media(mediaAdapter.itemCount, false, "2.00", "8:47", KeyUtils.FILEPATH + "10.mp3")

        mediaList.add(m1)
        mediaList.add(m2)
        mediaList.add(m3)
        mediaList.add(m4)
        mediaList.add(m5)
        mediaList.add(m6)
        mediaList.add(m7)
        mediaList.add(m8)
        mediaList.add(m9)
        mediaList.add(m10)

        rvMusicFiles.adapter = mediaAdapter
        mediaAdapter.addDataList(mediaList)
    }

    override fun onResume() {
        super.onResume()
        mediaAdapter.initializeMedia()
    }

    override fun onPause() {
        mediaAdapter.stopAudio()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaAdapter.releaseMedia()
    }
}
