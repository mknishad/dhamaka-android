package com.invariant.dhamaka.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.invariant.dhamaka.R
import com.invariant.dhamaka.preference.DhamakaPreferences
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var preferences: DhamakaPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        preferences = DhamakaPreferences(this)

        if (preferences.isFirstTimeLaunch()) {
            startActivity<IntroActivity>()
        } else {
            startActivity<MainActivity>()
        }
    }
}
