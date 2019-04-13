package com.crazy_iter.checkjobs

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        Handler().postDelayed({
            val sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)

            if (sharedPreferences.getBoolean("first", true)) {

                val editSharedPreferences = sharedPreferences.edit()
                editSharedPreferences.putBoolean("first", false)
                editSharedPreferences.apply()

                startActivity(Intent(this, TipsActivity::class.java))

            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }

            finish()
        }, 2000)

    }
}
