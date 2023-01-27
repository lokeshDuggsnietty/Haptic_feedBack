package com.example.hapticfeedback

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.hapticfeedback.databinding.ItemRecyclerViewBinding


class Adapter(
    val list: List<String>,
    val vibrator: Vibrator,
    var mediaPlayer: MediaPlayer,
    val context: MainActivity
) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    val click = longArrayOf(0, 200, 100, 300, 100, 400)
    val confirmArray = longArrayOf(0, 200, 50, 300, 50, 300)
    val gesture = longArrayOf(0, 300, 50, 200)
    val virtualRelease = longArrayOf(0, 500, 50, 100)
    val keyBoardPress = longArrayOf(0, 200, 100, 600)
    val longPress = longArrayOf(0, 200, 50, 600, 50, 800)
    val clockTick = longArrayOf(0, 300, 50, 300, 50, 400, 50, 400)


    class MyViewHolder(val viewDataBinding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    @SuppressLint("InlinedApi")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: Adapter.MyViewHolder, position: Int) {
        holder.viewDataBinding.textview.text = list.get(position)

        holder.viewDataBinding.textview.setOnClickListener {
            when (holder.adapterPosition) {
                0 -> holder.itemView.performHapticFeedback(
                    HapticFeedbackConstants.LONG_PRESS,
                    HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
                )
                1 -> holder.itemView.performHapticFeedback(
                    HapticFeedbackConstants.KEYBOARD_PRESS,
                    HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
                )
                2 -> holder.itemView.performHapticFeedback(
                    HapticFeedbackConstants.CLOCK_TICK,
                    HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
                )
                3 -> holder.itemView.performHapticFeedback(
                    HapticFeedbackConstants.VIRTUAL_KEY_RELEASE,
                    HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
                )
                4 -> holder.itemView.performHapticFeedback(
                    HapticFeedbackConstants.KEYBOARD_TAP,
                    HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
                )
                5 -> holder.itemView.performHapticFeedback(
                    HapticFeedbackConstants.TEXT_HANDLE_MOVE,
                    HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
                )
                6 -> holder.itemView.performHapticFeedback(
                    HapticFeedbackConstants.GESTURE_START,
                    HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
                )
                7 -> holder.itemView.performHapticFeedback(
                    HapticFeedbackConstants.CONFIRM,
                    HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
                )
                8 -> vibrate(VibrationEffect.EFFECT_HEAVY_CLICK)
                9 -> vibrate(VibrationEffect.EFFECT_TICK)
                10 -> vibrate(VibrationEffect.EFFECT_DOUBLE_CLICK)
                11 -> playSongAndVibration(R.raw.longpress, longPress)
                12 -> playSongAndVibration(R.raw.keyboardpress, keyBoardPress)
                13 -> playSongAndVibration(R.raw.clock, clockTick)
                14 -> playSongAndVibration(R.raw.virtualrelease, virtualRelease)
                15 -> playSongAndVibration(R.raw.click, click)
                16 -> playSongAndVibration(R.raw.gesture, gesture)
                17 -> playSongAndVibration(R.raw.confirm, confirmArray)
            }
        }

    }

    private fun vibrate(vibrationEffect: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            vibrator.vibrate(
                VibrationEffect.createPredefined(
                    vibrationEffect
                )
            )
        }
    }

    private fun playSongAndVibration(sound: Int, vibrationEffect: LongArray) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mediaPlayer = MediaPlayer.create(context, sound)
            mediaPlayer.start()
            vibrator.vibrate(VibrationEffect.createWaveform(vibrationEffect, -1))
        } else {
            vibrator.vibrate(1000)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}