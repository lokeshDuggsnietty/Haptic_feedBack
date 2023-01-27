package com.example.hapticfeedback

import android.media.MediaPlayer
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hapticfeedback.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    var list = mutableListOf<String>()
    private lateinit var binding: ActivityMainBinding
    lateinit var vibrator: Vibrator
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        vibrator = this.getSystemService(VIBRATOR_SERVICE) as Vibrator
        mediaPlayer = MediaPlayer()
        initList()


        binding.mainRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = Adapter(list, vibrator, mediaPlayer, this@MainActivity)
        }

    }


    private fun initList() {
        list.add("LONG_PRESS")
        list.add("KEYBOARD_PRESS")
        list.add("CLOCK_TICK")
        list.add("VIRTUAL_KEY_RELEASE")
        list.add("KEYBOARD_TAP")
        list.add("TEXT_HANDLE_MOVE")
        list.add("GESTURE_START")
        list.add("confirm")
        list.add("EFFECT_HEAVY_CLICK")
        list.add("EFFECT_TICK")
        list.add("EFFECT_DOUBLE_CLICK")
        list.add("CUSTOM_LONG_PRESS")
        list.add("CUSTOM_KEYBOARD_PRESS")
        list.add("CUSTOM_CLOCK_TICK")
        list.add("CUSTOM_VIRTUAL_KEY_RELEASE")
        list.add("CUSTOM_KEYBOARD_TAP")
        list.add("CUSTOM_GESTURE_START")
        list.add("CUSTOM_confirm")

    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
        vibrator.cancel()
    }
}
