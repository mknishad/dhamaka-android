package com.invariant.dhamaka.preference

import android.content.Context
import android.content.SharedPreferences
import com.invariant.dhamaka.util.Constants


/**
 * Created by invar on 07-Nov-17.
 */

const val PREFERENCE_TITLE = "DhamakaPreferences"

class DhamakaPreferences(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_TITLE, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preferences.edit()

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor.putBoolean(Constants.FIRST_TIME, isFirstTime)
        editor.commit()
    }

    fun isFirstTimeLaunch(): Boolean {
        return preferences.getBoolean(Constants.FIRST_TIME, true)
    }
}
