package com.mindinventory.mediaplayerdemo.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mindinventory.mediaplayerdemo.R
import com.mindinventory.mediaplayerdemo.extenstion.hide
import com.mindinventory.mediaplayerdemo.extenstion.inflate
import com.mindinventory.mediaplayerdemo.extenstion.showWithCondition
import com.mindinventory.mediaplayerdemo.presentation.model.Video
import kotlinx.android.synthetic.main.row_video.view.*
import java.util.*

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    private val TAG = this::class.java.simpleName
    lateinit var mediaController: MediaController

    private var videoList = mutableListOf<Video>()
    var currentposition: Int = -1
    lateinit var oldvideoView: VideoView

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

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(video: Video, position: Int) {

//            if (currentposition == position && itemView.vvVideoView.isPlaying) {
//                itemView.imgPlayPauseVideo.setImageDrawable(ContextCompat.getDrawable(
//                        itemView.imgPlayPauseVideo.context, R.drawable.ic_pause_black))
//                itemView.imgPlayPauseVideo.hide()
//            } else {
//                itemView.imgPlayPauseVideo.setImageDrawable(ContextCompat.getDrawable(
//                        itemView.imgPlayPauseVideo.context, R.drawable.ic_play_arrow_black))
//            }
            itemView.imgPlayPauseVideo.showWithCondition(!itemView.vvVideoView.isPlaying)

            itemView.imgPlayPauseVideo.setOnClickListener {
                /*if (currentposition == position && itemView.vvVideoView.isPlaying) {
                    pauseVideo(itemView.vvVideoView)
                    itemView.imgPlayPause?.setImageDrawable(ContextCompat.getDrawable(
                            itemView.imgPlayPause.context, R.drawable.ic_play_arrow_black))
                } else if (currentposition == position) {
                    startVideo(itemView.vvVideoView, video.filepath, true, position, mediaController)
                    itemView.imgPlayPauseVideo.hide()
                    itemView.imgPlayPause?.setImageDrawable(ContextCompat.getDrawable(
                            itemView.imgPlayPause.context, R.drawable.ic_pause_black))
                } else {
                    stopVideo()
                    currentposition = position
                    oldvideoView = itemView.vvVideoView
                    startVideo(itemView.vvVideoView, video.filepath, false, position, mediaController)
                    itemView.imgPlayPauseVideo.hide()
                    itemView.imgPlayPause?.setImageDrawable(ContextCompat.getDrawable(
                            itemView.imgPlayPause.context, R.drawable.ic_pause_black))
                }*/

                stopVideo()
                currentposition = position
                oldvideoView = itemView.vvVideoView
                startVideo(itemView.context,itemView.vvVideoView, video.filepath)
                itemView.imgPlayPauseVideo.hide()
            }
        }
    }

    private fun startVideo(context: Context,vvVideoView: VideoView, filepath: String) {
        Log.d(TAG, "start Play")
        vvVideoView.setVideoPath(filepath)
        mediaController = MediaController(context)
        mediaController.setAnchorView(vvVideoView)
        vvVideoView.setMediaController(mediaController)
        vvVideoView.start()
    }

    private fun stopVideo() {
        if (::oldvideoView.isInitialized) {
            oldvideoView.stopPlayback()
            notifyItemChanged(currentposition)
        }
    }
}
