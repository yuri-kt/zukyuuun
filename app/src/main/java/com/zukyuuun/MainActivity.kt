package com.zukyuuun

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.zukyuuun.api.TweetApi
import com.zukyuuun.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        TweetApi().get {
            val list = listOf("dummy1", "dummy2", "dummy3")
            binding.recyclerView.adapter = TimelineAdapter(list)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }
}
