package com.mindinventory.mediaplayerdemo.presentation.activities

import com.mindinventory.mediaplayerdemo.R
import com.mindinventory.mediaplayerdemo.Utils.KeyUtils
import com.mindinventory.mediaplayerdemo.presentation.adapter.VideoAdapter
import com.mindinventory.mediaplayerdemo.presentation.base.BaseActivity
import com.mindinventory.mediaplayerdemo.presentation.model.Video
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayerActivity : BaseActivity() {
    val videoList = arrayListOf<Video>()

    override fun getContentResource(): Int {
        return R.layout.activity_video_player
    }

    private val videoAdapter by lazy {
        VideoAdapter(this, rvVideoFiles)
    }

    override fun initViews() {
        super.initViews()

        val v1 = Video(1, false, "0.00", "9:56",
                KeyUtils.VIDEOFILEPATH + "BigBuckBunny.mp4")
        val v2 = Video(2, false, "0.00", "10:54",
                KeyUtils.VIDEOFILEPATH + "ElephantsDream.mp4")
        val v3 = Video(3, false, "0.00", "0:15",
                KeyUtils.VIDEOFILEPATH + "ForBiggerBlazes.mp4")
        val v4 = Video(4, false, "0.00", "10:54",
                KeyUtils.VIDEOFILEPATH + "TearsOfSteel.mp4")
        val v5 = Video(5, false, "0.00", "12:14",
                KeyUtils.VIDEOFILEPATH + "ForBiggerEscapes.mp4")
        val v6 = Video(6, false, "0.00", "",
                KeyUtils.VIDEOFILEPATH + "ForBiggerFun.mp4")
        val v7 = Video(7, false, "0.00", "1:00",
                KeyUtils.VIDEOFILEPATH + "SubaruOutbackOnStreetAndDirt.mp4")
        val v8 = Video(8, false, "0.00", "",
                KeyUtils.VIDEOFILEPATH + "ForBiggerJoyrides.mp4")
        val v9 = Video(9, false, "0.00", "0:15",
                KeyUtils.VIDEOFILEPATH + "ForBiggerMeltdowns.mp4")
        val v10 = Video(10, false, "0.00", "0:15",
                KeyUtils.VIDEOFILEPATH + "Sintel.mp4")

        videoList.add(v1)
        videoList.add(v2)
        videoList.add(v3)
        videoList.add(v4)
        videoList.add(v5)
        videoList.add(v6)
        videoList.add(v7)
        videoList.add(v8)
        videoList.add(v9)
        videoList.add(v10)

        rvVideoFiles.adapter = videoAdapter
        videoAdapter.addDataToList(videoList)
    }

    override fun onResume() {
        super.onResume()
        videoAdapter.initializeVideo(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
