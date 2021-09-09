package com.nikx.mangaindo

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Process
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.nikx.mangaindo.Adapters.ViewPagerAdapter
import com.nikx.mangaindo.Fragments.AboutFragment
import com.nikx.mangaindo.Fragments.BookmarkFragment
import com.nikx.mangaindo.Fragments.HomeFragment


class MainActivity : AppCompatActivity() {
    private var text2: String? = null
    private var text_genre: String? = null
    var doubleBackToExitPressedOnce = false
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        text2 = intent.getStringExtra("searchss")
        text_genre = intent.getStringExtra("genree")
        val txt: TextView = findViewById(R.id.searchingin)
        txt.visibility = View.INVISIBLE
        if (!text2.isNullOrEmpty()){
            txt.visibility = View.VISIBLE
            Toast.makeText(this,text2,Toast.LENGTH_SHORT).show()
            txt.text = "Hasil Pencarian : $text2"
        }

        setupTabs()
    }

    private fun setupTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager, text2, text_genre)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(BookmarkFragment(), "Bookmark")
        adapter.addFragment(AboutFragment(), "About")
        val viewPager= findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = adapter
        val tabs= findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)
        tabs.getTabAt(1)
        tabs.getTabAt(2)
    }

    @Override
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            val pid = Process.myPid()
            return Process.killProcess(pid)
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Pencet sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}