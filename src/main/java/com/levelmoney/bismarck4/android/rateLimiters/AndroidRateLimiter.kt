package com.levelmoney.bismarck4.android.rateLimiters

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.preference.PreferenceManager

import com.levelmoney.bismarck4.RateLimiter

/**
 * Created by Aaron Sarazan on 11/25/13
 * Copyright(c) 2013 Level, Inc.
 */
public class AndroidRateLimiter(val c: Context, val interval: Long, val key: String) : RateLimiter {

    /**
     * Uses [System.nanoTime]
     */
    override var lastReset: Long = 0
        private set

    override fun update() {
        val e = editor()
        e.putLong(key, System.nanoTime()).commit()
    }

    override fun reset() {
        editor().remove(key).commit()
        lastReset = System.nanoTime()
    }

    override fun isFresh(): Boolean {
        return !pass(System.nanoTime())
    }

    private fun prefs(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(c)
    }

    private fun editor(): Editor {
        return prefs().edit()
    }

    private fun pass(current: Long = System.nanoTime()): Boolean {
        val last = getLastRun()
        return last == 0L || current - last >= interval
    }

    public fun getLastRun(): Long {
        return prefs().getLong(key, 0L)
    }

}
