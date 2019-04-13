package com.crazy_iter.checkjobs

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.crazy_iter.checkjobs.MainFragments.MainFragmentsAdapter
import kotlinx.android.synthetic.main.activity_tips.*

class TipsActivity : AppCompatActivity() {

    private val locationPreCode = 100
    private val pagesNum = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)

        tipsVP.offscreenPageLimit = pagesNum - 1
        tipsVP.adapter = MainFragmentsAdapter(supportFragmentManager)
        tipsVP.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {}

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageSelected(p0: Int) {
                if (p0 == pagesNum - 1) {
                    skipSlideTV.setText(R.string.done)
                } else {
                    skipSlideTV.setText(R.string.skip)
                }
            }

        })

        skipSlideTV.setOnClickListener {
            if (tipsVP.currentItem == pagesNum - 1) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                tipsVP.currentItem = tipsVP.currentItem + 1
            }
        }

        requestCall()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == locationPreCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                requestCall()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun requestCall() {
        if (ActivityCompat.shouldShowRequestPermissionRationale
                (this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            AlertDialog.Builder(this)
                    .setMessage(R.string.permission_needed)
                    .setPositiveButton(R.string.ok) { _, _ ->
                        ActivityCompat.requestPermissions(
                                this,
                                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                locationPreCode
                        )
                    }
                    .setNegativeButton(R.string.cancel) { dialogInterface, _ ->
                        dialogInterface.dismiss()
                        requestCall()
                    }
                    .create()
                    .show()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPreCode)
        }
    }
}
