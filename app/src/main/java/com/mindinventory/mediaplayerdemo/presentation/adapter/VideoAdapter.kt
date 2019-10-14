package com.mindinventory.mediaplayerdemo.presentation.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.mindinventory.mediaplayerdemo.R
import com.mindinventory.mediaplayerdemo.Utils.KeyUtils.VIDEOFILEPATH
import com.mindinventory.mediaplayerdemo.extenstion.inflate
import com.mindinventory.mediaplayerdemo.presentation.model.Video
import kotlinx.android.synthetic.main.row_video.view.*
import java.util.*


class VideoAdapter(activity: Context, rvVideoFiles: RecyclerView?)
    : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    private val TAG = this::class.java.simpleName

    private lateinit var player: VideoView

    private var videoList = mutableListOf<Video>()
    var mPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(parent.inflate(R.layout.row_video))
    }

    override fun getItemCount() = videoList.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videoList[position], position)
    }

    fun addDataToList(videoList: ArrayList<Video>) {
        videoList.let { this.videoList.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: VideoViewHolder) {
        Log.d(TAG, "onViewAttachedToWindow")
        super.onViewAttachedToWindow(holder)
    }


    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(video: Video, position: Int) {

            itemView.imagePlayPause.setOnClickListener {
                val mediaController = MediaController(itemView.context)
                mediaController.setAnchorView(itemView.imagePlayPause)

                val uri = Uri.parse(VIDEOFILEPATH + "ElephantsDream.mp4")
                itemView.vvVideoView.setMediaController(mediaController)
                itemView.vvVideoView.setVideoURI(uri)
                itemView.vvVideoView.requestFocus()
                itemView.vvVideoView.start()
            }

        }
    }

    override fun onViewDetachedFromWindow(holder: VideoViewHolder) {
        Log.d(TAG, "onViewDetachedFromWindow")
        super.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: VideoViewHolder) {
        Log.d(TAG, "onViewRecycled")
        super.onViewRecycled(holder)
    }

    fun initializeVideo(context: Context) {
/*

        val mediaController = MediaController(context)
        mediaController.setAnchorView()

        val uri = Uri.parse(VIDEOFILEPATH+"ElephantsDream.mp4")
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()
*/
    }
}
