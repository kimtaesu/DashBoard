package com.hucet.robo.dash

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.hucet.robo.dash.fragment.FakePageFragment
import com.hucet.robo.dash.fragment.MainFragment
import com.hucet.robo.dash.fragment.SubFragment
import kotlinx.android.synthetic.main.activity_dashboard.*

/**
 * Created by taesu on 2017-12-20.
 */

private enum class EnumFragments(val title: String) {
    Fake("fake"), Main("main"), Sub("sub")
}

class DashBoardActivity : AppCompatActivity() {

    private val tabs = listOf(EnumFragments.Fake, EnumFragments.Main, EnumFragments.Sub)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        initActionbar()
        initViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true
    }

    private fun initViewPager() {
        tabLayout.setupWithViewPager(viewpager)
        viewpager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int = tabs.size

            override fun getPageTitle(position: Int): CharSequence {
                return when (position) {
                    EnumFragments.Main.ordinal -> {
                        EnumFragments.Main.title
                    }
                    EnumFragments.Sub.ordinal -> {
                        EnumFragments.Sub.title
                    }
                    EnumFragments.Fake.ordinal -> {
                        EnumFragments.Fake.title
                    }
                    else -> {
                        throw IllegalArgumentException()
                    }
                }
            }

            override fun getItem(position: Int): Fragment {
                return when (position) {
                    EnumFragments.Main.ordinal -> {
                        MainFragment.newInstance()
                    }
                    EnumFragments.Sub.ordinal -> {
                        SubFragment.newInstance()
                    }
                    EnumFragments.Fake.ordinal -> {
                        FakePageFragment.newInstance()
                    }
                    else -> {
                        throw IllegalArgumentException()
                    }
                }
            }
        }
    }

    private fun initActionbar() {
        setSupportActionBar(toolbar)
    }

    override fun onStart() {
        super.onStart()
        animationView.playAnimation()
    }

    override fun onStop() {
        super.onStop()
        animationView.cancelAnimation()
    }
}