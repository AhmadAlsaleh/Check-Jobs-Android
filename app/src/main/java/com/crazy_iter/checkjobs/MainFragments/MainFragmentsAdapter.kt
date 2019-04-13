package com.crazy_iter.checkjobs.MainFragments

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MainFragmentsAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) = when (position) {
        0 -> MainFragment0()
        1 -> MainFragment1()
        2 -> MainFragment2()
        3 -> MainFragment3()
        4 -> MainFragment4()
        else -> null
    }

    override fun getCount() = 5
}
