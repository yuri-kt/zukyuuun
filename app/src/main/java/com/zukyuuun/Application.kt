package com.zukyuuun

import android.app.Application
import android.util.Log
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        val config = TwitterConfig.Builder(this)
            .logger(DefaultLogger(Log.DEBUG))
            .twitterAuthConfig(
                TwitterAuthConfig(
                    "F6HZTisYRr3z2RRlAZU66c9BV",
                    "KpGDSJz7qsuMU3ask5PjGi97bUGIXauXxL4AnVdPpOVMESTQ14"
                )
            )
            .debug(true)
            .build()
        Twitter.initialize(config)
    }
}
