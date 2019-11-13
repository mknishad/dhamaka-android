package com.invariant.dhamaka.activity

import android.os.Bundle
import android.os.Handler
import com.invariant.dhamaka.R
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if (preferences.isFirstTimeLaunch()) {
                startActivity<IntroActivity>()
                preferences.setFirstTimeLaunch(false)
            } else {
                startActivity<MainActivity>()
            }

            finish()
        }, 1000)
    }
}
