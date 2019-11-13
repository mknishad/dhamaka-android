package com.invariant.dhamaka.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.invariant.dhamaka.preference.DhamakaPreferences

open class BaseActivity : AppCompatActivity() {

    protected lateinit var preferences: DhamakaPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferences = DhamakaPreferences(this)
    }
}
