package com.mindinventory.mediaplayerdemo.presentation.adapter

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mindinventory.mediaplayerdemo.R
import com.mindinventory.mediaplayerdemo.extenstion.inflate
import com.mindinventory.mediaplayerdemo.extenstion.millisecondsToTime
import com.mindinventory.mediaplayerdemo.presentation.model.Media
import kotlinx.android.synthetic.main.row_media.view.*
import java.util.*

class MediaAdapter(var activity: Context,
                   private val rvMedia: RecyclerView,
                   val itemCheckedChanged: (media: Media) -> Unit)
    : RecyclerView.Adapter<MediaAdapter.MediaViewHolder>() {
    private val TAG = this::class.java.simpleName

    private lateinit var player: MediaPlayer
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    var currentId: Int = 0

    private var mediaList = mutableListOf<Media>()
    var mPosition = -1

    override fun getItemCount() = mediaList.size

    fun addDataList(mediaModel: ArrayList<Media>?) {
        mediaModel?.let { mediaList.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(mediaList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder =
            MediaViewHolder(parent.inflate(R.layout.row_media))

    inner class MediaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var seekbar: SeekBar? = itemView.findViewById(R.id.seekbar)

        fun bind(media: Media) {

            if (currentId == adapterPosition && player.isPlaying) {
                pauseAudio()
                itemView.imgPlayPause?.buttonDrawable = (ContextCompat.getDrawable(activity, R.drawable.ic_play_arrow_black))
            } else if (currentId == adapterPosition) {
                startAudio(media.filepath, true, adapterPosition)
                itemView.imgPlayPause?.buttonDrawable = (ContextCompat.getDrawable(activity, R.drawable.ic_pause_black))
            } else {
                stopAudio()
                currentId = media.id
                startAudio(media.filepath, true, adapterPosition)
                itemView.imgPlayPause?.buttonDrawable = (ContextCompat.getDrawable(activity, R.drawable.ic_pause_black))
            }
            itemView.totalTime.text = media.totalTime

            itemView.imgPlayPause.setOnCheckedChangeListener { _, _ ->

                itemCheckedChanged(media)
            }
            val mediatime = millisecondsToTime(player.duration.toLong())
            itemView.totalTime.text = mediatime

            val mSeekbarUpdateHandler = Handler()

            val mUpdateSeekbar = object : Runnable {
                override fun run() {
                    itemView.seekbar.progress = player.currentPosition
                    Log.i(TAG, "player.getCurrentPosition()>>>" + player.currentPosition)
                    mSeekbarUpdateHandler.postDelayed(this, 50)
                }
            }

            if (currentId == adapterPosition) {
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

        fun startAudio(audioUrl: String, isAudioResume: Boolean, position: Int) {
            try {
                mPosition = position
                if (!isAudioResume) {
                    player.reset()
                    player.setDataSource(audioUrl)
                    player.prepare()
                }
                player.start()
                startHandler()
                notifyDataSetChanged()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun startHandler() {
            mHandler = Handler()

            mRunnable = Runnable {
                if (getCurrentSeekBar() != null) {
                    getCurrentSeekBar()?.max = player.duration
                    getCurrentSeekBar()?.progress = player.currentPosition
                }

                mHandler.postDelayed(mRunnable, 15)
            }

            mHandler.postDelayed(mRunnable, 15)

            player.setOnCompletionListener {
                player.stop()
                player.reset()
                currentId = 0
                mHandler.removeCallbacks(mRunnable)
                notifyDataSetChanged()
            }
        }

        private fun getCurrentSeekBar(): SeekBar? {
            try {
                val itemView = rvMedia.findViewHolderForLayoutPosition(mPosition)
                val mediaViewHolder = itemView as MediaViewHolder
                return mediaViewHolder.seekbar
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
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

        fun onStartMedia(media: Media) {
//            showLodingDialog()

            if (player.isPlaying) {
                player.pause()
                media.isPlaying = false
            }
            if (media.isPlaying) { //initially, pause is set to false
                player.seekTo(player.currentPosition)
                player.start()
                media.isPlaying = false
                //playing audio when in paused state
            } else {
                player.start()
                //playing audio when in prepared state
            }
        }

        fun onStopMedia(media: Media) {
            with(player) {
                setAudioStreamType(AudioManager.STREAM_MUSIC)
                setDataSource(media.filepath)
                prepare()
                stop()
            }
        }

        fun onRelaseMedia(media: Media) {
            if (player.isPlaying || media.isPlaying.equals(true)) {
                media.isPlaying = false
                player.stop()
                player.reset()
                player.release()
                //audio is stopped here
            }
        }

/*
        private fun showLodingDialog() {
            player.setOnInfoListener(object : player.OnInfoListener {
                override fun onInfo(mp: player, what: Int, extra: Int): Boolean {
                    when (what) {
//                    player.MEDIA_INFO_BUFFERING_START -> progressBar.show()
//                    player.MEDIA_INFO_BUFFERING_END ->  progressBar.hide()
                    }
                    return false
                }
            })
        }
*/

        fun releaseMedia() {
            stopAudio()
            if (::player.isInitialized)
                player.release()
        }

        fun stopAudio() {
            if (!::player.isInitialized) {
                return
            }

            if (player.isPlaying) {
                player.stop()
                mHandler.removeCallbacks(mRunnable)
            }
            currentId = 0
            player.reset()
            notifyDataSetChanged()
        }

        fun initializeMedia() {
            player = MediaPlayer()
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
        currentId = 0
        player.reset()
        notifyDataSetChanged()
    }

    fun releaseMedia() {
        stopAudio()
        if (::player.isInitialized) {
            player.release()
        }
    }

    fun initializeMedia() {
        player = MediaPlayer()
    }

}