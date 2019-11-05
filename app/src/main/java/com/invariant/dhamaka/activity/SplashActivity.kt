package com.invariant.dhamaka.activity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.invariant.dhamaka.R
import com.invariant.dhamaka.preference.DhamakaPreferences
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var preferences: DhamakaPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        preferences = DhamakaPreferences(this)

        Handler().postDelayed({
            if (preferences.isFirstTimeLaunch()) {
                startActivity<IntroActivity>()
                preferences.setFirstTimeLaunch(false)
            } else {
                startActivity<MainActivity>()
            }

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 1000)
    }
}
