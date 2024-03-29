package com.invariant.dhamaka.activity

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.invariant.dhamaka.R
import com.invariant.dhamaka.databinding.ActivityIntroBinding
import org.jetbrains.anko.startActivity

class IntroActivity : BaseActivity() {

    private lateinit var binding: ActivityIntroBinding
    private lateinit var layouts: Array<Int>
    private lateinit var dots: ArrayList<ImageView>
    private lateinit var adapter: MyViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {

        /*if (!preferences.isFirstTimeLaunch()) {
            launchHomeScreen()
            finish()
        }*/

        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)
        binding.lifecycleOwner = this

        layouts = arrayOf(
            R.layout.activity_intro_1,
            R.layout.activity_intro_2,
            R.layout.activity_intro_3
        )

        addBottomDots(0)

        changeStatusBarColor()

        adapter = MyViewPagerAdapter()
        binding.viewPager.adapter = adapter
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                addBottomDots(position)

                if (position == layouts.size - 1) {
                    binding.btnNext.text = getString(R.string.done)
                    binding.btnSkip.visibility = View.GONE
                } else {
                    binding.btnNext.text = getString(R.string.next)
                    binding.btnSkip.visibility = View.VISIBLE
                }
            }

        })

        binding.btnSkip.setOnClickListener {
            launchHomeScreen()
        }

        binding.btnNext.setOnClickListener {
            val current = getItem(+1)
            if (current < layouts.size) {
                binding.viewPager.currentItem = current
            } else {
                launchHomeScreen()
            }
        }
    }

    private fun getItem(i: Int): Int {
        return binding.viewPager.currentItem + i
    }

    private fun launchHomeScreen() {
        startActivity<MainActivity>()
        finish()
    }

    private fun addBottomDots(currentPage: Int) {
        dots = ArrayList()

        binding.layoutDots.removeAllViews()
        for (i in 0..2) {
            dots.add(ImageView(this))
            dots[i].setImageResource(R.drawable.ic_slider_dot)
            dots[i].setPadding(8, 0, 8, 0)
            binding.layoutDots.addView(dots[i])
        }

        if (dots.isNotEmpty())
            dots[currentPage].setImageResource(R.drawable.ic_slider_dot_red)
    }

    /**
     * Making notification bar transparent
     */
    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }


    /**
     * View pager adapter
     */
    inner class MyViewPagerAdapter : PagerAdapter() {

        override fun getCount() = layouts.size

        private var layoutInflater: LayoutInflater? = null

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val view = layoutInflater!!.inflate(layouts[position], container, false)
            container.addView(view)

            return view
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }


        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }
}
