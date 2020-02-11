package com.example.squarerepos.ui

import android.os.Bundle
import com.example.squarerepos.R
import com.example.squarerepos.ui.repos.ReposFragment
import dagger.android.support.DaggerAppCompatActivity



class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, ReposFragment.newInstance())
                .commit()
        }
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

}
