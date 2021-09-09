package com.nikx.mangaindo.Adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nikx.mangaindo.Fragments.AboutFragment
import com.nikx.mangaindo.Fragments.BookmarkFragment
import com.nikx.mangaindo.Fragments.HomeFragment


class ViewPagerAdapter(supportFragmentManager: FragmentManager, searchs: String?, genres: String?) : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()
    private var filterListResult : String? = searchs
    private var filterListResult2 : String? = genres


    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                val bundle = Bundle()
                val homeFragment = HomeFragment()
                bundle.putString("cariin", filterListResult)
                bundle.putString("genrenih", filterListResult2)
                homeFragment.arguments = bundle
                homeFragment
            }
            1 -> BookmarkFragment()
            else -> AboutFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}