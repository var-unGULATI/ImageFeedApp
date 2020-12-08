package com.varunsoft.wariqfeed.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.varunsoft.wariqfeed.R
import com.varunsoft.wariqfeed.ui.posts.FeedFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            var fragment = supportFragmentManager.findFragmentByTag(FeedFragment.TAG) as FeedFragment?
            if (fragment == null) {
                fragment = FeedFragment.newInstance()
                fragmentTransaction.add(R.id.containerFragment, fragment)
            } else {
                fragmentTransaction.show(fragment)
            }
            fragmentTransaction.commit()
        }

        

    }
}