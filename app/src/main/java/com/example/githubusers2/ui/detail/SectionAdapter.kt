package com.example.githubusers2.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.githubusers2.R

class SectionAdapter(private val content: Context, fragment: FragmentManager, data: Bundle) :
    FragmentPagerAdapter(fragment, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var bundle: Bundle

    init {
        bundle = data
    }

    @StringRes
    private val TAB = intArrayOf(R.string.tab_1, R.string.tab_2)


    override fun getCount(): Int = 2
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {

            0 -> fragment = FollowsFragment()
            1 -> fragment = FollowingFragment()

        }
        fragment?.arguments = this.bundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return content.resources.getString(TAB[position])
    }


}