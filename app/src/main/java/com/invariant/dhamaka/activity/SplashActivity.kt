package com.invariant.dhamaka.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.invariant.dhamaka.R
import com.invariant.dhamaka.preference.DhamakaPreferences

class SplashActivity : AppCompatActivity() {

    private lateinit var preferences: DhamakaPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (preferences.isFirstTimeLaunch()) {

        } else {

        }
    }
}
