package com.mindinventory.mediaplayerdemo.presentation.adapter

import android.media.MediaPlayer
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mindinventory.mediaplayerdemo.R
import com.mindinventory.mediaplayerdemo.extenstion.inflate
import com.mindinventory.mediaplayerdemo.extenstion.millisecondsToTime
import com.mindinventory.mediaplayerdemo.presentation.model.Media
import kotlinx.android.synthetic.main.row_media.view.*
import java.util.*

class MediaAdapter(private val rvMedia: RecyclerView)
    : RecyclerView.Adapter<MediaAdapter.MediaViewHolder>() {
    private val TAG = this::class.java.simpleName

    private lateinit var player: MediaPlayer
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    var currentposition: Int = -1

    private var mediaList = mutableListOf<Media>()
    var mPosition = -1

    override fun getItemCount() = mediaList.size

    fun addDataList(mediaList: ArrayList<Media>?) {
        mediaList?.let { this.mediaList.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(mediaList[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        return MediaViewHolder(parent.inflate(R.layout.row_media))
    }

    inner class MediaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var seekbar: SeekBar? = itemView.findViewById(R.id.seekbar)
        var currentTime: TextView? = itemView.findViewById(R.id.currentTime)

        fun bind(media: Media, position: Int) {
            if (currentposition == position && player.isPlaying) {
                itemView.imgPlayPause?.setImageDrawable(ContextCompat.getDrawable(
                        itemView.imgPlayPause.context, R.drawable.ic_pause_black))
            } else {
                itemView.imgPlayPause?.setImageDrawable(ContextCompat.getDrawable(
                        itemView.imgPlayPause.context, R.drawable.ic_play_arrow_black))
            }

            itemView.totalTime.text = media.totalTime

            itemView.imgPlayPause.setOnClickListener {
                if (currentposition == position && player.isPlaying) {
                    pauseAudio()
                    itemView.imgPlayPause?.setImageDrawable(ContextCompat.getDrawable(
                            itemView.imgPlayPause.context, R.drawable.ic_play_arrow_black))
                } else if (currentposition == position) {
                    startAudio(media.filepath, true, position)
                    itemView.imgPlayPause?.setImageDrawable(ContextCompat.getDrawable(
                            itemView.imgPlayPause.context, R.drawable.ic_pause_black))
                } else {
                    stopAudio()
                    currentposition = position
                    startAudio(media.filepath, false, position)
                    itemView.imgPlayPause?.setImageDrawable(ContextCompat.getDrawable(
                            itemView.imgPlayPause.context, R.drawable.ic_pause_black))
                }
            }

            if (currentposition == position) {
                itemView.seekbar.isEnabled = true
                itemView.seekbar.max = player.duration
                itemView.seekbar.progress = player.currentPosition

                itemView.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onStopTrackingTouch(seekBar: SeekBar) {
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar) {

                    }

                    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                        if (fromUser) {
                            player.seekTo(progress)
                        }
                    }
                })

            } else {
                itemView.seekbar.progress = 0
                itemView.seekbar.isEnabled = false
            }
        }
    }

    fun releaseMedia() {
        stopAudio()
        if (::player.isInitialized)
            player.release()
    }

    fun initializeMedia() {
        player = MediaPlayer()
    }

    private fun startAudio(audioUrl: String, isAudioResume: Boolean, position: Int) {
        try {
            mPosition = position
            if (!isAudioResume) {
                player.reset()
                player.setDataSource(audioUrl)
                player.prepare()
            }
            player.start()
            startHandlerRemaingTime()
            notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun startHandlerRemaingTime() {
        mHandler = Handler()

        mRunnable = Runnable {
            if (getCurrentSeekBar() != null) {
                getCurrentSeekBar()?.max = player.duration
                getCurrentSeekBar()?.progress = player.currentPosition
                getCurrentTextView()?.text = millisecondsToTime(player.currentPosition.toLong())
                Log.i(TAG, "player.currentPosition>>" + player.currentPosition)
            }
            mHandler.postDelayed(mRunnable, 1000)
        }

        mHandler.postDelayed(mRunnable, 1000)

        player.setOnCompletionListener {
            player.stop()
            player.reset()
            currentposition = -1
            mHandler.removeCallbacks(mRunnable)
            notifyDataSetChanged()
        }
    }

    private fun pauseAudio() {
        try {
            player.pause()
            mHandler.removeCallbacks(mRunnable)
            notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stopAudio() {
        if (!::player.isInitialized) {
            return
        }

        if (player.isPlaying) {
            player.stop()
            mHandler.removeCallbacks(mRunnable)
        }
        currentposition = -1
        player.reset()
        notifyDataSetChanged()
    }

    private fun getCurrentSeekBar(): SeekBar? {
        try {
            val itemView = rvMedia.findViewHolderForLayoutPosition(mPosition)
            val audioViewHolder = itemView as MediaViewHolder
            return audioViewHolder.seekbar
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun getCurrentTextView(): TextView? {
        try {
            val itemView = rvMedia.findViewHolderForLayoutPosition(mPosition)
            val audioViewHolder = itemView as MediaViewHolder
            return audioViewHolder.currentTime
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}